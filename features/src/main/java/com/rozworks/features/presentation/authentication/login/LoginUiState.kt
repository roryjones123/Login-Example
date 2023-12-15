package com.rozworks.features.presentation.authentication.login

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class LoginUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccess: Boolean = false,
    val verifyCodeFailed: Boolean = false,
    val isPasswordChangeSuccess: Boolean = false,
    val forgotEmailEntered: Boolean = false,
    val forgotEmailCodeEntered: Boolean = false,
    val resetPasswordToken: String = "",
    val forgottenPasswordEmail: String = "",
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState()
        data class LoginError(val message: String) : PartialState()
        data class VerifyCodeError(val message: String) : PartialState()
        data class ForgotEmailEntered(val emailEntered: String) : PartialState()
        data class CodeVerifySuccess(val token: String) : PartialState()
        data object PasswordResetSuccess : PartialState()
        data object LoginSuccess : PartialState()
        data object Register : PartialState()
    }
}
