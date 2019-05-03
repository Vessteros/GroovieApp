package com.vessteros.groovie.entities.issues

interface Issue<I> {
    var executable: I.() -> Unit

    val message: Int

    val textColor: Int

    fun execute()
}