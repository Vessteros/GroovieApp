package com.vessteros.groovie.apiDataSources

import com.vessteros.groovie.apiDataSources.Requests.*
import com.vessteros.groovie.apiDataSources.Responses.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Отправка запросов к серверу
 */
interface ApiRequestMethods {
    /**
     * Авторизация пользователя из приложения
     */
    @POST("auth")
    fun authorize(
        @Body request: AuthRequest
    ) : Call<Response<AuthResponse>>

    @POST("register")
    fun register(
        @Body request: RegisterRequest
    ) : Call<Response<AuthResponse>>
}