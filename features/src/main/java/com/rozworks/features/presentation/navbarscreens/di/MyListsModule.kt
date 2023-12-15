package com.rozworks.features.presentation.navbarscreens.di

import com.rozworks.features.presentation.home.MyListsUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object MyListsViewModel {

    @Provides
    fun provideInitialMyListsUiState(): MyListsUiState = MyListsUiState()
}