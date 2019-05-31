package com.vessteros.groovie.app.activities.networks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.r0adkll.slidr.Slidr
import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.presenters.networks.VkPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback


class VkActivity : AppCompatActivity() {
    private val presenter = VkPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vk)
        Slidr.attach(this)

        init()
    }

    private fun init() {
        when {
            presenter.findAuth() -> {}
            else -> presenter.auth()
        }
    }

    fun moveOn(intent: Intent) = startActivity(intent)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let {
            VK.onActivityResult(requestCode, resultCode, data, object : VKAuthCallback {
                override fun onLogin(token: VKAccessToken) {
                    Toast.makeText(this@VkActivity, token.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onLoginFailed(errorCode: Int) {
                    val context = this@VkActivity

                    Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_LONG).show()

                    moveOn(Intent(context, MainActivity::class.java))
                }

            })
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
