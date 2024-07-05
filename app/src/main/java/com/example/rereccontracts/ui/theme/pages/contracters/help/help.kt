package com.example.rereccontracts.ui.theme.pages.contracters.help

import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.ui.theme.RerecContractsTheme

@Composable
fun HelpContracter(navController: NavHostController) {
Surface {
    Column (modifier=Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "About Us")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Rural Electrification and Renewable Energy Corporation (REREC) has an expanded mandate of spearheading Kenya’s green energy drive, in addition to implementing rural electrification projects. " +
                "We provide sustainable energy solutions for all through renewable energy and rural electrification for social economic transformation.",
            Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Q & A")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "What is the purpose of the app?")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Who is the app meant for?")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Can I receive notifications about contract deadlines?")
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Contact Information")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "info@rerec.co.ke")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "(+254) 709 193 000 / 3600")




    }
}
}

@Preview
@Composable
private fun HelpContracterPreview() {
RerecContractsTheme {
    HelpContracter(rememberNavController())
}
}