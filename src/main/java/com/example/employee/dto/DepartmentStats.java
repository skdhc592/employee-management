package com.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 部署別統計情報
 */
@Data
@AllArgsConstructor
public class DepartmentStats {

    /**
     * 部署ID
     */
    private Long departmentId;

    /**
     * 部署名
     */
    private String departmentName;

    /**
     * 社員数
     */
    private Long employeeCount;

    /**
     * 平均給与
     */
    private BigDecimal averageSalary;

    /**
     * 最高給与
     */
    private BigDecimal maxSalary;

    /**
     * 最低給与
     */
    private BigDecimal minSalary;
}