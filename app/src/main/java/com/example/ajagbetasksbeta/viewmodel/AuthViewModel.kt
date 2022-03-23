package com.example.ajagbetasksbeta.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajagbetasksbeta.repository.AuthenticationRepository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser

class AuthViewModel() : ViewModel() {

    private val repository = AuthenticationRepository(Application())

    private val userData: MutableLiveData<FirebaseUser> = repository.getFireBaseMutableLiveData()
    private val loggedStatus: MutableLiveData<Boolean> =
        repository.getUserLoggedOutMutableLiveData()


    fun getUserData(): MutableLiveData<FirebaseUser> {
        return userData
    }

    fun getLoggedStatus(): MutableLiveData<Boolean> {
        return loggedStatus
    }


    fun register(email: String, password: String) {
        repository.registerUser(email, password)
    }


    fun checkForEmpty(
        fullName: TextInputEditText,
        fullNameLayout: TextInputLayout,
        email: TextInputEditText,
        emailLayout: TextInputLayout,
        password: TextInputEditText,
        passwordLayout: TextInputLayout,
        confirmPassword: TextInputEditText,
        confirmPasswordLayout: TextInputLayout
    ) {
        repository.checkForEmpty(
            fullName,
            fullNameLayout,
            email,
            emailLayout,
            password,
            passwordLayout,
            confirmPassword,
            confirmPasswordLayout
        )
    }

    fun login(email: String, password: String) {
        repository.login(email, password)
    }

    fun signOut() {
        repository.signOut()
    }


}