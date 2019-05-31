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
}