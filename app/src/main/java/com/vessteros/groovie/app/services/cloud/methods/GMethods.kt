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

    /**
     * Проверка пароля
     */
    @POST("user/check/password")
    fun checkPassword(
        @Body data: AuthRequest
    ): Single<Response<PasswordValidationData>>

    /**
     * Сохранение данных профиля
     */
    @POST("user/profile/update")
    fun profileUpdate(
        @Body data: ProfileChangesRequest
    ): Single<Response<ProfileUpdateData>>

    /**
     * Получение пользователя по id
     */
    @GET("user/{id}/get")
    fun getUser(
        @Path("id") id: Int
    ): Single<Response<AuthData>>

    /**
     * Сохранение токена авторизации
     */
    @POST("user/network_token/set")
    fun setNetworkAccessToken(
        @Body data: NetworkAccessTokenSetRequest
    ): Single<Response<AccessTokenData>>

    /**
     * Получение токена сети по ее id
     */
    @POST("user/network_token/get")
    fun getNetworkAccessToken(
        @Body data: NetworkGetterRequest
    ): Single<Response<AccessTokenData>>

    /**
     * Получение списка подключенных социальных сетей к мастер-аккаунту
     */
    @GET("user/network_list")
    fun getConnectedNetworksList(): Single<Response<ConnectedNetworksList>>
}