package com.vessteros.groovie.presenters

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.vessteros.groovie.apiDataSources.Requests.*
import com.vessteros.groovie.apiDataSources.Responses.*
import com.vessteros.groovie.apiDataSources.Responses.Response as Resp
import com.vessteros.groovie.helpers.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val view: AppCompatActivity) {
    fun loginAction() {
        val request = AuthRequest(view.login.toString(), view.password.toString())

        RetrofitClient.client.authorize(request).apply {
            enqueue(object : Callback<Resp<AuthResponse>> {

                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<Resp<AuthResponse>>, response: Response<Resp<AuthResponse>>) {
                    view.textView.text = response.body().toString()
                }

                @SuppressLint("SetTextI18n")
                override fun onFailure(call: Call<Resp<AuthResponse>>, t: Throwable) {
                    view.textView.text = "Fail: ${t.message}"
                }
            })
        }
    }
}