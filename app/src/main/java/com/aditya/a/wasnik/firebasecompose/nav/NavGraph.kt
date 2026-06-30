package com.aditya.a.wasnik.firebasecompose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditya.a.wasnik.firebasecompose.home.HomeScreen
import com.aditya.a.wasnik.firebasecompose.login.LoginScreen
import com.aditya.a.wasnik.firebasecompose.signup.SignupScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignupScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }
    }
}
