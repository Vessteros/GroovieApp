package com.vessteros.groovie.app.models.cloud.requests

class Requests {
    data class AuthRequest(val login: String, val password: String)

    data class RegisterRequest(val login: String, val password: String, val rePass: String)
}