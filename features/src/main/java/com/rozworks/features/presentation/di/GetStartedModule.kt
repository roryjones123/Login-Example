package com.rozworks.features.presentation.di

import com.rozworks.features.presentation.getstarted.GetStartedUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object GetStartedViewModel {

    @Provides
    fun provideInitialGetStartedUiState(): GetStartedUiState = GetStartedUiState()
}