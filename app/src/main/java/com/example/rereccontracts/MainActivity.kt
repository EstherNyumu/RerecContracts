 package com.example.rereccontracts


 import android.os.Build
 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.activity.enableEdgeToEdge
 import androidx.annotation.RequiresApi
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.runtime.setValue
 import androidx.compose.ui.tooling.preview.Preview
 import androidx.navigation.compose.rememberNavController
 import com.example.rereccontracts.ui.theme.RerecContractsTheme
 import com.example.rereccontracts.ui.theme.pages.main.MainScreenAdmin
 import com.example.rereccontracts.ui.theme.pages.models.BottomBarScreen
 import com.example.rereccontracts.ui.theme.pages.navigation.AppNavHost
 import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_HOME
 import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_SIGNIN


 class MainActivity : ComponentActivity() {
     @RequiresApi(Build.VERSION_CODES.O)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         enableEdgeToEdge()
         setContent {
             RerecContractsTheme {
                 MainScreenAdmin()
//                 val navController = rememberNavController()
//                 var startDestination by remember { mutableStateOf(ROUTE_SIGNIN) }
//
//                 val (isLoggedIn, userRole) = SessionManager.getLoginState(navController.context)
//                 startDestination = when {
//                     !isLoggedIn -> ROUTE_SIGNIN
//                     userRole == "admin" -> BottomBarScreen.Contracts.route
//                     else -> ROUTE_HOME
//                 }
//
//                 AppNavHost(navController = navController, startDestination = startDestination)

             }
             }
         }
     }


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RerecContractsTheme {
//        MainScreen()
    }
}