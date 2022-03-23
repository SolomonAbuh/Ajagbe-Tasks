package com.example.ajagbetasksbeta.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ajagbetasksbeta.R
import com.example.ajagbetasksbeta.databinding.FragmentSignBinding

class SignFragment : Fragment() {
    //    private var emailValidated = false
//    private var passwordVlidated = false
    private lateinit var binding: FragmentSignBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //
        binding = FragmentSignBinding.inflate(layoutInflater)

        binding.inSignBtn.setOnClickListener {
            //   checkForEmpty()
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