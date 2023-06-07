package com.sps.compose.ui.events

import android.content.Context

sealed class SignupEvent {
    class OnNameChange(val name: String) : SignupEvent()
    class OnPhoneChange(val phone: String) : SignupEvent()
    class OnEmailChange(val email: String) : SignupEvent()
    class OnPasswordChange(val password: String) : SignupEvent()
    class OnConfirmPassChange(val confirmPass: String) : SignupEvent()
    class OnAcceptTerms(val acceptTerms: Boolean) : SignupEvent()
    class OnLogin(val context: Context) : SignupEvent()
    object OnSubmit : SignupEvent()
}