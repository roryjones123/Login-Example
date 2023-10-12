package com.rozworks.features.data.remote.api

import com.rozworks.features.data.remote.model.ForgotPasswordRequest
import com.rozworks.features.data.remote.model.ForgotPasswordResponse
import com.rozworks.features.data.remote.model.LoginRequest
import com.rozworks.features.data.remote.model.LoginResponse
import com.rozworks.features.data.remote.model.RegisterRequest
import com.rozworks.features.data.remote.model.RegisterResponse
import com.rozworks.features.data.remote.model.ResetPasswordRequest
import com.rozworks.features.data.remote.model.ResetPasswordResponse
import com.rozworks.features.data.remote.model.VerifyCodeRequest
import com.rozworks.features.data.remote.model.VerifyCodeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("/login")
    suspend fun login(@Body requestBody: LoginRequest): LoginResponse

    @POST("/register")
    suspend fun register(@Body requestBody: RegisterRequest): RegisterResponse

    @POST("/forgot_password")
    suspend fun forgotPassword(@Body requestBody: ForgotPasswordRequest): ForgotPasswordResponse

    @POST("/verify_reset_code")
    suspend fun verifyResetCode(@Body requestBody: VerifyCodeRequest): VerifyCodeResponse

    @POST("/reset_password")
    suspend fun resetPassword(@Body requestBody: ResetPasswordRequest): ResetPasswordResponse
}
