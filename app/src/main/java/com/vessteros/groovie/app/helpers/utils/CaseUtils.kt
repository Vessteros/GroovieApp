package com.vessteros.groovie.app.helpers.utils

import com.vessteros.groovie.app.models.cases.UpdaterCase

fun UpdaterCase.dataExists() = when {
    !this.name.isNullOrEmpty() -> true
    !this.lastName.isNullOrEmpty() -> true
    !this.login.isNullOrEmpty() -> true
    !this.newPassword.isNullOrEmpty() -> true
    !this.currentPassword.isNullOrEmpty() -> true
    else -> false
}