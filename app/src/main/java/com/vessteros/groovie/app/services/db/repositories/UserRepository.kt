package com.vessteros.groovie.app.services.db.repositories

import com.vessteros.groovie.app.models.db.User
import com.vessteros.groovie.app.models.entities.User as EUser
import io.realm.Realm

class UserRepository {
    private val realm = Realm.getDefaultInstance()!!

    /**
     * Получить пользователя по id или null
     */
    fun getUserOrNull(id: Int): User? = realm.where(User::class.java)
        .equalTo("id", id)
        .findFirst()

    /**
     * Получение списка активных пользователей.
     * По идее одновременно может быть только один активный юзер, но если что-то где-то,
     * то выше обработаем эту ситуацию
     */
    fun getActiveUsers(): List<User>? {
        var userList: List<User>? = null

        realm.executeTransaction {
            userList = it
                .where(User::class.java)
                .equalTo("isActive", true)
                .findAll()
                .toList()
        }

        return userList
    }

    fun getActiveUser() = realm
            .where(User::class.java)
            .equalTo("isActive", true)
            .findFirst()

    /**
     * Деактивация всех пользователей
     * P.S. мало ли что я упустил при разработке позже.
     */
    fun deactivateUsers() {
        realm.executeTransaction {
            val userList = it
                .where(User::class.java)
                .findAll()

            userList.forEach {
                it.isActive = false
            }
        }
    }

    /**
     * Активация пользователя
     */
    fun enableUser(user: User): Unit = realm.executeTransaction {
        user.isActive = true
    }

    /**
     * Создание нового юзера
     */
    fun saveUser(euser: EUser) {
        realm.executeTransaction {
            it
                .createObject(User::class.java)
                .apply {
                    id = euser.id!! // в механизм работы с бд зашли только если id есть
                    login = euser.login!!
                    name = euser.name
                    token = euser.token
                    lastName = euser.lastName
                    isActive = true
                }
        }
    }
}