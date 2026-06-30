package com.aditya.a.wasnik.firebasecompose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aditya.a.wasnik.firebasecompose.auth.AuthViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Home",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.logout()
                navController.navigate("login"){
                    popUpTo(0)
                }
            }
        ) {
            Text("Logout")
        }
    }

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//
//        Text(
//            "Welcome Home",
//            fontSize = 30.sp
//        )
//
//    }

}