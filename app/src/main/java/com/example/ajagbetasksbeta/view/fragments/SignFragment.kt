package com.example.ajagbetasksbeta.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ajagbetasksbeta.R
import com.example.ajagbetasksbeta.databinding.FragmentSignBinding
import com.example.ajagbetasksbeta.viewmodel.AuthViewModel

class SignFragment : Fragment() {
    //    private var emailValidated = false
//    private var passwordVlidated = false
    private lateinit var binding: FragmentSignBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        ).get(AuthViewModel::class.java)

        viewModel.getUserData().observe(this, {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //
        binding = FragmentSignBinding.inflate(layoutInflater)
        val emailEt = binding.inEmailEt
        val emailLayout = binding.inEmailLayout
        val passwordEt = binding.inPasswordEt
        val passwordLayout = binding.inPasswordLayout

        binding.inSignBtn.setOnClickListener {
            if (emailEt.text.toString().isNotEmpty() && passwordEt.text.toString().isNotEmpty()) {
                viewModel.login(emailEt.text.toString(), passwordEt.text.toString())
            }
            viewModel.checkForEmpty(emailEt, emailLayout, passwordEt, passwordLayout)
        }


        binding.inSignupText.setOnClickListener {
            findNavController().navigate(R.id.goToSignup)
        }

        // validation()

        return binding.root
    }


    /*private fun validation(){
        binding.inEmailEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (Patterns.EMAIL_ADDRESS.matcher(binding.inEmailEt.text.toString()).matches()){
                    emailValidated = true
                    binding.inEmailLayout.error = null
                }else{
                    binding.inEmailLayout.error = "Not a valid email"
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.inPasswordEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.inPasswordEt.text!!.isNotEmpty()){
                    binding.inPasswordLayout.error = null
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun checkForEmpty(){
        if(binding.inEmailEt.text!!.isEmpty())binding.inEmailLayout.error = "Email field cannot me empty"
        else binding.inEmailLayout.error = null

        if (binding.inPasswordEt.text!!.isEmpty()) binding.inPasswordLayout.error = "Password field cannot be empty"
        else binding.inPasswordLayout.error = null
    }*/

}