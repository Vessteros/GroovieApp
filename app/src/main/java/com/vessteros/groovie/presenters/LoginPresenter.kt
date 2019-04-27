package com.vessteros.groovie.presenters

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.vessteros.groovie.apiDataSources.requests.AuthRequest
import com.vessteros.groovie.apiDataSources.responses.AuthResponse
import com.vessteros.groovie.helpers.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val view: AppCompatActivity) {
    fun loginAction() {
        val request = AuthRequest(view.login.toString(), view.password.toString())

        RetrofitClient.client.authorize(request).apply {
            enqueue(object : Callback<AuthResponse> {

                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    view.textView.text = response.body().toString()
                }

                @SuppressLint("SetTextI18n")
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    view.textView.text = "Fail: ${t.message}"
                }
            })
        }
    }
}