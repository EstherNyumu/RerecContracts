package com.example.rereccontracts.ui.theme.pages.admin.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rereccontracts.ui.theme.RerecContractsTheme

@Composable
fun SignUpAdmin(modifier: Modifier = Modifier) {
    Surface {
        Column (modifier =Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            var email by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }
            var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email")})
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = password,
                onValueChange = {password = it},
                label = { Text(text = "Password")})
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = confirmPassword,
                onValueChange = {confirmPassword = it},
                label = { Text(text = "Confirm Password")})
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Preview
@Composable
private fun SignUpAdminPreview() {
    RerecContractsTheme {
        SignUpAdmin()
    }
}