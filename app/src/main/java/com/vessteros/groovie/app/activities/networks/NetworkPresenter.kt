package com.vessteros.groovie.app.activities.networks

interface NetworkPresenter {
    fun findAuth(): Boolean

    fun auth(): Any
}