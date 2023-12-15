package com.rozworks.features.presentation

import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.authentication.getstarted.GetStartedNavigationFactory
import com.rozworks.features.presentation.authentication.login.LoginNavigationFactory
import com.rozworks.features.presentation.authentication.register.RegisterNavigationFactory
import com.rozworks.features.presentation.home.MyListsNavigationFactory
import com.rozworks.features.presentation.home.ProfileNavigationFactory
import com.rozworks.features.presentation.navbarscreens.home.HomeNavigationFactory
import com.rozworks.features.presentation.navbarscreens.search.SearchNavigationFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

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

    @Singleton
    @Binds
    @IntoSet
    fun bindHomeNavigationFactory(factory: HomeNavigationFactory): NavigationFactory

    @Singleton
    @Binds
    @IntoSet
    fun bindProfileNavigationFactory(factory: ProfileNavigationFactory): NavigationFactory

    @Singleton
    @Binds
    @IntoSet
    fun bindMyListsNavigationFactory(factory: MyListsNavigationFactory): NavigationFactory

    @Singleton
    @Binds
    @IntoSet
    fun bindSearchNavigationFactory(factory: SearchNavigationFactory): NavigationFactory
}