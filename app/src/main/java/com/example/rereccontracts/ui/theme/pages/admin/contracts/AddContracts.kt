package com.example.rereccontracts.ui.theme.pages.admin.contracts

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
import com.example.rereccontracts.ui.theme.RerecContractsTheme

@Composable
fun AddContracts(modifier: Modifier = Modifier) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            var companyName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var services by remember { mutableStateOf("") }
            var startDate by remember { mutableStateOf("") }
            var endDate by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Add Contract")
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = companyName,
                onValueChange = {companyName = it},
                label = { Text(text = "companyName") })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = email,
                onValueChange = {email = it},
                label = { Text(text = "email") })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = services,
                onValueChange = {services= it},
                label = { Text(text = "services") })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = startDate,
                onValueChange = {startDate = it},
                label = { Text(text = "startDate") })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = endDate,
                onValueChange = {endDate = it},
                label = { Text(text = "endDate") })
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add")
            }
        }
    }
}

@Preview
@Composable
private fun AddContractsPreview() {
    RerecContractsTheme {
        AddContracts()
    }
}