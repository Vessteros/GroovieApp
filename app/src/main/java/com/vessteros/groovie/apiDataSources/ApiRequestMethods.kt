package com.vessteros.groovie.apiDataSources

import com.vessteros.groovie.apiDataSources.Requests.*
import com.vessteros.groovie.apiDataSources.Responses.*
import io.reactivex.Single
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
        @Body data: Request<BaseServiceData, AuthData>
    ): Single<Response<AuthResponse, BaseProblem>>

    @POST("register")
    fun register(
        @Body data: RegisterData
    ): Single<Response<AuthResponse, BaseProblem>>
}