package com.example.rereccontracts.ui.theme.pages.admin.licences

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
fun AddLicense(modifier: Modifier = Modifier) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            var softwareName by remember { mutableStateOf("") }
            var vendorName by remember { mutableStateOf("") }
            var licenseDuration by remember { mutableStateOf("") }
            var cost by remember { mutableStateOf("") }
            var startDate by remember { mutableStateOf("") }
            var endDate by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Add License")
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = softwareName,
                onValueChange = {softwareName = it},
                label = { Text(text = "Software Name") })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = vendorName,
                onValueChange = {vendorName= it},
                label = { Text(text = "vendorName") })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = licenseDuration,
                onValueChange = {licenseDuration= it},
                label = { Text(text = "licenseDuration") })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = cost,
                onValueChange = {cost= it},
                label = { Text(text = "cost") })
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
private fun AddlicensePreview() {
    RerecContractsTheme {
        AddLicense()
    }
}