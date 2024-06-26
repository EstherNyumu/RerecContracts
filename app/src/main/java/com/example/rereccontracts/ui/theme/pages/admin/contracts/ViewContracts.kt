package com.example.rereccontracts.ui.theme.pages.admin.contracts

import android.app.AlertDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rereccontracts.ui.theme.Green
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.pages.admin.data.AuthRepository
import com.example.rereccontracts.ui.theme.pages.admin.data.ContractsRepository
import com.example.rereccontracts.ui.theme.pages.admin.models.Contracts
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_ADD_CONTRACT
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_SIGNIN
import org.jetbrains.annotations.Contract
import java.time.LocalDate


@Composable
fun ViewContractsAdmin(navController:NavHostController) {
    val context = LocalContext.current
    val contractsRepository = ContractsRepository(navController, context)

    val emptyContractState = remember { mutableStateOf(Contracts()) }
    val emptyContractsListState = remember { mutableStateListOf<Contracts>() }
    val contracts = contractsRepository.viewContracts(emptyContractState, emptyContractsListState)


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Contracts",
            color = Green,
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(contracts) {
                ContractItem(
                    companyName = it.companyName,
                    services = it.services,
                    startDate = it.startDate,
                    endDate = it.endDate,
                    period = it.period,
                    contractId = it.contractId,
                    contractsRepository = contractsRepository
                )
            }

        }
    }


    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row {
//            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = {
                    val authRepository = AuthRepository(navController, context)
                    if (!(authRepository.isLoggedIn())) {
                        navController.navigate(ROUTE_SIGNIN)
                    } else {
                        navController.navigate(ROUTE_ADD_CONTRACT)
                    }
                },
                containerColor = Green,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }


}



@Composable
fun ContractItem(
    companyName: String,
    services: String,
    startDate: String,
    endDate: String,
    period:String,
    contractId: String,
    contractsRepository: ContractsRepository
) {
    var showDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
//            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        ){
            Row {
                Text(text = "Company Name:", color = Green,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = companyName, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Services:",color = Green,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = services, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Start Date:",color = Green,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = startDate, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "End Date:",color = Green,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = endDate, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Days remaining:",color = Green,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = period, modifier = Modifier.padding(5.dp))
            }
            Row {
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {

                    }, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Orange
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Icon"
                    )
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Orange
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "Delete Icon"
                    )
                }
            }
        }
        if (showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirm Termination") },
                text = { Text("Are you sure you want to terminate?") },
                confirmButton = {
                    Button(onClick = {
                        contractsRepository.terminateContract(contractId)
                        showDialog = false
                    },colors = ButtonDefaults.buttonColors(Orange)) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(Orange)) {
                        Text("No")
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

