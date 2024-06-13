package com.example.rereccontracts.ui.theme.pages.contracters.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignInContracters(modifier: Modifier = Modifier) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Welcome Back")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Sign in")
            OutlinedTextField(value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email") })
            Spacer(modifier = Modifier.height(20.dp))


            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = password,
                onValueChange = {email = it},
                label = { Text(text = "password") })
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Sign in")
            }

        }
    }
}

@Preview
@Composable
private fun SignInContractersPreview() {
    SignInContracters()
}