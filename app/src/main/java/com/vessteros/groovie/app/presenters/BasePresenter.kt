package com.vessteros.groovie.app.presenters

interface BasePresenter {
    interface BaseWorker<F> {
        var fragment: F?

        fun attach(fragment: F)

        fun update()
    }
}