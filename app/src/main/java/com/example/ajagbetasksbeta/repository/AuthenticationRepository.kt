package com.example.ajagbetasksbeta.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository(val application: Application) {
    private var firbaseMutableLiveData: MutableLiveData<FirebaseUser> =
        MutableLiveData<FirebaseUser>()
    private var userLoggedOutMutableLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    init {
        if (auth.currentUser != null) {
            firbaseMutableLiveData.postValue(auth.currentUser)
        }
    }


    //20g8899525   575445

    fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firbaseMutableLiveData.postValue(auth.currentUser);
                userLoggedOutMutableLiveData.postValue(false)
            } else {
                Toast.makeText(application, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firbaseMutableLiveData.postValue(auth.currentUser)
            } else Toast.makeText(application, "${it.exception?.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun signOut() {
        auth.signOut()
        userLoggedOutMutableLiveData.postValue(true)
    }

    fun getUserLoggedOutMutableLiveData(): MutableLiveData<Boolean> {
        return userLoggedOutMutableLiveData
    }

    fun getFireBaseMutableLiveData(): MutableLiveData<FirebaseUser> {
        return firbaseMutableLiveData
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
        if (fullName.text!!.isEmpty()) fullNameLayout.error =
            "Full name field cannot me empty"
        else fullNameLayout.error = null

        if (email.text!!.isEmpty()) emailLayout.error =
            "Email field cannot me empty"
        else emailLayout.error = null

        if (password.text!!.isEmpty()) passwordLayout.error =
            "Password field cannot be empty"
        else passwordLayout.error = null

        if (confirmPassword.text!!.isEmpty()) {
            confirmPasswordLayout.error = "Password field cannot be empty"
        } else {
            confirmPasswordLayout.error = null
        }
    }

    //overLoading the function for sign in page
    fun checkForEmpty(
        email: TextInputEditText,
        emailLayout: TextInputLayout,
        password: TextInputEditText,
        passwordLayout: TextInputLayout,
    ) {
        if (email.text!!.isEmpty()) emailLayout.error =
            "Email field cannot me empty"
        else emailLayout.error = null

        if (password.text!!.isEmpty()) passwordLayout.error =
            "Password field cannot be empty"
        else passwordLayout.error = null
    }

}