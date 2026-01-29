package com.example.employee.controller;

import com.example.employee.dto.EmployeeForm;
import com.example.employee.entity.Department;
import com.example.employee.entity.Employee;
import com.example.employee.repository.DepartmentRepository;
import com.example.employee.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException; 
import java.io.PrintWriter;  
import java.util.List;

/**
 * 社員管理コントローラ
 */
@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    /**
     * 社員一覧画面（検索機能付き・ページング対応）
     */
    @GetMapping
    public String list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false, defaultValue = "false") boolean activeOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sort,
            @RequestParam(defaultValue = "asc") String order,
            Model model) {
        
        // ソート設定
        Sort.Direction direction = order.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        
        // 検索条件に応じて社員を取得
        Page<Employee> employeePage;
        
        if (name != null && !name.isEmpty()) {
            employeePage = employeeService.findByName(name, pageable);
        } else if (departmentId != null) {
            var department = departmentRepository.findById(departmentId).orElse(null);
            if (department != null) {
                employeePage = employeeService.findByDepartment(department, pageable);
            } else {
                employeePage = employeeService.findAll(pageable);
            }
        } else if (activeOnly) {
            employeePage = employeeService.findActiveEmployees(pageable);
        } else {
            employeePage = employeeService.findAll(pageable);
        }
        
        // 部署一覧を取得
        List<Department> departments = departmentRepository.findAll();
        
        // モデルに追加
        model.addAttribute("employeePage", employeePage);
        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("departments", departments);
        model.addAttribute("searchName", name);
        model.addAttribute("searchDepartmentId", departmentId);
        model.addAttribute("activeOnly", activeOnly);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("totalElements", employeePage.getTotalElements());
        model.addAttribute("currentSort", sort);      
        model.addAttribute("currentOrder", order);
        
        return "employees/list";
    }

    /**
     * 社員詳細画面
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        var employee = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("社員が見つかりません。ID: " + id));
        
        model.addAttribute("employee", employee);
        
        return "employees/detail";
    }

    /**
     * 社員登録フォーム表示
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("employeeForm", new EmployeeForm());
        
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        
        return "employees/form";
    }

    /**
     * 社員登録処理
     */
    @PostMapping
    public String create(
            @Valid @ModelAttribute EmployeeForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        // バリデーションエラーがある場合
        if (bindingResult.hasErrors()) {
            List<Department> departments = departmentRepository.findAll();
            model.addAttribute("departments", departments);
            return "employees/form";
        }
        
        // メールアドレスの重複チェック
        if (employeeService.existsByEmail(form.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "このメールアドレスは既に登録されています");
            List<Department> departments = departmentRepository.findAll();
            model.addAttribute("departments", departments);
            return "employees/form";
        }
        
        // フォームデータをEntityに変換
        Employee employee = new Employee();
        employee.setLastName(form.getLastName());
        employee.setFirstName(form.getFirstName());
        employee.setEmail(form.getEmail());
        employee.setHireDate(form.getHireDate());
        employee.setSalary(form.getSalary());
        employee.setStatus(form.getStatus());
        
        // 部署を設定
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("部署が見つかりません"));
        employee.setDepartment(department);
        
        // 保存
        Employee savedEmployee = employeeService.save(employee);
        
        // 成功メッセージ
        redirectAttributes.addFlashAttribute("successMessage", 
                "社員「" + savedEmployee.getLastName() + " " + savedEmployee.getFirstName() + "」を登録しました。");
        
        // 詳細画面にリダイレクト
        return "redirect:/employees/" + savedEmployee.getEmployeeId();
    }

    /**
     * 社員編集フォーム表示
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("社員が見つかりません。ID: " + id));
        
        EmployeeForm form = new EmployeeForm();
        form.setLastName(employee.getLastName());
        form.setFirstName(employee.getFirstName());
        form.setEmail(employee.getEmail());
        form.setDepartmentId(employee.getDepartment().getDepartmentId());
        form.setHireDate(employee.getHireDate());
        form.setSalary(employee.getSalary());
        form.setStatus(employee.getStatus());
        
        model.addAttribute("employeeForm", form);
        model.addAttribute("employeeId", id);
        model.addAttribute("isEdit", true);
        
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        
        return "employees/form";
    }

    /**
     * 社員更新処理
     */
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute EmployeeForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeId", id);
            model.addAttribute("isEdit", true);
            List<Department> departments = departmentRepository.findAll();
            model.addAttribute("departments", departments);
            return "employees/form";
        }
        
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("社員が見つかりません。ID: " + id));
        
        if (!employee.getEmail().equals(form.getEmail()) && 
            employeeService.existsByEmail(form.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "このメールアドレスは既に登録されています");
            model.addAttribute("employeeId", id);
            model.addAttribute("isEdit", true);
            List<Department> departments = departmentRepository.findAll();
            model.addAttribute("departments", departments);
            return "employees/form";
        }
        
        employee.setLastName(form.getLastName());
        employee.setFirstName(form.getFirstName());
        employee.setEmail(form.getEmail());
        employee.setHireDate(form.getHireDate());
        employee.setSalary(form.getSalary());
        employee.setStatus(form.getStatus());
        
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("部署が見つかりません"));
        employee.setDepartment(department);
        
        employeeService.save(employee);
        
        redirectAttributes.addFlashAttribute("successMessage", 
                "社員「" + employee.getLastName() + " " + employee.getFirstName() + "」の情報を更新しました。");
        
        return "redirect:/employees/" + id;
    }

    /**
     * 社員削除処理
     */
    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {
        
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("社員が見つかりません。ID: " + id));
        
        String employeeName = employee.getLastName() + " " + employee.getFirstName();
        
        employeeService.deleteById(id);
        
        redirectAttributes.addFlashAttribute("successMessage", 
                "社員「" + employeeName + "」を削除しました。");
        
        return "redirect:/employees";
    }

    /**
     * 社員一覧CSVダウンロード
     * @param response HTTPレスポンス
     * @throws IOException 
     */
    @GetMapping("/download-csv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        // 全社員を取得
        List<Employee> employees = employeeService.findAll();
        
        // CSVのヘッダーとデータ型を設定
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // ファイル名を設定（日本語ファイル名対応）
        String filename = "社員一覧.csv";
        String encodedFilename = java.net.URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFilename);
        
        // CSVライターを作成
        PrintWriter writer = response.getWriter();
        
        // BOM（Byte Order Mark）を追加（Excel対応）
        writer.write('\ufeff');
        
        // ヘッダー行を出力
        writer.println("社員番号,姓,名,メールアドレス,部署,入社日,給与,在籍状況");
        
        // データ行を出力
        for (Employee employee : employees) {
            writer.println(
                employee.getEmployeeId() + "," +
                escapeCSV(employee.getLastName()) + "," +
                escapeCSV(employee.getFirstName()) + "," +
                escapeCSV(employee.getEmail()) + "," +
                escapeCSV(employee.getDepartment().getDepartmentName()) + "," +
                employee.getHireDate() + "," +
                employee.getSalary() + "," +
                escapeCSV(employee.getStatus())
            );
        }
        
        writer.flush();
    }
    
    /**
     * CSVのエスケープ処理
     * カンマや改行、ダブルクォートを含むデータを正しく出力
     */
    private String escapeCSV(String value) {
        if (value == null) {
            return "";
        }
        // カンマ、改行、ダブルクォートを含む場合はダブルクォートで囲む
        if (value.contains(",") || value.contains("\n") || value.contains("\"")) {
            // ダブルクォートは2つ重ねてエスケープ
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }

    /**
     * ダッシュボード画面
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // 部署別統計を取得
        var stats = employeeService.getDepartmentStats();
        model.addAttribute("departmentStats", stats);
        
        // 全体の統計も計算
        long totalEmployees = stats.stream().mapToLong(com.example.employee.dto.DepartmentStats::getEmployeeCount).sum();
        model.addAttribute("totalEmployees", totalEmployees);
        
        return "employees/dashboard";
    }
}