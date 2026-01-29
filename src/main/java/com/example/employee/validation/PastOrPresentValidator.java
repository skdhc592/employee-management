package com.example.employee.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/**
 * PastOrPresentバリデーションの実装クラス
 */
public class PastOrPresentValidator implements ConstraintValidator<PastOrPresent, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        // nullは@NotNullで別途チェックするため、ここではtrueを返す
        if (value == null) {
            return true;
        }
        
        // 現在日付または過去日付ならtrue
        return !value.isAfter(LocalDate.now());
    }
}