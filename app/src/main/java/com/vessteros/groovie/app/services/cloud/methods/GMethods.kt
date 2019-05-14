package com.vessteros.groovie.app.services.cloud.methods

import com.vessteros.groovie.app.models.cloud.requests.Requests.*
import com.vessteros.groovie.app.models.cloud.responses.Response
import com.vessteros.groovie.app.models.cloud.responses.data.DataList.*
import io.reactivex.Single
import retrofit2.http.*

interface GMethods {
    /**
     * Авторизация пользователя из приложения
     */
    @POST("auth")
    fun authorize(
        @Body data: AuthRequest
    ): Single<Response<AuthData>>

    /**
     * Регистрация пользователя в системе
     */
    @POST("register")
    fun register(
        @Body data: RegisterRequest
    ): Single<Response<AuthData>>
}