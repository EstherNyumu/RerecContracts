package com.example.rereccontracts.ui.theme.pages.admin.signin

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.ui.theme.Green
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.RerecContractsTheme
import com.example.rereccontracts.ui.theme.pages.admin.data.AuthRepository
import com.example.rereccontracts.ui.theme.pages.admin.models.BottomBarScreen
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_SIGNUP


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInAdmin(navController: NavHostController) {
    var context = LocalContext.current
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Welcome Back!",
                fontSize = 30.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Sign In",
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email", color = Orange, fontStyle = FontStyle.Italic)},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = password,
                onValueChange = {password = it},
                label = { Text(text = "Password", color = Orange, fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                var authRepository = AuthRepository(navController,context)
                authRepository.signIn(email,password)
                navController.navigate(BottomBarScreen.Contracts.route)
            },
                colors = ButtonDefaults.buttonColors(Orange)){
                Text(text = "Sign in")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "No account? Sign In", color = Green,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navController.navigate(ROUTE_SIGNUP)
                })//take us to sign up
        }
    }

}
@Preview
@Composable
private fun SignInAdminPreview() {
    RerecContractsTheme {
        SignInAdmin(rememberNavController())
    }
}
