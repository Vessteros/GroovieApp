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
        @Body data: Request<BaseServiceInfo, AuthData>
    ) : Call<Response<AuthResponse, BaseProblem>>

    @POST("register")
    fun register(
        @Body data: RegisterData
    ) : Call<Response<AuthResponse, BaseProblem>>
}