package com.rozworks.features.presentation.register

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class RegisterUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isRegisterSuccess: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState()
        data class ServerError(val message: String) : PartialState()
        data object Success : PartialState()
        data object Login : PartialState()
    }
}

