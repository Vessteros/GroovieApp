package com.vessteros.groovie.app.models.cloud.responses.data

class DataList {
    data class AuthData(
        val id: Int?,
        val login: String?,
        val password: String?,
        val token: String?,
        val name: String?,
        val lastName: String?,
        val secondName: String?
    ) : BaseData
}