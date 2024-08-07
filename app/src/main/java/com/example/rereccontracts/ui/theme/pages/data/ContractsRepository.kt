package com.example.rereccontracts.ui.theme.pages.data



import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.rereccontracts.ui.theme.pages.models.BottomBarScreen
import com.example.rereccontracts.ui.theme.pages.models.Contracts
import com.example.rereccontracts.ui.theme.pages.navigation.ROUTE_ADD_CONTRACT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.annotations.Contract

class ContractsRepository(var navController: NavHostController,var context: Context){
    var progress: ProgressDialog
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    init {
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }
    /*----Saving Data Logic---*/
    fun saveContracts(companyName:String,email:String,services:String,startDate:String,endDate:String,period:String){
        var id = System.currentTimeMillis().toString()
        var contractData= Contracts(companyName,email,services,startDate,endDate,period,id)
        var contractRef = FirebaseDatabase.getInstance().getReference().child("Contracts/$id")
        progress.show()

        contractRef.setValue(contractData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Contract added successfully!", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Contracts.route)
            }
            else {
                Toast.makeText(context, "ERROR: Try Again! ", Toast.LENGTH_SHORT).show()
                //   navController.navigate(ROUTE_ADD_CONTRACT)
            }
        }
    }
    /*----Viewing Data Logic---*/
    fun viewContracts(contract: MutableState<Contracts>, contracts: SnapshotStateList<Contracts>): SnapshotStateList<Contracts> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Contracts")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                contracts.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Contracts::class.java)
                    contract.value = value!!
                    contracts.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return contracts
    }
    fun viewContractsContracter(contract: MutableState<Contracts>, contracts: SnapshotStateList<Contracts>): SnapshotStateList<Contracts> {
        val currentUser = mAuth.currentUser
        val userEmail = currentUser?.email ?: return contracts
        val ref = FirebaseDatabase.getInstance().getReference().child("Contracts")
        val query = ref.orderByChild("email").equalTo(userEmail)

        progress.show()
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                contracts.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Contracts::class.java)
                    contract.value = value!!
                    contracts.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return contracts
    }

    /*----Updating Data Logic---*/
    fun updateContracts(companyName:String,email:String,services:String,startDate:String,endDate:String,period:String){
        var id = System.currentTimeMillis().toString()
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Contracts/$id")
        progress.show()
        var updateData = Contracts(companyName,email,services,startDate,endDate,period,id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Edited successful", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Contracts.route)
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    /*----Deleting Data Logic---*/
    fun terminateContract(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference().child("Contracts/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if(it.isSuccessful){
                Toast.makeText(context, "Contract Terminated", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Failed to Terminate Contract", Toast.LENGTH_SHORT).show()
            }
        }
    }
}