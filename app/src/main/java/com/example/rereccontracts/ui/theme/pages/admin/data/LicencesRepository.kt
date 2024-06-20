package com.example.rereccontracts.ui.theme.pages.admin.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.rereccontracts.ui.theme.pages.admin.models.BottomBarScreen
import com.example.rereccontracts.ui.theme.pages.admin.models.Contracts
import com.example.rereccontracts.ui.theme.pages.admin.models.Licences
import com.example.rereccontracts.ui.theme.pages.admin.navigation.ROUTE_ADD_CONTRACT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.annotations.Contract

class LicenceRepository(var navController: NavHostController,var context: Context){
    var progress: ProgressDialog
    init {
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }
    /*----Saving Data Logic---*/
    fun saveLicence(softwareName:String,vendorName:String,licenceType:String,cost:String,startDate:String,endDate:String){
        var id = System.currentTimeMillis().toString()
        var licenceData= Licences(softwareName,vendorName,licenceType,cost,startDate,endDate,id)
        var licenceRef = FirebaseDatabase.getInstance().getReference().child("Licences/$id")
        progress.show()

        licenceRef.setValue(licenceData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Licence added successfully!", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Licences.route)
            }
            else {
                Toast.makeText(context, "ERROR: Try Again! ", Toast.LENGTH_SHORT).show()
                //   navController.navigate(ROUTE_ADD_CONTRACT)
            }
        }
    }
    /*----Viewing Data Logic---*/
    fun viewLicences(licence: MutableState<Licences>, licences: SnapshotStateList<Licences>): SnapshotStateList<Licences> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Licences")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                licences.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Licences::class.java)
                    licence.value = value!!
                    licences.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return licences
    }
}


