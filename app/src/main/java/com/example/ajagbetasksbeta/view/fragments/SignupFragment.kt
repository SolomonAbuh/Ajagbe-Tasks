package com.example.ajagbetasksbeta.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ajagbetasksbeta.R
import com.example.ajagbetasksbeta.databinding.FragmentSignupBinding
import com.example.ajagbetasksbeta.viewmodel.AuthViewModel

class SignupFragment : Fragment() {
    private var emailValidated = false
    private var fullNameValidated = false
    private var passwordValidated = false
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        ).get(AuthViewModel::class.java)


        viewModel.getUserData().observe(this,
            {
                if (it != null) {
                    findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                }
            })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignupBinding.inflate(layoutInflater)
        val emailEt = binding.upEmailEt
        val emailLayout = binding.upEmailLayout
        val fullNameEt = binding.upFullnameEt
        val fullNameLayout = binding.upFullnameLayout
        val passwordEt = binding.upPasswordEt
        val passwordLayout = binding.upPasswordLayout
        val confirmPasswordEt = binding.upConfirmPasswordEt
        val confirmPasswordLayout = binding.upConfirmPasswordLayout



        binding.upSignBtn.setOnClickListener {
            if (emailEt.text.toString().isNotEmpty() && passwordEt.text.toString().isNotEmpty()) {
                viewModel.register(emailEt.text.toString(), passwordEt.text.toString())
            }
            viewModel.checkForEmpty(
                fullNameEt,
                fullNameLayout,
                emailEt,
                emailLayout,
                passwordEt,
                passwordLayout,
                confirmPasswordEt,
                confirmPasswordLayout
            )
        }

        return binding.root
    }


    private fun validation() {
        binding.upFullnameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.upFullnameEt.text!!.isNotEmpty()) {
                    fullNameValidated = true
                    binding.upFullnameLayout.error = null
                } else {
                    fullNameValidated = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.upEmailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (Patterns.EMAIL_ADDRESS.matcher(binding.upEmailEt.text.toString()).matches()) {
                    emailValidated = true
                    binding.upEmailLayout.error = null
                } else {
                    binding.upEmailLayout.error = "Not a valid email"
                    emailValidated = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.upPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.upPasswordEt.text.toString().length <= 8) binding.upPasswordLayout.error =
                    "Password must be 8 characters or more"
                else binding.upPasswordLayout.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.upConfirmPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((binding.upPasswordEt.text.toString() != binding.upConfirmPasswordEt.text.toString())
                    && binding.upPasswordEt.text!!.length <= 8
                ) {
                    binding.upConfirmPasswordLayout.error = "Password Don't match"
                    passwordValidated = false
                } else {
                    binding.upConfirmPasswordLayout.error = null
                    passwordValidated = true
                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


}