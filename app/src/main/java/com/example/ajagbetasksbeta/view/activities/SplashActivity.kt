package com.example.ajagbetasksbeta.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.core.content.edit
import com.example.ajagbetasksbeta.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        val sharedPreferences = getSharedPreferences("onBoard", MODE_PRIVATE)
        val edit = sharedPreferences.getBoolean("viewed", false)
        if (!edit) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }, 3000)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }, 3000)
        }


    }
}