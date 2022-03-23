package com.example.ajagbetasksbeta.view.activities

import android.animation.Animator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Property
import android.view.View
import android.view.animation.Animation
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.ajagbetasksbeta.R
import com.example.ajagbetasksbeta.adapters.OnBoardingAdapter
import com.example.ajagbetasksbeta.databinding.ActivityOnboardingBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager_two)

        val adapter = OnBoardingAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter


        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                progressIndicator()
                when (viewPager2.currentItem) {
                    2 -> {
                        binding.lastLayout.visibility = View.VISIBLE
                        binding.firstLayout.visibility = View.INVISIBLE
                    }
                    else -> {
                        binding.lastLayout.visibility = View.INVISIBLE
                        binding.firstLayout.visibility = View.VISIBLE
                    }
                }
            }
        })

        val nextBtn = findViewById<MaterialButton>(R.id.next_btn)


        nextBtn.setOnClickListener {
            viewPager2.currentItem++

        }

        binding.getStartedBtn.setOnClickListener {
            firstTimeUserCheck()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }


    }

    private fun progressIndicator() {
        when (binding.viewPagerTwo.currentItem) {
            0 -> {
                binding.circularProgress.progress = 33
                binding.progressText.text = "1"
            }
            1 -> {
                binding.circularProgress.progress = 66
                binding.progressText.text = "2"
            }
            2 -> {
                binding.circularProgress.progress = 100
                binding.progressText.text = "3"
            }
        }
    }

    private fun firstTimeUserCheck() {
        val sharedPreferences = getSharedPreferences("onBoard", MODE_PRIVATE)
        val editshared = sharedPreferences.edit()
        editshared.putBoolean("viewed", true).apply()
    }

}