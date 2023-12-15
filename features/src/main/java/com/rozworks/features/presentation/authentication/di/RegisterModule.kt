package com.rozworks.features.presentation.authentication.di

import com.rozworks.features.presentation.authentication.register.RegisterUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object RegisterViewModel {

    @Provides
    fun provideInitialRegisterUiState(): RegisterUiState = RegisterUiState()
}