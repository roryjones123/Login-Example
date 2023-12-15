package com.rozworks.features.domain.usecase

import com.rozworks.core.SecureStorageDataSource
import com.rozworks.features.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LoginUseCase(
    private val authenticationRepository: AuthenticationRepository,
    private val secureStorageDataSource: SecureStorageDataSource
) {
    operator fun invoke(username: String, password: String): Flow<Result<String>> {
        return authenticationRepository
            .login(username, password)
            .map {
                secureStorageDataSource.storeToken(it)
                Result.success(it)
            }
            .catch {
                emit(Result.failure(LoginError.ConnectionError(it.message ?: "Unknown connection error occurred")))
            }
    }

    sealed class LoginError(reason: String) : Exception(reason) {
        class ConnectionError(reason: String) : LoginError(reason)
    }
}
