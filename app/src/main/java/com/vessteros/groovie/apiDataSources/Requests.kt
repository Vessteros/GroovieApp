package com.vessteros.groovie.apiDataSources

class Requests {
    data class AuthRequest(val login: String, val password: String)

    data class RegisterRequest(val login: String, val password: String, val rePass: String)
}