package com.example.ajagbetasksbeta.repository

import android.app.Application
import android.content.ContentValues.TAG
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository(val application: Application) {
    private var passwordValidated: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var fullNameValidated: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var emailValidated: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val failedLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val failedLoginText: MutableLiveData<String> = MutableLiveData<String>()
    private val failedRegister: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val failedRegisterText: MutableLiveData<String> = MutableLiveData<String>()

    private var firbaseMutableLiveData: MutableLiveData<FirebaseUser> =
        MutableLiveData<FirebaseUser>()
    private var userLoggedOutMutableLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    init {
        if (auth.currentUser != null) {
            firbaseMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firbaseMutableLiveData.postValue(auth.currentUser);
                userLoggedOutMutableLiveData.postValue(false)
                failedRegister.postValue(false)
            } else {
                failedRegisterText.postValue(it.exception?.message)
                failedRegister.postValue(true)
            }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firbaseMutableLiveData.postValue(auth.currentUser)
                failedLogin.postValue(false)
            } else {
                failedLoginText.postValue(it.exception?.message)
                Log.w(TAG, "signInWithEmail:failure", it.exception)
                failedLogin.postValue(true)

            }
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

    fun getEmailValidated(): MutableLiveData<Boolean> {
        return emailValidated
    }

    fun getPasswordValidated(): MutableLiveData<Boolean> {
        return passwordValidated
    }

    fun getFullNameValidated(): MutableLiveData<Boolean> {
        return fullNameValidated
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
        if (fullName.text!!.isEmpty()) {
            fullNameLayout.error = "Full name field cannot me empty"
            YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(3)
                .playOn(fullName);
        } else fullNameLayout.error = null

        if (email.text!!.isEmpty()) {
            emailLayout.error = "Email field cannot me empty"
            YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(3)
                .playOn(email);
        } else emailLayout.error = null

        if (password.text!!.isEmpty()) {
            passwordLayout.error = "Password field cannot be empty"
            YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(3)
                .playOn(password)
        } else passwordLayout.error = null

        if (confirmPassword.text!!.isEmpty()) {
            confirmPasswordLayout.error = "Password field cannot be empty"
            YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(3)
                .playOn(confirmPassword);
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
        if (email.text!!.isEmpty()) {
            emailLayout.error = "Email field cannot me empty"
            YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(3)
                .playOn(email);
        } else emailLayout.error = null

        if (password.text!!.isEmpty()) {
            passwordLayout.error = "Password field cannot be empty"
            YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(3)
                .playOn(password);
        } else passwordLayout.error = null
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
        //live text watcher to see if the values entered are valid
        fullName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    fullName.text!!.isNotEmpty() -> {
                        fullNameValidated.postValue(true)
                        fullNameLayout.error = null
                    }
                    else -> {
                        fullNameLayout.error = "Full name field cannot me empty"
                        fullNameValidated.postValue(false)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() -> {
                        emailValidated.postValue(true)
                        emailLayout.error = null
                    }
                    email.text!!.isEmpty() -> emailLayout.error =
                        "Email field cannot me empty"
                    else -> {
                        emailLayout.error = "Not a valid email"
                        emailValidated.postValue(false)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    password.toString().length <= 8 -> passwordLayout.error =
                        "Password must be 8 characters or more"
                    password.text!!.isEmpty() -> passwordLayout.error =
                        "Password field cannot be empty"
                    else -> passwordLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    password.text.toString() != confirmPassword.text.toString() -> {
                        confirmPasswordLayout.error = "Password Don't match"
                        passwordValidated.postValue(false)
                    }
                    confirmPassword.text!!.isEmpty() -> {
                        confirmPasswordLayout.error = "Password field cannot be empty"
                    }
                    else -> {
                        confirmPasswordLayout.error = null
                        passwordValidated.postValue(true)
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun validation(
        email: TextInputEditText,
        emailLayout: TextInputLayout,
        password: TextInputEditText,
        passwordLayout: TextInputLayout,
    ) {
        //live text watcher to see if the values entered are valid
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() -> {
                        emailValidated.postValue(true)
                        emailLayout.error = null
                    }
                    email.text!!.isEmpty() -> emailLayout.error =
                        "Email field cannot me empty"
                    else -> {
                        emailLayout.error = "Not a valid email"
                        emailValidated.postValue(false)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    password.toString().length <= 8 -> passwordLayout.error =
                        "Password must be 8 characters or more"
                    password.text!!.isEmpty() -> passwordLayout.error =
                        "Password field cannot be empty"
                    else -> passwordLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


}