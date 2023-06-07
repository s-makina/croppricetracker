package com.sps.compose.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sps.compose.data.Prefs
import com.sps.compose.data.Prefs.authTokenKey
import com.sps.compose.data.Prefs.putStr
import com.sps.compose.data.repo.UserRepo
import com.sps.compose.ui.events.SignupEvent
import com.sps.compose.ui.usecases.Validators
import com.sps.compose.util.Resource
import com.sps.compose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.sps.compose.ui.presentation.LoginState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo: UserRepo
) : ViewModel() {
    var state by mutableStateOf(LoginState())
    private val validators = Validators()

    private val loginSignupEventChannel = Channel<Resource<Any>>()
    val loginSignupEvent = loginSignupEventChannel.receiveAsFlow()

    fun event(event: SignupEvent) {
        when (event) {
            is SignupEvent.OnNameChange -> {
                state = state.copy(name = event.name, nameError = null)
            }

            is SignupEvent.OnEmailChange -> {
                state = state.copy(email = event.email, emailError = null)
            }

            is SignupEvent.OnPhoneChange -> {
                state = state.copy(phone = event.phone, phoneError = null)
            }

            is SignupEvent.OnPasswordChange -> {
                state = state.copy(password = event.password, passwordError = null)
            }

            is SignupEvent.OnAcceptTerms -> {
                val error: String? = if(!event.acceptTerms) null else ""
                state = state.copy(acceptTerms = event.acceptTerms, acceptTermsError = error )
            }

            is SignupEvent.OnConfirmPassChange -> {
                var error: String? = null
                if (state.password != event.confirmPass) {
                    error = "Does not match"
                }
                state = state.copy(confirmPass = event.confirmPass, confirmPassError = error)
            }

            is SignupEvent.OnSubmit -> {
                signup()
            }

            is SignupEvent.OnLogin-> {
                login(event.context)
            }
        }
    }

    private fun signup() {
        val nameResult = validators.validateName.execute(state.name)
        val emailResult = validators.validateRequired.execute(state.email)
        val phoneResult = validators.validateRequired.execute(state.phone)
        val passwordResult = validators.validateRequired.execute(state.password)
        val confPasswordResult = validators.validateRequired.execute(state.confirmPass)
        val acceptTermsResult = validators.acceptTermsValidate.execute(state.acceptTerms)

        val hasErrors = listOf(
            emailResult,
            nameResult,
            passwordResult,
            acceptTermsResult
        ).any { !it.successful }
        if (hasErrors) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                emailError = emailResult.errorMessage,
                phoneError = phoneResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPassError = confPasswordResult.errorMessage,
                acceptTermsError = acceptTermsResult.errorMessage
            )
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            userRepo.signup(
                name = state.name,
                phone = state.phone,
                email = state.email,
                password = state.password
            ).collectLatest {
                loginSignupEventChannel.send(it)
            }
        }
    }

    private fun login(context: Context) {
        val phoneResult = validators.validateRequired.execute(state.phone)
        val passwordResult = validators.validateRequired.execute(state.password)

        val hasErrors = listOf(
            phoneResult,
            passwordResult,
        ).any { !it.successful }
        if (hasErrors) {
            state = state.copy(
                phoneError = phoneResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            userRepo.authenticate(phone = state.phone, password = state.password).collectLatest {
                if (it.status == Status.SUCCESS) {
                    context.putStr(authTokenKey, it.data?.token)
                }
                loginSignupEventChannel.send(it)
            }
        }
    }

//    private val _signupUser: MutableState<Resource<User>> = mutableStateOf(Resource.idle<String>())
//    val signupUser: State<Resource<User>> = _signupUser

//    fun signup(
//        name: String,
//        phone: String,
//        email: String,
//        password: String,
//        repeatPass: String
//    ) {
//        if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {
//            _signupUser.value = Resource.error("Name, Phone and Password are required fields", null)
//            return
//        }
//
//        if (repeatPass != password) {
//            _signupUser.value = Resource.error("Passwords does not match", null)
//            return
//        }
//
//        viewModelScope.launch(Dispatchers.IO) {
//            userRepo.signup(name, phone, email, password).collectLatest {
//                _signupUser.value = it
//            }
//        }
//    }

    fun isUserAuthenticated(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.getAuthenticatedUser().collectLatest { user ->
                Prefs.authUser.value = user
                state = state.copy(user = user)
                context.putStr(authTokenKey, user?.token)
            }
        }
    }

}
