package com.vessteros.groovie.app.models.cloud.responses

import com.vessteros.groovie.app.models.cloud.responses.data.BaseData

data class Response<D : BaseData>(
    val code: Int?,
    val status: String?,
    val data: D?
)