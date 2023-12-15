package com.rozworks.features.presentation.authentication.getstarted

sealed class GetStartedIntent {
    data object RegisterClick : GetStartedIntent()
    data object LoginClick : GetStartedIntent()
}
