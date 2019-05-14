package com.vessteros.groovie.app.helpers.utils

import com.vessteros.groovie.app.helpers.issues.Issue

fun <I : Issue<I>> I?.isEmpty(): Boolean = this == null