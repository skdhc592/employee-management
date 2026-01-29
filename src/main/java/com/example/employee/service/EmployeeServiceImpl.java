package com.example.employee.service;

import com.example.employee.entity.Department;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

/**
 * 社員サービス（実装クラス）
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final com.example.employee.repository.DepartmentRepository departmentRepository;  // ← この行を追加

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findActiveEmployees() {
        return employeeRepository.findByStatus("在籍中");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByName(String name) {
        return employeeRepository.findByLastNameContainingOrFirstNameContaining(name, name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> findByName(String name, Pageable pageable) {
        return employeeRepository.findByLastNameContainingOrFirstNameContaining(name, name, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> findByDepartment(Department department, Pageable pageable) {
        return employeeRepository.findByDepartment(department, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> findActiveEmployees(Pageable pageable) {
        return employeeRepository.findByStatus("在籍中", pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<com.example.employee.dto.DepartmentStats> getDepartmentStats() {
        // 各部署の統計データを取得
        List<Object[]> counts = employeeRepository.countByDepartment();
        List<Object[]> avgSalaries = employeeRepository.avgSalaryByDepartment();
        List<Object[]> maxSalaries = employeeRepository.maxSalaryByDepartment();
        List<Object[]> minSalaries = employeeRepository.minSalaryByDepartment();
        
        // 部署IDをキーとしたマップを作成
        Map<Long, Long> countMap = counts.stream()
                .collect(Collectors.toMap(arr -> (Long) arr[0], arr -> (Long) arr[1]));
        
        // Double → BigDecimal に変換
        Map<Long, BigDecimal> avgSalaryMap = avgSalaries.stream()
                .collect(Collectors.toMap(
                    arr -> (Long) arr[0], 
                    arr -> arr[1] != null ? BigDecimal.valueOf((Double) arr[1]) : BigDecimal.ZERO
                ));
        
        Map<Long, BigDecimal> maxSalaryMap = maxSalaries.stream()
                .collect(Collectors.toMap(
                    arr -> (Long) arr[0], 
                    arr -> arr[1] != null ? (BigDecimal) arr[1] : BigDecimal.ZERO
                ));
        
        Map<Long, BigDecimal> minSalaryMap = minSalaries.stream()
                .collect(Collectors.toMap(
                    arr -> (Long) arr[0], 
                    arr -> arr[1] != null ? (BigDecimal) arr[1] : BigDecimal.ZERO
                ));
        
        // 全部署を取得して統計情報を構築
        return departmentRepository.findAll().stream()
                .map(dept -> new com.example.employee.dto.DepartmentStats(
                        dept.getDepartmentId(),
                        dept.getDepartmentName(),
                        countMap.getOrDefault(dept.getDepartmentId(), 0L),
                        avgSalaryMap.getOrDefault(dept.getDepartmentId(), BigDecimal.ZERO),
                        maxSalaryMap.getOrDefault(dept.getDepartmentId(), BigDecimal.ZERO),
                        minSalaryMap.getOrDefault(dept.getDepartmentId(), BigDecimal.ZERO)
                ))
                .collect(Collectors.toList());
    }
}