package com.rozworks.features.presentation.navbarscreens.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import com.rozworks.core.BaseViewModel
import com.rozworks.core.navigation.NavigationManager
import com.rozworks.features.presentation.home.MyListsIntent
import com.rozworks.features.presentation.home.MyListsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    homeInitialState: MyListsUiState,
) : BaseViewModel<MyListsUiState, MyListsUiState.PartialState, MyListsIntent>(
    savedStateHandle,
    homeInitialState,
) {
    val username = mutableStateOf(TextFieldValue())
    val password = mutableStateOf(TextFieldValue())
    override fun mapIntents(intent: MyListsIntent): Flow<MyListsUiState.PartialState> = when (intent) {
        is MyListsIntent.AttemptLogin -> flowOf()
    }

    override fun reduceUiState(
        previousState: MyListsUiState,
        partialState: MyListsUiState.PartialState,
    ): MyListsUiState = when (partialState) {
        is MyListsUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
        )
    }
}