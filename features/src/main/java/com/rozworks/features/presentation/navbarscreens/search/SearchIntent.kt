package com.rozworks.features.presentation.navbarscreens.search

import com.rozworks.features.presentation.home.MyListsIntent

sealed class SearchIntent {
    data class AttemptLogin(val username: String, val password: String) : SearchIntent()
}
