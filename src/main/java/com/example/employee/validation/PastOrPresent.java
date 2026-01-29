package com.example.employee.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 過去または現在の日付であることをチェックするカスタムバリデーション
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastOrPresentValidator.class)
@Documented
public @interface PastOrPresent {

    String message() default "日付は過去または現在でなければなりません";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}