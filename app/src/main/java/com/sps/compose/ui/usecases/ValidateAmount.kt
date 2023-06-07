package com.sps.compose.ui.usecases

class ValidateAmount {
    fun execute(amount: String): ValidationResult {
        if (amount.isBlank()) {
            return ValidationResult(false, "Amount can't be blank")
        }
       return ValidationResult(true)
    }
}