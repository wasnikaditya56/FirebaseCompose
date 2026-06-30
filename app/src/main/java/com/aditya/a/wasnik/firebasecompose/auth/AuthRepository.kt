package com.aditya.a.wasnik.firebasecompose.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {

    private val auth by lazy { FirebaseAuth.getInstance()  }
    private val db by lazy { FirebaseFirestore.getInstance() }

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

//    fun signup(
//        email: String,
//        password: String,
//        onSuccess: () -> Unit,
//        onError: (String) -> Unit
//    ) {
//        auth.createUserWithEmailAndPassword(email,password)
//            .addOnSuccessListener {
//                onSuccess()
//            }.addOnFailureListener {
//                onError(it.message ?: "Error")
//            }
//    }


    fun signup(
        email: String,
        password: String,
     //   name: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                val uid = auth.currentUser!!.uid

                val user = hashMapOf(
                    "userId" to "00001",   // Replace later with auto-generated ID
                   // "name" to name,
                    "email" to email
                )

                db.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onError(it.message ?: "Failed to save user")
                    }
            }
            .addOnFailureListener {
                onError(it.message ?: "Signup failed")
            }
    }

    fun forgotPassword(
        email: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .sendPasswordResetEmail(email)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it.message ?: "Error")
            }
    }
}
