package com.vessteros.groovie.app.models.cases

data class LoginCase(val login: String?, val password: String?)

data class RegisterCase(val login: String?, val password: String?, val repassword: String?)

data class UpdaterCase(
    val name: String?,
    val lastName: String?,
    val login: String?,
    val currentPassword: String?,
    val newPassword: String?
)
