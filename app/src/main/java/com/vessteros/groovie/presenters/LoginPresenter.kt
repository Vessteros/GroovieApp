package com.vessteros.groovie.presenters

import android.annotation.SuppressLint
import android.widget.Toast
import com.vessteros.groovie.activities.LoginActivity
import com.vessteros.groovie.apiDataSources.ApiRequestMethods
import com.vessteros.groovie.apiDataSources.Requests.*
import com.vessteros.groovie.entities.User
import com.vessteros.groovie.entities.issues.InputIssue
import com.vessteros.groovie.entities.issues.Issue
import com.vessteros.groovie.apiDataSources.Responses.Response as Resp
import com.vessteros.groovie.helpers.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginPresenter(private val view: LoginActivity) {

    @SuppressLint("SetTextI18n")
    fun loginAction() {
        val issue = checkIssues()

        if (issue == null) sendAndParseRequest() else view.issue(issue)
    }

    private fun sendAndParseRequest() {
        val request = Request(
            BaseServiceData(),
            AuthData(
                view.login.text.toString(),
                view.password.text.toString()
            )
        )

        RetrofitClient
            .setBaseUrl()
            .create(ApiRequestMethods::class.java).apply {
                authorize(request)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.textView.text = it.toString()

//                        it.data?.let {
//                            User.apply {
//                                if (it.id != null) {
//                                    id = it.id
//                                }
//                                token = it.token
//                            }
//                        }
                    }, {
                        Toast.makeText(view, it.toString(), Toast.LENGTH_LONG).show()
                    })
            }
    }

    private fun checkIssues(): InputIssue? = when {
        view.login.text.isEmpty() -> {
            val issue = InputIssue.EmptyLoginField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.login.setTextColor(this.textColor)
                    view.login.setBackgroundColor(this.inputColor)
                }
            }

            issue
        }

        view.password.text.isEmpty() -> {
            val issue = InputIssue.EmptyPasswordField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.password.setTextColor(this.textColor)
                    view.password.setBackgroundColor(this.inputColor)
                }
            }

            issue
        }

        else -> null
    }
}