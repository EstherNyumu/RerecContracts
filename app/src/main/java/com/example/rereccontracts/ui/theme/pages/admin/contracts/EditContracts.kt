package com.example.rereccontracts.ui.theme.pages.admin.contracts


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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.ui.theme.Green
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.RerecContractsTheme
import com.example.rereccontracts.ui.theme.pages.data.ContractsRepository
import com.example.rereccontracts.ui.theme.pages.models.Contracts
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContracts(navController: NavHostController,id:String) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            var companyName1 by remember { mutableStateOf("")}
            var email1 by remember { mutableStateOf("") }
            var services1 by remember { mutableStateOf("") }
            var startDate1 by remember { mutableStateOf("") }
            var endDate1 by remember { mutableStateOf("") }
            var period1 by remember { mutableStateOf("") }
            var context = LocalContext.current
            val formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy")

            var currentDataRef = FirebaseDatabase.getInstance().getReference()
                .child("Contracts/$id")
            currentDataRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var contract = snapshot.getValue(Contracts::class.java)
                    companyName1 = contract!!.companyName
                    email1 = contract!!.email
                    services1 = contract!!.services
                    startDate1 = contract!!.startDate
                    endDate1 = contract!!.endDate
                    period1 = contract!!.period
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
            var companyName by remember { mutableStateOf(TextFieldValue(companyName1))}
            var email by remember { mutableStateOf(TextFieldValue(email1)) }
            var services by remember { mutableStateOf(TextFieldValue(services1)) }
            var startDate by remember { mutableStateOf(TextFieldValue(startDate1)) }
            var endDate by remember { mutableStateOf(TextFieldValue(endDate1)) }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Edit Contract",
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
                label = { Text(text = "Start Date (dd-MM-yyyy)",color = Orange,
                    fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = endDate,
                onValueChange = {endDate = it},
                label = { Text(text = "End Date (dd-MM-yyyy)",color = Orange,
                    fontStyle = FontStyle.Italic) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green
                ))
            Spacer(modifier = Modifier.height(20.dp))

            var result by remember { mutableStateOf("") }
//            Button(onClick = {
//                try {
//                    val start = LocalDate.parse(startDate, formatter)
//                    val end = LocalDate.parse(endDate, formatter)
////                    result = Period.between(start, end).toString()
//                    val period = ChronoUnit.DAYS.between(start, end)
//                    result = "Days between: $period"
//                } catch (e: Exception) {
//                    result = "Invalid date format"
//                }
//            }) {
//                Text("Calculate")
//            }
//            Text(text = result)
            Button(onClick = {
                try {
                    val start = LocalDate.parse(startDate1, formatter)
                    val end = LocalDate.parse(endDate1, formatter)
                    val difference = ChronoUnit.DAYS.between(start, end)
                    period1 = "$difference days"
                    val contractsRepository = ContractsRepository(navController,context )
                    contractsRepository.updateContracts(companyName1,email1,services1,startDate1,endDate1,period1)
                } catch (e: Exception) {
                    Toast.makeText(context, "Wrong date format or fill in all the details.", Toast.LENGTH_SHORT).show()
                }
            },
                colors = ButtonDefaults.buttonColors(Orange)) {
                Text(text = "Update")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun EditContractsPreview() {
    RerecContractsTheme {
        EditContracts(rememberNavController(), id = "" )
    }
}