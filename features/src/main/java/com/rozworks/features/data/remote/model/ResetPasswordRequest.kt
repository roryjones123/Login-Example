package com.rozworks.features.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(
    @SerialName("new_password")
    val newPassword: String,
    @SerialName("reset_password_token")
    val resetPasswordToken: String
)