package com.vessteros.groovie.apiDataSources

class Responses {
    data class Response<T> (val code: Int, val status: String, val data: T)

    data class AuthResponse(val id: Int, val token: String)
}