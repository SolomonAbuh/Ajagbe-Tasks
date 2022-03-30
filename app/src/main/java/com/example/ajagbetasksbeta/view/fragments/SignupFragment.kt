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
import com.example.ajagbetasksbeta.databinding.FragmentSignupBinding
import com.example.ajagbetasksbeta.viewmodel.AuthViewModel

class SignupFragment : Fragment() {
    private lateinit var dialog: Dialog
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        ).get(AuthViewModel::class.java)


        dialog = Dialog(requireActivity())

        viewModel.getUserData().observe(this,
            {
                if (it != null) {
                    findNavController().navigate(R.id.setupProfile)
                }
            })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val dbinding: ErrorDialogBinding =
            ErrorDialogBinding.inflate(LayoutInflater.from(requireActivity()))
        binding = FragmentSignupBinding.inflate(layoutInflater)
        val emailEt = binding.upEmailEt
        val emailLayout = binding.upEmailLayout
        val fullNameEt = binding.upFullnameEt
        val fullNameLayout = binding.upFullnameLayout
        val passwordEt = binding.upPasswordEt
        val passwordLayout = binding.upPasswordLayout
        val confirmPasswordEt = binding.upConfirmPasswordEt
        val confirmPasswordLayout = binding.upConfirmPasswordLayout


        dialog.setContentView(dbinding.root)
        val back = ColorDrawable(Color.TRANSPARENT.hashCode())
        val inset = InsetDrawable(back, 35)
        dialog.window?.setBackgroundDrawable(inset)



        viewModel.validation(
            fullNameEt,
            fullNameLayout,
            emailEt,
            emailLayout,
            passwordEt,
            passwordLayout,
            confirmPasswordEt,
            confirmPasswordLayout
        )


        binding.upSignBtn.setOnClickListener {

            viewModel.validation(
                fullNameEt,
                fullNameLayout,
                emailEt,
                emailLayout,
                passwordEt,
                passwordLayout,
                confirmPasswordEt,
                confirmPasswordLayout
            )


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

            if (viewModel.getFullNameValidated().value == true
                && viewModel.getPasswordValidated().value == true
                && viewModel.getEmailValidated().value == true
            ) {

                if (emailEt.text.toString().isNotEmpty() && passwordEt.text.toString()
                        .isNotEmpty()
                ) {
                    viewModel.register(emailEt.text.toString(), passwordEt.text.toString())
                    viewModel.getFailedRegister().observe(requireActivity(), {
                        if (it == true) {
                            dbinding.errorText.text = viewModel.getFailedRegisterText().value
                            displayErrorDialog()
                        }
                    })
                }
            }

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