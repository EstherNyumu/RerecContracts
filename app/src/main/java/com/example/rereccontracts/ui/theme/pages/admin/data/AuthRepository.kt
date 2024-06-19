package com.example.rereccontracts.ui.theme.pages.admin.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.rereccontracts.ui.theme.pages.admin.models.BottomBarScreen
import com.example.rereccontracts.ui.theme.pages.admin.models.Users
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_SIGNIN
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_SIGNUP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository(var navController: NavHostController, var context: Context) {
    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading...")
        progress.setMessage("Please wait...")
    }

    /*----Sign Up Logic---*/
    fun signUp( email: String, password: String) {
        try {
            progress.show()
            progress.dismiss()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    var userData = Users(email, password, mAuth.currentUser!!.uid)
                    var regRef = FirebaseDatabase.getInstance().getReference()
                        .child("Users" + mAuth.currentUser!!.uid)
                    regRef.setValue(userData).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Thank you $email for joining us!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(BottomBarScreen.Contracts.route)
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Error: Not successful or the user already exists",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(ROUTE_SIGNUP)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Kindly add all your details to signup.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /*----Log in logic---*/
    fun signIn(email: String, password: String) {
        try {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(BottomBarScreen.Contracts.route)
                } else {
                    Toast.makeText(
                        context,
                        "The account doesn't exist or the details entered are wrong.",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(ROUTE_SIGNIN)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Kindly add your details to login.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /*----Log Out Logic---*/
    fun logout() {
        mAuth.signOut()
        Toast.makeText(context, "You've been logged out", Toast.LENGTH_SHORT).show()
        navController.navigate(ROUTE_SIGNIN)
    }

    /*----Log in Restriction Logic---*/
    fun isLoggedIn(): Boolean = mAuth.currentUser != null

}