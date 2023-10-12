package com.rozworks.features.domain.usecase

import com.rozworks.features.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ForgotPasswordUseCase(private val authenticationRepository: AuthenticationRepository) {
    operator fun invoke(email: String): Flow<Result<String>> {
        return authenticationRepository
            .forgotPassword(email)
            .map { Result.success(it) }
            .catch {
                emit(Result.failure(ForgotPasswordError.ConnectionError(it.message ?: "Unknown connection error occurred")))
            }
    }

    sealed class ForgotPasswordError(reason: String) : Exception(reason) {
        class ConnectionError(reason: String) : ForgotPasswordError(reason)
    }
}