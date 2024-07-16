package com.example.rereccontracts.ui.theme.pages.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.rereccontracts.ui.theme.pages.admin.contracts.AddContracts
import com.example.rereccontracts.ui.theme.pages.admin.contracts.EditContracts
import com.example.rereccontracts.ui.theme.pages.admin.contracts.ViewContractsAdmin
import com.example.rereccontracts.ui.theme.pages.admin.licences.AddLicense
import com.example.rereccontracts.ui.theme.pages.admin.licences.ViewLicenceAdmin
import com.example.rereccontracts.ui.theme.pages.contracters.help.HelpContracter
import com.example.rereccontracts.ui.theme.pages.contracters.home.HomeScreen
import com.example.rereccontracts.ui.theme.pages.data.AuthRepository
import com.example.rereccontracts.ui.theme.pages.models.BottomBarScreen
import com.example.rereccontracts.ui.theme.pages.signin.ForgotPasswordScreen
import com.example.rereccontracts.ui.theme.pages.signin.SignInAdmin
import com.example.rereccontracts.ui.theme.pages.signup.SignUpAdmin


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController){
    var authRepository = AuthRepository(navController, LocalContext.current)
    NavHost(navController = navController, startDestination = ROUTE_SIGNIN) {
        composable(route = BottomBarScreen.Contracts.route) {
            ViewContractsAdmin(navController)
        }
        composable(route = BottomBarScreen.Licences.route) {
            ViewLicenceAdmin(navController)
        }
        composable(route = ROUTE_ADD_CONTRACT) {
            AddContracts(navController)
        }
        composable(route = ROUTE_ADD_LICENSE) {
            AddLicense(navController)
        }
        composable(route = ROUTE_SIGNIN) {
            SignInAdmin(navController)
        }
        composable(route = ROUTE_SIGNUP) {
            SignUpAdmin(navController)
        }
        composable(route = "$ROUTE_EDIT_CONTRACTS/{id}") { passedData ->
            EditContracts(navController, passedData.arguments?.getString("id")!!)
        }
//        composable(route = ROUTE_HOME) {
//            HomeScreen(navController)
//        }
//        composable(route = ROUTE_HELP) {
//            HelpContracter(navController)
//        }
        composable(route = ROUTE_FORGOT_PASSWORD){
            ForgotPasswordScreen { email ->
                authRepository.resetPassword(email)
            }
        }
    }
}