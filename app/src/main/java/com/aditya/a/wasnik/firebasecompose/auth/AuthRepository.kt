package com.aditya.a.wasnik.firebasecompose.auth

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {

                onSuccess()

            }.addOnFailureListener {

                onError(it.message ?: "Error")
            }

    }

    fun signup(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {

                onSuccess()

            }.addOnFailureListener {

                onError(it.message ?: "Error")
            }

    }
}