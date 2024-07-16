package com.example.rereccontracts.ui.theme.pages.contracters.home

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.ui.theme.Green
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.pages.data.AuthRepository
import com.example.rereccontracts.ui.theme.pages.data.ContractsRepository
import com.example.rereccontracts.ui.theme.pages.models.Contracts
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_ADD_CONTRACT
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_EDIT_CONTRACTS
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_HELP
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_SIGNIN

@Composable
fun HomeScreen(navController: NavHostController){
    val context = LocalContext.current
    val contractsRepository = ContractsRepository(navController, context)

    val emptyContractState = remember { mutableStateOf(Contracts()) }
    val emptyContractsListState = remember { mutableStateListOf<Contracts>() }
    val contracts = contractsRepository.viewContractsContracter(emptyContractState, emptyContractsListState)


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
                    email = it.email,
                    services = it.services,
                    startDate = it.startDate,
                    endDate = it.endDate,
                    period = it.period,
                    contractId = it.contractId,
                    contractsRepository = contractsRepository,
                    navController = navController
                )
            }

        }
    }


    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = {
                    val authRepository = AuthRepository(navController, context)
                    if (!(authRepository.isLoggedIn())) {
                        navController.navigate(ROUTE_SIGNIN)
                    } else {
                        navController.navigate(ROUTE_HELP)
                    }
                },
                containerColor = Green,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(Icons.Default.Help, contentDescription = "Add")
            }
        }
    }


}
@Composable
fun ContractItem(
    companyName: String,
    email: String,
    services: String,
    startDate: String,
    endDate: String,
    period:String,
    contractId: String,
    contractsRepository: ContractsRepository,
    navController:NavHostController
) {
    var showDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
//            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Row {
                Text(
                    text = "Company Name:",
                    color = Green,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = companyName, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(
                    text = "Email:",
                    color = Green,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = email, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(
                    text = "Services:",
                    color = Green,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = services, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(
                    text = "Start Date:",
                    color = Green,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = startDate, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(
                    text = "End Date:",
                    color = Green,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = endDate, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(
                    text = "Days remaining:",
                    color = Green,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = period, modifier = Modifier.padding(5.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Text(text = "Renew",
                    modifier = Modifier.clickable {  }
                        .padding(5.dp),
                    textDecoration = TextDecoration.Underline,
                    fontStyle = FontStyle.Italic,
                    color = Green)
                Spacer(modifier = Modifier.weight(2f))
                Text(text = "Terminate",
                    modifier = Modifier.clickable {  }
                        .padding(5.dp)
                        .weight(1f),
                    textDecoration = TextDecoration.Underline,
                    fontStyle = FontStyle.Italic,
                    color = Color.Red)

            }
            Row {
            }
        }
    }
}
@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}