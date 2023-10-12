package com.rozworks.features.presentation.di

import com.rozworks.features.presentation.login.LoginUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object LoginViewModel {

    @Provides
    fun provideInitialLoginUiState(): LoginUiState = LoginUiState()
}