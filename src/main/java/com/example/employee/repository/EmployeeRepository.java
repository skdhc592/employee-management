package com.example.employee.repository;

import com.example.employee.entity.Department;
import com.example.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 社員リポジトリ
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // List返却メソッド（ページングなし）
    
    List<Employee> findByEmail(String email);

    List<Employee> findByDepartment(Department department);

    List<Employee> findByStatus(String status);

    List<Employee> findByLastNameContainingOrFirstNameContaining(String lastName, String firstName);

    boolean existsByEmail(String email);

    // Page返却メソッド（ページング対応）

    /**
     * ページング対応：部署で検索
     */
    Page<Employee> findByDepartment(Department department, Pageable pageable);

    /**
     * ページング対応：在籍状況で検索
     */
    Page<Employee> findByStatus(String status, Pageable pageable);

    /**
     * ページング対応：名前で検索
     */
    Page<Employee> findByLastNameContainingOrFirstNameContaining(
            String lastName, String firstName, Pageable pageable);

    /**
     * 部署ごとの社員数をカウント
     */
    @Query("SELECT e.department.departmentId, COUNT(e) FROM Employee e WHERE e.status = '在籍中' GROUP BY e.department.departmentId")
    List<Object[]> countByDepartment();

    /**
     * 部署ごとの平均給与を計算
     */
    @Query("SELECT e.department.departmentId, AVG(e.salary) FROM Employee e WHERE e.status = '在籍中' GROUP BY e.department.departmentId")
    List<Object[]> avgSalaryByDepartment();

    /**
     * 部署ごとの最高給与を取得
     */
    @Query("SELECT e.department.departmentId, MAX(e.salary) FROM Employee e WHERE e.status = '在籍中' GROUP BY e.department.departmentId")
    List<Object[]> maxSalaryByDepartment();

    /**
     * 部署ごとの最低給与を取得
     */
    @Query("SELECT e.department.departmentId, MIN(e.salary) FROM Employee e WHERE e.status = '在籍中' GROUP BY e.department.departmentId")
    List<Object[]> minSalaryByDepartment();

}