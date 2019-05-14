package com.vessteros.groovie.app.models.entities

data class User(
    var id: Int?,
    var login: String?,
    var token: String?,
    var name: String?,
    var lastName: String?,
    var secondName: String?
)