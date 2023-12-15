package com.rozworks.features.presentation.authentication.getstarted

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
class GetStartedUiState : Parcelable {
    sealed class PartialState {
        data object Register : PartialState()
        data object Login : PartialState()
    }
}
