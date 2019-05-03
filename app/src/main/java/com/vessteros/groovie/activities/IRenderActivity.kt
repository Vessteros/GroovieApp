package com.vessteros.groovie.activities

import com.vessteros.groovie.entities.issues.Issue

interface IRenderActivity {
    fun <I : Issue<I>> issue(issue: I)
}