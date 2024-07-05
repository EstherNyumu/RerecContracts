package com.example.rereccontracts.ui.theme.pages.contracters.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.R.drawable.rerec_logo
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.pages.admin.main.BottomBar
import com.example.rereccontracts.ui.theme.pages.admin.main.bottomBarAnimatedScroll
import com.example.rereccontracts.ui.theme.pages.navigation.AppNavHost
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_SIGNIN
import com.example.rereccontracts.ui.theme.pages.data.AuthRepository

@Composable
fun HomeScreen(navController: NavHostController) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {

        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}