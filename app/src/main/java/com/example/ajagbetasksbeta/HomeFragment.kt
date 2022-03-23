package com.example.ajagbetasksbeta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ajagbetasksbeta.databinding.FragmentHomeBinding
import com.example.ajagbetasksbeta.viewmodel.AuthViewModel


class HomeFragment : Fragment() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        ).get(AuthViewModel::class.java)

        viewModel.getLoggedStatus().observe(this, {
            if (it) {
                findNavController().navigate(R.id.action_homeFragment_to_signFragment)
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.userEmail.text = viewModel.getUserData().value?.email

        binding.signOutBtn.setOnClickListener {
            viewModel.signOut()

        }



        return binding.root
    }
}