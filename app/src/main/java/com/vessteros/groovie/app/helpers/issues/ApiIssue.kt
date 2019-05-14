package com.vessteros.groovie.app.helpers.issues

import com.vessteros.groovie.R

open class ApiIssue: Issue<ApiIssue> {
    override lateinit var executable: ApiIssue.() -> Unit

    override val message: Int = 0

    override val textColor: Int = 0

    override fun execute() {
        executable()
    }

    object EmptyAuthData: ApiIssue() {
        override val message: Int = R.string.emptyAuthData
    }
}