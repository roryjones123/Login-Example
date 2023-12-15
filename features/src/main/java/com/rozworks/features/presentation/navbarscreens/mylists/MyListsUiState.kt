package com.rozworks.features.presentation.home

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class MyListsUiState(
    val isLoading: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState()
    }
}
