package com.rozworks.core.network

data class NetworkApiException(val statusCode: Int, val errorMessage: String) : Exception(errorMessage)
