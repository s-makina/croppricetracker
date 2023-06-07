package com.sps.compose.ui.usecases

data class ValidationResult(
    val successful: Boolean = false,
    val errorMessage: String? = null
)
