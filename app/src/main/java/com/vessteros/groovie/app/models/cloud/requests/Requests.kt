package com.vessteros.groovie.app.models.cloud.requests

class Requests {
    data class AuthRequest(val login: String, val password: String)

    data class RegisterRequest(val login: String, val password: String, val rePass: String)

    data class ProfileChangesRequest(
        val id: Int,
        val name: String?,
        val lastName: String?,
        val login: String?,
        val currentPassword: String?,
        val newPassword: String?
    )

    data class NetworkAccessTokenSetRequest(
        val api_user_id: Int,
        val network_id: String,
        val access_token: String,
        val expire_at: String
    )

    data class NetworkGetterRequest(
        val network_id: String
    )
}