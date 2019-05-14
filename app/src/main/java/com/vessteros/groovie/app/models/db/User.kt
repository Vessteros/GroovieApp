package com.vessteros.groovie.app.models.db

import io.realm.RealmObject

open class User: RealmObject() {
    /**
     * Идентификатор юзера с бэка
     */
    var id: Int = 0

    /**
     * Почта
     */
    var login: String = ""

    /**
     * Токен авторизации
     */
    var token: String? = null

    /**
     * Имя
     */
    var name: String? = null

    /**
     * Фамилия
     */
    var lastName: String? = null

    /**
     * Отчество
     */
    var secondName: String? = null

    /**
     * Одним прриложением могут пользоваться несколько пользователей в разное время на одном телефоне
     * При каждом выходе из приложения / неудачной авторизации на бэке проставляется false
     */
    var isActive: Boolean = false

//    override fun toString(): String {
//        return "GUser: {id: $id, token: $token, login: $login, name: $name, lastName: $lastName, isActive: $isActive}"
//    }
}