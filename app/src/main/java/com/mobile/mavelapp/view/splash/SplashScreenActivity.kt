package com.mobile.mavelapp.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mobile.mavelapp.R
import com.mobile.mavelapp.view.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler()
    }

    fun handler() {
        Handler().postDelayed({
            goMain()
            finish()
        }, SPLASH_TIME_OUT.toLong())

    }

    fun goMain() {

        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val SPLASH_TIME_OUT = 500
    }
}
