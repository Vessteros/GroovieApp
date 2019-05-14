package com.vessteros.groovie.app.activities

import com.vessteros.groovie.app.helpers.issues.Issue

interface IRenderActivity {
    fun <I : Issue<I>> issue(issue: I) {
        issue.execute()
    }
}