package com.rozworks.features.domain.usecase

import com.rozworks.features.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ResetPasswordUseCase(private val authenticationRepository: AuthenticationRepository) {
    operator fun invoke(newPassword: String, token: String): Flow<Result<String>> {
        return authenticationRepository
            .resetPassword(newPassword, token)
            .map { Result.success(it) }
            .catch {
                emit(Result.failure(ResetPasswordError.ConnectionError(it.message ?: "Unknown connection error occurred")))
            }
    }

    sealed class ResetPasswordError(reason: String) : Exception(reason) {
        class ConnectionError(reason: String) : ResetPasswordError(reason)
    }

}