package com.sps.compose.ui.usecases

import com.sps.compose.ui.usecases.ValidationResult


class ValidateTerms {
    fun execute(accepted: Boolean): ValidationResult {
        if (!accepted) {
            return ValidationResult(false, "Accept terms and conditions")
        }
        return ValidationResult(true)
    }
}