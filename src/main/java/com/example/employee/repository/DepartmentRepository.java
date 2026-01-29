package com.example.employee.repository;

import com.example.employee.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 部署リポジトリ
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String departmentName);

    boolean existsByDepartmentName(String departmentName);
}