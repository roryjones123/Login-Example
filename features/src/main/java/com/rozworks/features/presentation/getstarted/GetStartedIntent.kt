package com.rozworks.features.presentation.getstarted

sealed class GetStartedIntent {
    data object RegisterClick : GetStartedIntent()
    data object LoginClick : GetStartedIntent()
}
