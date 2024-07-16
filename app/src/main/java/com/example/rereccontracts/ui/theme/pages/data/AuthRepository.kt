package com.example.rereccontracts.ui.theme.pages.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.rereccontracts.ui.theme.pages.models.BottomBarScreen
import com.example.rereccontracts.ui.theme.pages.models.Users
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_SIGNIN
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_SIGNUP
import com.example.rereccontracts.ui.theme.pages.models.Roles
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_HOME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository(var navController: NavHostController, var context: Context) {
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val progress: ProgressDialog

    init {
        progress = ProgressDialog(context)
        progress.setTitle("Loading...")
        progress.setMessage("Please wait...")
    }

    /*----Sign Up Logic---*/
    fun signUp( role: String, email: String, password: String) {
        try {
            progress.show()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
                progress.dismiss()
                if (task.isSuccessful) {
                    val currentUser = mAuth.currentUser?:return@addOnCompleteListener
                    val roleData = Roles(role, currentUser.uid)

                    FirebaseDatabase.getInstance().getReference()
                        .child("Roles").child(currentUser.uid).setValue(roleData)
                        .addOnCompleteListener{ roleTask->
                            if (roleTask.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Thank you $email for joining us!",
                                    Toast.LENGTH_SHORT
                                ).show()
//                                navController.navigate(BottomBarScreen.Contracts.route)

                                when(role){
                                    "Admin" ->{
                                        navController.navigate(BottomBarScreen.Contracts.route)

                                    }
                                    "Contracter" ->{
                                        navController.navigate(ROUTE_HOME)
                                    }
                                    else ->{
                                        navController.navigate(ROUTE_SIGNUP)
                                        Toast.makeText(
                                            context,
                                            "Contact Admin for assistance",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
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
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = mAuth.currentUser ?: return@addOnCompleteListener
                    FirebaseDatabase.getInstance().getReference()
                        .child("Roles").child(currentUser.uid).get()
                        .addOnCompleteListener { roleTask ->
                            if (roleTask.isSuccessful) {
                                val role = roleTask.result.getValue(Roles::class.java)?.role
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT)
                                    .show()
//                                navController.navigate(BottomBarScreen.Contracts.route)

                                when (role) {
                                    "Admin" -> {
                                        navController.navigate(BottomBarScreen.Contracts.route)
                                    }

                                    "Contracter" -> {
                                        navController.navigate(ROUTE_HOME)
                                    }

                                    else -> {
                                        navController.navigate(ROUTE_SIGNIN)
                                        Toast.makeText(
                                            context,
                                            "Contact Admin for assistance",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "The account doesn't exist or the details entered are wrong.",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_SIGNIN)
                            }
                        }
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

    fun resetPassword(email: String) {
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Password reset email.",
                        Toast.LENGTH_LONG
                    ).show()
                    // Show success message or navigate to password reset page
                    // Example: message = "Password reset email sent."
                    // Example: navigateToResetPasswordPage()
                } else {
                    // Show error message
                    task.exception?.message?.let { message ->
                        Toast.makeText(
                            context,
                            "Error: $message",
                            Toast.LENGTH_LONG
                        ).show()
                        // Example: message = "Error: $message"
                    } ?: run {
                        Toast.makeText(
                            context,
                            "Failed to send password reset email.",
                            Toast.LENGTH_LONG
                        ).show()
                        // Example: message = "Failed to send password reset email."
                    }
                }
            }
    }
}