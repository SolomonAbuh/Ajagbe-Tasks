package com.example.ajagbetasksbeta.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajagbetasksbeta.repository.AuthenticationRepository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser

class AuthViewModel() : ViewModel() {

    private val repository = AuthenticationRepository()
    private val userData: MutableLiveData<FirebaseUser> = repository.getFireBaseMutableLiveData()
    private val loggedStatus: MutableLiveData<Boolean> =
        repository.getUserLoggedOutMutableLiveData()
    private val failedLogin: MutableLiveData<Boolean> = repository.getFailedLogin()
    private val failedLoginText: MutableLiveData<String> = repository.getFailedLoginText()
    private val failedRegister: MutableLiveData<Boolean> = repository.getFailedRegister()
    private val failedRegisterText: MutableLiveData<String> = repository.getFailedRegisterText()
    private val passwordValidated: MutableLiveData<Boolean> = repository.getPasswordValidated()
    private val fullNameValidated: MutableLiveData<Boolean> = repository.getFullNameValidated()
    private val emailValidated: MutableLiveData<Boolean> = repository.getEmailValidated()

    fun getEmailValidated(): MutableLiveData<Boolean> {
        return emailValidated
    }

    fun getPasswordValidated(): MutableLiveData<Boolean> {
        return passwordValidated
    }

    fun getFullNameValidated(): MutableLiveData<Boolean> {
        return fullNameValidated
    }


    fun getUserData(): MutableLiveData<FirebaseUser> {
        return userData
    }

    fun getLoggedStatus(): MutableLiveData<Boolean> {
        return loggedStatus
    }


    fun register(email: String, password: String) {
        repository.registerUser(email, password)
    }

    fun getFailedLogin(): MutableLiveData<Boolean> {
        return failedLogin
    }

    fun getFailedLoginText(): MutableLiveData<String> {
        return failedLoginText
    }

    fun getFailedRegister(): MutableLiveData<Boolean> {
        return failedRegister
    }

    fun getFailedRegisterText(): MutableLiveData<String> {
        return failedRegisterText
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


    fun checkForEmpty(
        email: TextInputEditText,
        emailLayout: TextInputLayout,
        password: TextInputEditText,
        passwordLayout: TextInputLayout,
    ) {
        repository.checkForEmpty(
            email,
            emailLayout,
            password,
            passwordLayout
        )
    }

    fun validation(
        email: TextInputEditText,
        emailLayout: TextInputLayout,
        password: TextInputEditText,
        passwordLayout: TextInputLayout,
    ) {
        repository.validation(
            email,
            emailLayout,
            password,
            passwordLayout
        )
    }

    fun validation(
        fullName: TextInputEditText,
        fullNameLayout: TextInputLayout,
        email: TextInputEditText,
        emailLayout: TextInputLayout,
        password: TextInputEditText,
        passwordLayout: TextInputLayout,
        confirmPassword: TextInputEditText,
        confirmPasswordLayout: TextInputLayout
    ) {
        repository.validation(
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