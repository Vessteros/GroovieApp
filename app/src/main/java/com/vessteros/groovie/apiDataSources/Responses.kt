package com.vessteros.groovie.apiDataSources

class Responses {
    data class Response<D, P> (val code: Int, val status: String, val data: D?, val problem: P?)

    /******************************** Successes ***************************************/

    data class AuthResponse(val id: Int, val token: String)

    data class RegisterResponse(val id: Int, val token: String)

    /******************************** Successes ***************************************/

    /******************************** Failures ****************************************/

    data class BaseProblem(val message: String, val line: Int, val file: String)

    /******************************** Failures ****************************************/
}