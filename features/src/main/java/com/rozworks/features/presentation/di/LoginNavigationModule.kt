package com.rozworks.features.presentation.di

import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.getstarted.GetStartedNavigationFactory
import com.rozworks.features.presentation.login.LoginNavigationFactory
import com.rozworks.features.presentation.register.RegisterNavigationFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AuthenticationModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindLoginNavigationFactory(factory: LoginNavigationFactory): NavigationFactory

    @Singleton
    @Binds
    @IntoSet
    fun bindGetStartedNavigationFactory(factory: GetStartedNavigationFactory): NavigationFactory

    @Singleton
    @Binds
    @IntoSet
    fun bindRegisterNavigationFactory(factory: RegisterNavigationFactory): NavigationFactory
}