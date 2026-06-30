package com.aditya.a.wasnik.firebasecompose.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    var message by mutableStateOf("")
        private set

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        repository.login(
            email,
            password,
            onSuccess
        ) {
            message = it
        }
    }

    fun signup(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        repository.signup(
            email,
            password,
            onSuccess
        ) {
            message = it
        }
    }

    fun validateInput(email: String, password: String): String? {
        if(email.isBlank()){
            return "Email is required"
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Enter  valid email address"
        }

        if(password.isBlank()) {
            return "Password is required"
        }

        if(password.length < 8){
            return "Password must be at least 8 characters"
        }

        if(!password.any{it.isUpperCase()}){
            return "Password must contain at least one uppercase letter"
        }

        if(!password.any{it.isLowerCase()}) {
            return "Password must contain at least one lowercase letter"
        }

        if (!password.any{it.isDigit()}) {
            return "Password must contain at least one digit"
        }

        return null
    }
}
