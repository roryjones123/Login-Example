package com.rozworks.features.presentation.home

sealed class MyListsIntent {
    data class AttemptLogin(val username: String, val password: String) : MyListsIntent()
}
