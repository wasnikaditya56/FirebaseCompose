package com.aditya.a.wasnik.firebasecompose.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aditya.a.wasnik.firebasecompose.auth.AuthViewModel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context: Context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Login",
            fontSize = 30.sp
        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            }
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                PasswordVisualTransformation(),

            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector =
                            if (passwordVisible)
                              Icons.Default.Visibility
                        else
                          Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(Modifier.height(20.dp))
        Button(

            onClick = {
                val error = viewModel.validateInput(email, password)

                if (error != null) {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    return@Button
                }

                viewModel.login(
                    email,
                    password
                ) {
                    navController.navigate("home")
                }
            },
            enabled = !viewModel.isLoading
        ) {
            if (viewModel.isLoading){
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Login")
            }

        }
        TextButton(
            onClick = {
                navController.navigate("signup")
            }
        ) {
            Text("Create Account")
        }
        Text(viewModel.message)
    }
}
