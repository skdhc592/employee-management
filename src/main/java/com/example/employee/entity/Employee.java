package com.example.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 社員エンティティ
 * employeesテーブルに対応
 */
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    /**
     * 社員ID（主キー）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    /**
     * 姓
     */
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * 名
     */
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * メールアドレス
     */
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    /**
     * 所属部署
     * 多対1の関係（多くの社員が1つの部署に所属）
     */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    /**
     * 入社日
     */
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    /**
     * 給与
     */
    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;

    /**
     * 在籍状況
     */
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    /**
     * 登録日時
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}