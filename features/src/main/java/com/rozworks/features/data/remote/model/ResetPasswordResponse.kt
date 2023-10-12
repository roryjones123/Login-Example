package com.rozworks.features.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordResponse(
    val msg: String
)