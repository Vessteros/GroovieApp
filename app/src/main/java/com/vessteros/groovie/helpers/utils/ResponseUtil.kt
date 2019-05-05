package com.vessteros.groovie.helpers.utils

import com.vessteros.groovie.apiDataSources.Responses.*

fun <D, P> Response<D, P>.succeed(): Boolean = (status == "success" && code == 200)

fun <D, P> Response<D, P>.onDataExists(callable: Response<D, P>.() -> Unit): Unit = data?.let { callable() } ?: Unit

fun <D, P> Response<D, P>.onProblemExists(callable: Response<D, P>.() -> Unit): Unit = problem?.let { callable() } ?: Unit


fun AuthResponse.necessityReached(callable: (AuthResponse) -> Unit) = if (id != null && token != null) { callable(this) } else Unit