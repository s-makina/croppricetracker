package com.sps.croppricetracker.ui.usecases


class ValidateTerms {
    fun execute(accepted: Boolean): ValidationResult {
        if (!accepted) {
            return ValidationResult(false, "Accept terms and conditions")
        }
        return ValidationResult(true)
    }
}