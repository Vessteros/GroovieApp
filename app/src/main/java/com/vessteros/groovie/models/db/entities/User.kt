package com.vessteros.groovie.models.db.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject() {

    /**
     * Идентификатор юзера с бэка
     */
//    @PrimaryKey
    var id: Int = 0

    /**
     * Почта
     */
    var login: String = ""

    /**
     * Пароль
     */
    var password: String = ""

    /**
     * Токен авторизации
     */
    var token: String = ""

    /**
     * Имя
     */
    var name: String? = null

    /**
     * Фамилия
     */
    var lastName: String? = null

    /**
     * Одним прриложением могут пользоваться несколько пользователей в разное время на одном телефоне
     * При каждом выходе из приложения / неудачной авторизации на бэке проставляется false
     */
    var isActive: Boolean = false

    override fun toString(): String {
        return "GUser: {id: $id, token: $token, login: $login, name: $name, name: $lastName, name: $password, name: $isActive}"
    }
}