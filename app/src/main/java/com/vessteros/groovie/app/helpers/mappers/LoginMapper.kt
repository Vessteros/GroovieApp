package com.vessteros.groovie.app.helpers.mappers

import com.vessteros.groovie.app.models.cloud.responses.Response
import com.vessteros.groovie.app.models.cloud.responses.data.DataList.*
import com.vessteros.groovie.app.models.entities.User
import io.reactivex.Single

object LoginMapper {
    fun map(response: Response<AuthData>) = Single.just(
        User(
            id = response.data?.id,
            login = response.data?.login,
            token = response.data?.token,
            name = response.data?.name,
            lastName = response.data?.lastName,
            secondName = response.data?.secondName
        )
    )
}