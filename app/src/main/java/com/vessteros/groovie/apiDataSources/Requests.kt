package com.vessteros.groovie.apiDataSources

class Requests {
    data class Request<S, D>(val version: S?, val data: D)

    /******************************* ServicesInfo **************************************/

    data class BaseServiceData(val version: Int = 1)

    /******************************* ServicesInfo **************************************/

    /******************************* RequestData ***************************************/

    data class AuthData(val login: String, val password: String)

    data class RegisterData(val login: String, val password: String, val rePass: String)

    /******************************* RequestData ***************************************/
}