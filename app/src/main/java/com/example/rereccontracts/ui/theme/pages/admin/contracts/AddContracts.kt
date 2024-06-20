package com.example.rereccontracts.ui.theme.pages.admin.contracts

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.ui.theme.Green
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.RerecContractsTheme
import com.example.rereccontracts.ui.theme.pages.admin.data.ContractsRepository
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContracts(navController: NavHostController) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            var companyName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var services by remember { mutableStateOf("") }
            var startDate by rememberSaveable { mutableStateOf("") }
            var endDate by remember { mutableStateOf("") }
            var context = LocalContext.current
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                context,
                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    startDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                }, year, month, day
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Add Contract",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = companyName,
                onValueChange = {companyName = it},
                label = { Text(text = "Contractor Name",
                    fontStyle = FontStyle.Italic,color = Orange) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email",color = Orange,
                    fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = services,
                onValueChange = {services= it},
                label = { Text(text = "Services",color = Orange,
                    fontStyle = FontStyle.Italic) },
                modifier = Modifier.height(200.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = startDate,
                onValueChange = {startDate = it},
                label = { Text(text = "Start Date",color = Orange,
                    fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ),
                modifier = Modifier.clickable {
                    datePickerDialog.show()
                })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = endDate,
                onValueChange = {endDate = it},
                label = { Text(text = "End Date",color = Orange,
                    fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                val contractsRepository = ContractsRepository(navController,context )
                contractsRepository.saveContracts(companyName,email,services,startDate,endDate)
            },
                colors = ButtonDefaults.buttonColors(Orange)) {
                Text(text = "Add")
            }
        }
    }
}

@Preview
@Composable
private fun AddContractsPreview() {
    RerecContractsTheme {
        AddContracts(rememberNavController())
    }
}