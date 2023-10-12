package com.rozworks.features.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class VerifyCodeResponse(
    val token: String
)