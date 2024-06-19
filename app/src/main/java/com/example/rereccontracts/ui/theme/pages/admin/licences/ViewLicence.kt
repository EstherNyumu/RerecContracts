package com.example.rereccontracts.ui.theme.pages.admin.licences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun ViewLicenseAdmin(navController: NavHostController) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "View License")
        }
    }
}

@Preview
@Composable
private fun ViewContractsAdminPreview() {
    RerecContractsTheme {
        ViewLicenseAdmin(rememberNavController())
    }
}