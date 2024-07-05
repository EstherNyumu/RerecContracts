 package com.example.rereccontracts

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.rereccontracts.ui.theme.RerecContractsTheme
import com.example.rereccontracts.ui.theme.pages.admin.main.MainScreenAdmin
import com.example.rereccontracts.ui.theme.pages.contracters.main.MainScreenContracter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

 class MainActivity : ComponentActivity() {
     @RequiresApi(Build.VERSION_CODES.O)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         enableEdgeToEdge()
         setContent {
             RerecContractsTheme {
                 val auth = FirebaseAuth.getInstance()
                 val currentUser = auth.currentUser
                 var userRole by remember { mutableStateOf<String?>(null) }
                 var context = LocalContext.current
                 val uid = currentUser?.uid

                 LaunchedEffect(uid) {
                     if (uid != null) {
                         val database = FirebaseDatabase.getInstance()
                         val userRef = database.getReference("Roles").child(uid)
                         userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                             override fun onDataChange(snapshot: DataSnapshot) {
                                 userRole = snapshot.getValue(String::class.java)
                             }

                             override fun onCancelled(error: DatabaseError) {
                                 // Handle error
                                 Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                             }
                         })
                     }
                 }
                 if(userRole == "Admin"){
                 MainScreenAdmin()
                 }
                 else if (userRole == "Contracter"){
                     MainScreenContracter(rememberNavController())
                 }
                 else{
                     Toast.makeText(
                         context,
                         "Not working",
                         Toast.LENGTH_SHORT
                     ).show()
                 }
                 MainScreenContracter(rememberNavController())
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