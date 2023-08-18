package com.sps.croppricetracker.ui.presentation

import com.sps.croppricetracker.data.model.User

data class LoginState(
    val user: User? = null,
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val phone: String = "",
//    val phone: String = "salvation.developer@gmail.com",
    val phoneError: String? = null,
    val password: String = "",
//    val password: String = "admin",
    val passwordError: String? = null,
    val confirmPass: String = "",
    val confirmPassError: String? = null,
    val acceptTerms: Boolean = false,
    val acceptTermsError: String? = null
)