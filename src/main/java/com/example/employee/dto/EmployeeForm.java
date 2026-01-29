package com.example.employee.dto;

import com.example.employee.validation.PastOrPresent;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 社員フォーム（登録・編集用）
 */
@Data
public class EmployeeForm {

    /**
     * 姓
     */
    @NotBlank(message = "姓を入力してください")
    @Size(max = 50, message = "姓は50文字以内で入力してください")
    private String lastName;

    /**
     * 名
     */
    @NotBlank(message = "名を入力してください")
    @Size(max = 50, message = "名は50文字以内で入力してください")
    private String firstName;

    /**
     * メールアドレス
     */
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "有効なメールアドレスを入力してください")
    @Size(max = 100, message = "メールアドレスは100文字以内で入力してください")
    private String email;

    /**
     * 部署ID
     */
    @NotNull(message = "部署を選択してください")
    private Long departmentId;

    /**
     * 入社日
     */
    @NotNull(message = "入社日を入力してください")
    @PastOrPresent(message = "入社日は過去または現在の日付を入力してください")
    private LocalDate hireDate;

    /**
     * 給与
     */
    @NotNull(message = "給与を入力してください")
    @DecimalMin(value = "100000", message = "給与は100,000円以上で入力してください")
    @DecimalMax(value = "2000000", message = "給与は2,000,000円以下で入力してください")
    private BigDecimal salary;

    /**
     * 在籍状況（デフォルト: 在籍中）
     */
    @NotBlank(message = "在籍状況を選択してください")
    private String status = "在籍中";
}