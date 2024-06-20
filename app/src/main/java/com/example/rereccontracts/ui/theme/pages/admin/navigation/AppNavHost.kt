package com.example.rereccontracts.ui.theme.pages.admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.rereccontracts.ui.theme.pages.admin.contracts.AddContracts
import com.example.rereccontracts.ui.theme.pages.admin.contracts.ViewContractsAdmin
import com.example.rereccontracts.ui.theme.pages.admin.licences.AddLicense
import com.example.rereccontracts.ui.theme.pages.admin.licences.ViewLicenceAdmin
import com.example.rereccontracts.ui.theme.pages.admin.models.BottomBarScreen
import com.example.rereccontracts.ui.theme.pages.admin.signin.SignInAdmin
import com.example.rereccontracts.ui.theme.pages.admin.signup.SignUpAdmin


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Contracts.route) {
        composable(route = BottomBarScreen.Contracts.route){
            ViewContractsAdmin(navController)
        }
        composable(route = BottomBarScreen.Licences.route){
            ViewLicenceAdmin(navController)
        }
        composable(route = ROUTE_ADD_CONTRACT){
            AddContracts(navController)
        }
        composable(route = ROUTE_ADD_LICENSE){
            AddLicense(navController)
        }
        composable(route = ROUTE_SIGNIN){
            SignInAdmin(navController)
        }
        composable(route = ROUTE_SIGNUP){
            SignUpAdmin(navController)
        }
    }
}