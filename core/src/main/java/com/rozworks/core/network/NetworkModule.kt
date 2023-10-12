package com.rozworks.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rozworks.loginexample.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val INTERCEPTOR_LOGGING_NAME = "INTERCEPTOR_LOGGING"

    @Provides
    @Named(INTERCEPTOR_LOGGING_NAME)
    fun provideHttpLoggingInterceptor(): Interceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            noOpInterceptor()
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(INTERCEPTOR_LOGGING_NAME) loggingInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val request = chain.request()

                try {
                    val response = chain.proceed(request)

                    if (!response.isSuccessful) {
                        val errorBody = response.body?.string()
                        if(errorBody?.isNotEmpty() == true) {
                            val errorResponse: ErrorResponse = Json.decodeFromString(errorBody)
                            throw NetworkApiException(response.code, errorResponse.msg)
                        }
                    }

                    response
                } catch (e: Exception) {
                    val msg: String
                    var code = 999

                    when (e) {
                        is SocketTimeoutException -> {
                            msg = "Timeout - Please check your internet connection"
                        }

                        is NetworkApiException -> {
                            msg = e.errorMessage
                            code = e.statusCode
                        }

                        is UnknownHostException -> {
                            msg = "Unable to make a connection. Please check your internet"
                        }

                        is ConnectionShutdownException -> {
                            msg = "Connection shutdown. Please check your internet"
                        }

                        is IOException -> {
                            msg = "Server is unreachable, please try again later."
                        }

                        is IllegalStateException -> {
                            msg = "${e.message}"
                        }

                        else -> {
                            msg = "${e.message}"
                        }
                    }
                    Response.Builder()
                        .request(request)
                        .message(msg)
                        .protocol(Protocol.HTTP_1_1)
                        .code(code)
                        .body("{${e}}".toResponseBody(null))
                        .build()
                }
            }
            .apply {
                addNetworkInterceptor(loggingInterceptor)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val contentType = "application/json".toMediaType()

        return Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(BuildConfig.LOCAL_HOST_URL)
            .client(okHttpClient)
            .build()
    }

    private fun noOpInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request())
        }
    }
}
