package com.rozworks.features.domain.usecase

import com.rozworks.features.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class VerifyCodeUseCase(private val authenticationRepository: AuthenticationRepository) {
    operator fun invoke(code: String, email: String): Flow<Result<String>> {
        return authenticationRepository
            .verifyResetCode(code, email)
            .map { Result.success(it) }
            .catch {
                emit(Result.failure(VerifyCodeError.ConnectionError(it.message ?: "Unknown connection error occurred")))
            }
    }

    sealed class VerifyCodeError(reason: String) : Exception(reason) {
        class ConnectionError(reason: String) : VerifyCodeError(reason)
    }
}