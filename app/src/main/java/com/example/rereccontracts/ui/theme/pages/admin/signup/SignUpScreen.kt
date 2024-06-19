package com.example.rereccontracts.ui.theme.pages.admin.signup

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_SIGNIN
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_SIGNUP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpAdmin(navController: NavHostController) {
    Surface {
        Column (modifier =Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            val context = LocalContext.current
            var email by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }
            var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Sign Up",
                fontSize = 30.sp)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email", color = Orange, fontStyle = FontStyle.Italic) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = password,
                onValueChange = {password = it},
                label = { Text(text = "Password",color = Orange, fontStyle = FontStyle.Italic) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = confirmPassword,
                onValueChange = {confirmPassword = it},
                label = { Text(text = "Confirm Password",color = Orange, fontStyle = FontStyle.Italic) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                if (password.text != confirmPassword.text) {
                    Toast.makeText(
                        context,
                        "Passwords do not match",
                        Toast.LENGTH_LONG
                    ).show()
//                    navController.navigate(ROUTE_SIGNUP)
                }else{
                    var authRepository = AuthRepository(navController,context)
                    authRepository.signUp(email.text,password.text)
                    navController.navigate(BottomBarScreen.Contracts.route)
                }
            },
                colors = ButtonDefaults.buttonColors(Orange)) {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Have an account?Sign In", color = Green,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navController.navigate(ROUTE_SIGNIN)
                })//take us to sign in
        }
    }
}

@Preview
@Composable
private fun SignUpAdminPreview() {
    RerecContractsTheme {
        SignUpAdmin(rememberNavController())
    }
}