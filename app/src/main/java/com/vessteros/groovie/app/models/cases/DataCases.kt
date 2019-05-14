package com.vessteros.groovie.app.models.cases

data class LoginCase(val login: String?, val password: String?)

data class RegisterCase(val login: String?, val password: String?, val repassword: String?)
