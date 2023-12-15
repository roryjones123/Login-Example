package com.rozworks.features.data.repository

import com.rozworks.core.SecureStorageDataSource
import com.rozworks.features.data.remote.api.AuthenticationApi
import com.rozworks.features.data.remote.model.ForgotPasswordRequest
import com.rozworks.features.data.remote.model.LoginRequest
import com.rozworks.features.data.remote.model.RegisterRequest
import com.rozworks.features.data.remote.model.ResetPasswordRequest
import com.rozworks.features.data.remote.model.VerifyCodeRequest
import com.rozworks.features.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultAuthenticationRepository @Inject constructor(
    private val authenticationApi: AuthenticationApi
) : AuthenticationRepository {
    override fun login(username: String, password: String): Flow<String> =
        flow { emit(authenticationApi.login(LoginRequest(username, password)).accessToken) }

    override fun register(username: String, password: String, email: String): Flow<String> =
        flow { emit(authenticationApi.register(RegisterRequest(username, password, email)).msg) }

    override fun resetPassword(newPassword: String, token: String): Flow<String> =
        flow { emit(authenticationApi.resetPassword(ResetPasswordRequest(newPassword, token)).msg) }

    override fun forgotPassword(email: String): Flow<String> =
        flow { emit(authenticationApi.forgotPassword(ForgotPasswordRequest(email)).msg) }

    override fun verifyResetCode(code: String, email: String): Flow<String> =
        flow { emit(authenticationApi.verifyResetCode(VerifyCodeRequest(code, email)).token) }
}
