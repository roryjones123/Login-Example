package com.rozworks.features.presentation.login

sealed class LoginIntent {
    data class AttemptLogin(val username: String, val password: String) : LoginIntent()
    data class ForgotPasswordInput(val email: String) : LoginIntent()
    data class VerifyCode(val email: String, val code: String) : LoginIntent()
    data class AttemptResetPassword(val password: String, val token: String) : LoginIntent()
    data object RegisterClicked : LoginIntent()
}
