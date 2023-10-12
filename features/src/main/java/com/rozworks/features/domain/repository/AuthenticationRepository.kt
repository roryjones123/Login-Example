package com.rozworks.features.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun login(username: String, password: String): Flow<String>
    fun register(username: String, password: String, email: String): Flow<String>
    fun resetPassword(newPassword: String, token: String): Flow<String>
    fun forgotPassword(email: String): Flow<String>
    fun verifyResetCode(code: String, email: String): Flow<String>

}
