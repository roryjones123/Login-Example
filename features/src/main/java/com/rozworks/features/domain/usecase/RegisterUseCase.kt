package com.rozworks.features.domain.usecase

import com.rozworks.features.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class RegisterUseCase(private val authenticationRepository: AuthenticationRepository) {
    operator fun invoke(username: String, password: String, email: String): Flow<Result<String>> {
        return authenticationRepository
            .register(username, password, email)
            .map { Result.success(it) }
            .catch {
                emit(Result.failure(RegisterError.ConnectionError(it.message ?: "Unknown connection error occurred")))
            }
    }

    sealed class RegisterError(reason: String) : Exception(reason) {
        class ConnectionError(reason: String) : RegisterError(reason)
    }
}