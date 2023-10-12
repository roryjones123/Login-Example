package com.rozworks.features.presentation.register

sealed class RegisterIntent {
    data class AttemptRegister(val username: String, val password: String, val email: String) : RegisterIntent()
    object SignInPress : RegisterIntent()
}
