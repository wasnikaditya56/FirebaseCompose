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
}