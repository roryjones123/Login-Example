package com.rozworks.features.presentation.navbarscreens.home

sealed class HomeIntent {
    data class AttemptLogin(val username: String, val password: String) : HomeIntent()
}
