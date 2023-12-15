package com.rozworks.features.presentation.navbarscreens.profile

sealed class ProfileIntent {
    data class AttemptLogin(val username: String, val password: String) : ProfileIntent()
}
