package com.example.rereccontracts.ui.theme.pages.admin.licences

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.ui.theme.Green
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.RerecContractsTheme
import com.example.rereccontracts.ui.theme.pages.data.LicenceRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLicense(navController: NavHostController) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            var context = LocalContext.current
            var softwareName by remember { mutableStateOf("") }
            var vendorName by remember { mutableStateOf("") }
            var licenseType by remember { mutableStateOf("") }
            var cost by remember { mutableStateOf("") }
            var startDate by remember { mutableStateOf("") }
            var endDate by remember { mutableStateOf("") }
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            var period by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Add License",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = softwareName,
                onValueChange = {softwareName = it},
                label = { Text(text = "Software Name",color = Orange) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = vendorName,
                onValueChange = {vendorName= it},
                label = { Text(text = "vendorName",color = Orange) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = licenseType,
                onValueChange = {licenseType= it},
                label = { Text(text = "License Type",color = Orange) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = cost,
                onValueChange = {cost= it},
                label = { Text(text = "cost",color = Orange) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = startDate,
                onValueChange = {startDate = it},
                label = { Text(text = "Start Date (dd-MM-yyyy)",color = Orange) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = endDate,
                onValueChange = {endDate = it},
                label = { Text(text = "End Date (dd-MM-yyyy)",color = Orange) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                try{
                    var start = LocalDate.parse(startDate,formatter)
                    var end = LocalDate.parse(endDate,formatter)
                    var difference  = ChronoUnit.DAYS.between(start,end)
                    period = "$difference days"
                    var licencesRepository = LicenceRepository(navController,context)
                    licencesRepository.saveLicence(softwareName,vendorName,licenseType,cost,startDate,endDate,period)
                }
                catch (e:Exception){
                    Toast.makeText(context, "Wrong date format or fill in all the details.", Toast.LENGTH_SHORT).show()
                }
            },
                colors = ButtonDefaults.buttonColors(Orange)) {
                Text(text = "Add")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun AddlicensePreview() {
    RerecContractsTheme {
        AddLicense(rememberNavController())
    }
}