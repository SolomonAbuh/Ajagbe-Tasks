package com.example.ajagbetasksbeta.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.ajagbetasksbeta.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val sharedPreferences = getSharedPreferences("onBoard", MODE_PRIVATE)
        val edit = sharedPreferences.getBoolean("viewed", false)
        if (!edit) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }, 1000)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }, 1000)
        }


    }
}