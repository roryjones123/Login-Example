package com.rozworks.features.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.rozworks.features.data.remote.api.AuthenticationApi
import com.rozworks.features.data.repository.DefaultAuthenticationRepository
import com.rozworks.features.domain.repository.AuthenticationRepository
import com.rozworks.features.domain.usecase.ForgotPasswordUseCase
import com.rozworks.features.domain.usecase.LoginUseCase
import com.rozworks.features.domain.usecase.RegisterUseCase
import com.rozworks.features.domain.usecase.ResetPasswordUseCase
import com.rozworks.features.domain.usecase.VerifyCodeUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AuthenticationDomainModule {

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        retrofit: Retrofit,
    ): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }
    @Provides
    @Singleton
    fun provideLoginUseCase(
        authenticationRepository: AuthenticationRepository
    ): LoginUseCase {
        return LoginUseCase(authenticationRepository)
    }

    @Provides
    @Singleton
    fun provideForgotPasswordUseCase(
        authenticationRepository: AuthenticationRepository
    ): ForgotPasswordUseCase {
        return ForgotPasswordUseCase(authenticationRepository)
    }

    @Provides
    @Singleton
    fun provideResetPasswordUseCase(
        authenticationRepository: AuthenticationRepository
    ): ResetPasswordUseCase {
        return ResetPasswordUseCase(authenticationRepository)
    }

    @Provides
    @Singleton
    fun verifyCodeUseCase(
        authenticationRepository: AuthenticationRepository
    ): VerifyCodeUseCase {
        return VerifyCodeUseCase(authenticationRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(
        authenticationRepository: AuthenticationRepository
    ): RegisterUseCase {
        return RegisterUseCase(authenticationRepository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {
        @Binds
        @Singleton
        fun bindAuthenticationRepository(defaultAuthenticationRepository: DefaultAuthenticationRepository): AuthenticationRepository
    }
}
