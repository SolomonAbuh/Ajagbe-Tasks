package com.example.ajagbetasksbeta.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.ajagbetasksbeta.view.fragments.OnboardOne
import com.example.ajagbetasksbeta.view.fragments.OnboardThree
import com.example.ajagbetasksbeta.view.fragments.OnboardTwo

class OnBoardingAdapter(fragmentManager: FragmentManager, fragmentLifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, fragmentLifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnboardOne()
            }
            1 -> {
                OnboardTwo()
            }
            2 -> {
                OnboardThree()
            }
            else -> {
                Fragment()
            }
        }
    }
}