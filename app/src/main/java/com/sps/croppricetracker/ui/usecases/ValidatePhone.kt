package com.sps.croppricetracker.ui.usecases

import android.util.Patterns

class ValidatePhone {
    fun execute(phone: String): ValidationResult {
        if (phone.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The phone can't be blank"
            )
        }

        if (!Patterns.PHONE.matcher(phone).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid phone number"
            )
        }

        return ValidationResult(successful = true)
    }
}