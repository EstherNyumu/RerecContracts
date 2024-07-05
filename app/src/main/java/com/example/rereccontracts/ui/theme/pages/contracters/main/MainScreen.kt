package com.example.rereccontracts.ui.theme.pages.contracters.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.R.drawable.rerec_logo
import com.example.rereccontracts.ui.theme.Orange
import com.example.rereccontracts.ui.theme.pages.data.AuthRepository
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_SIGNIN
import com.example.rereccontracts.ui.theme.pages.signin.SignInAdmin

@Composable
fun MainScreenContracter(navController: NavHostController) {
    Scaffold(
        topBar = {TopAppBar(modifier = Modifier,navController = navController) }
    ){paddingValues->
        Box(modifier = Modifier.padding(paddingValues)){
            SignInAdmin(rememberNavController())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar( modifier:Modifier,navController: NavHostController) {
    val context = LocalContext.current
    val authRepository = AuthRepository(navController, context)
    var logout by remember { mutableStateOf(false) }


    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Orange,
            titleContentColor = Color.White
        ) ,

        title = {
            Text(
                text = "REREC CONTRACTS",
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)


        },
        navigationIcon = {
            Image(painter = painterResource(rerec_logo), contentDescription = "logo",
                modifier = Modifier.width(100.dp))
        },
        actions = {
            logout=authRepository.isLoggedIn()
            IconButton(onClick = {
                if(authRepository.isLoggedIn()){
                    authRepository.logout()
                }
                else{
                    navController.navigate(ROUTE_SIGNIN)
                }
            },
                enabled = logout,
                colors = IconButtonDefaults.iconButtonColors( contentColor = Color.White)) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = "logout")
            }


        }
    )

}
