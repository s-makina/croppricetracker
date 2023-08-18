package com.sps.croppricetracker.ui.usecases

class ValidateAmount {
    fun execute(amount: String): ValidationResult {
        if (amount.isBlank()) {
            return ValidationResult(false, "Amount can't be blank")
        }
       return ValidationResult(true)
    }
}