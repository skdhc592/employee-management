package com.example.employee.service;

import com.example.employee.entity.Department;
import com.example.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 社員サービス（インターフェース）
 */
public interface EmployeeService {

    /**
     * 全社員を取得
     */
    List<Employee> findAll();

    /**
     * IDで社員を取得
     */
    Optional<Employee> findById(Long id);

    /**
     * 社員を保存（新規登録・更新）
     */
    Employee save(Employee employee);

    /**
     * 社員を削除
     */
    void deleteById(Long id);

    /**
     * 在籍中の社員のみを取得
     */
    List<Employee> findActiveEmployees();

    /**
     * 名前で検索（姓または名に含まれる）
     */
    List<Employee> findByName(String name);

    /**
     * 部署で検索
     */
    List<Employee> findByDepartment(Department department);

    /**
     * メールアドレスの存在チェック
     */
    boolean existsByEmail(String email);

    // ページング対応メソッド

    /**
     * ページング対応：全社員を取得
     */
    Page<Employee> findAll(Pageable pageable);

    /**
     * ページング対応：名前で検索
     */
    Page<Employee> findByName(String name, Pageable pageable);

    /**
     * ページング対応：部署で検索
     */
    Page<Employee> findByDepartment(Department department, Pageable pageable);

    /**
     * ページング対応：在籍中の社員のみ取得
     */
    Page<Employee> findActiveEmployees(Pageable pageable);

    /**
     * 部署別統計情報を取得
     */
    List<com.example.employee.dto.DepartmentStats> getDepartmentStats();
}