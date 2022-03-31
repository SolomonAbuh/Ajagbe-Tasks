package com.example.ajagbetasksbeta.view.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ajagbetasksbeta.R
import com.example.ajagbetasksbeta.databinding.ErrorDialogBinding
import com.example.ajagbetasksbeta.databinding.FragmentSignBinding
import com.example.ajagbetasksbeta.viewmodel.AuthViewModel

class SignFragment : Fragment() {

    private lateinit var dialog: Dialog
    private lateinit var binding: FragmentSignBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        ).get(AuthViewModel::class.java)

        dialog = Dialog(requireActivity())

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

        val dbinding: ErrorDialogBinding =
            ErrorDialogBinding.inflate(LayoutInflater.from(requireActivity()))
        dialog.setContentView(dbinding.root)
        val back = ColorDrawable(Color.TRANSPARENT.hashCode())
        val inset = InsetDrawable(back, 35)
        dialog.window?.setBackgroundDrawable(inset)


        viewModel.validation(
            emailEt,
            emailLayout,
            passwordEt,
            passwordLayout,
        )

        binding.inSignBtn.setOnClickListener {

            viewModel.validation(
                emailEt,
                emailLayout,
                passwordEt,
                passwordLayout,
            )

            viewModel.checkForEmpty(
                emailEt,
                emailLayout,
                passwordEt,
                passwordLayout,
            )

            if (emailEt.text.toString().isNotEmpty() && passwordEt.text.toString().isNotEmpty()) {
                viewModel.login(emailEt.text.toString(), passwordEt.text.toString())
                viewModel.getFailedLogin().observe(requireActivity(), {
                    if (it == true) {
                        dbinding.errorText.text = viewModel.getFailedLoginText().value
                        displayErrorDialog()
                    }
                })
            }
            viewModel.checkForEmpty(emailEt, emailLayout, passwordEt, passwordLayout)

        }



        binding.inSignupText.setOnClickListener {
            findNavController().navigate(R.id.goToSignup)
        }
        return binding.root
    }

    private fun displayErrorDialog() {
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, 2500)
        dialog.show()

    }


}