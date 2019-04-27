package com.vessteros.groovie.apiDataSources

import com.vessteros.groovie.apiDataSources.requests.AuthRequest
import com.vessteros.groovie.apiDataSources.responses.AuthResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Отправка запросов к серверу
 */
interface RetrofitRequests {
    /**
     * Авторизация пользователя из приложения
     */
    @POST("auth")
    fun authorize(
        @Body request: AuthRequest
    ) : Call<AuthResponse>
}