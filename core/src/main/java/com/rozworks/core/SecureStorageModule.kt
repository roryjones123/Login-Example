package com.rozworks.core

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecureStorageModule {
    @Provides
    @Singleton
    fun provideSecureStorageDataSource(encryptedSharedPreferences: SharedPreferences): SecureStorageDataSource {
        return SecureStorageDataSource(encryptedSharedPreferences)
    }
}