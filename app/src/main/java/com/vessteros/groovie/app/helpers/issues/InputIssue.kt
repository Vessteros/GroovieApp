package com.vessteros.groovie.app.helpers.issues

import com.vessteros.groovie.R

open class InputIssue :
    Issue<InputIssue> {
    override lateinit var executable: InputIssue.() -> Unit

    override val message: Int = 0

    override val textColor: Int = R.color.red

    val inputColor: Int = R.color.red

    override fun execute() {
        executable()
    }

    object EmptyLoginField: InputIssue() {
        override val message: Int = R.string.loginFieldEmpty
    }

    object EmptyPasswordField: InputIssue() {
        override val message: Int = R.string.passwordFieldEmpty
    }

    object PasswordFieldsEmpty: InputIssue() {
        override val message: Int = R.string.passwordFieldNotEqual
    }

    object PasswordFieldsNotEqual: InputIssue() {
        override val message: Int = R.string.passwordFieldNotEqual
    }
}