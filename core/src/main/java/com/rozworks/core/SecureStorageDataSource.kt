package com.rozworks.core

import android.content.SharedPreferences
import javax.inject.Inject

class SecureStorageDataSource @Inject constructor(
    private val encryptedSharedPreferences: SharedPreferences
) {
    fun storeToken(jwtToken: String) {
        encryptedSharedPreferences.edit().putString(JWT_TOKEN, jwtToken).apply()
    }

    fun getToken(): String? {
        return encryptedSharedPreferences.getString(JWT_TOKEN, null)
    }

    companion object {
        const val JWT_TOKEN = "jwt_token"
    }
}