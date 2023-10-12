package com.rozworks.features.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class VerifyCodeRequest(
    val code: String,
    val email: String
)