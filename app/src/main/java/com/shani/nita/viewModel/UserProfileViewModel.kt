package com.shani.nita.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.shani.nita.Models.UserProfileModel
import com.shani.nita.utils.SharePref
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserProfileViewModel:ViewModel(){

    private fun getUniqueUserId(context: Context): String {
        return SharePref.getUniqueUserId(context)
    }

    fun saveData(
        userProfileModel: UserProfileModel,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{
        val userProfileRef = Firebase.firestore.collection("UserDetails")
        //        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val userId =   getUniqueUserId(context)
        try {

            userProfileRef.document(userId).set(userProfileModel).addOnSuccessListener {
                SharePref.storeData(userProfileModel, context)
                Toast.makeText(context,   "Succesfully saved", Toast.LENGTH_SHORT).show()
            }

        } catch (e:Exception){
            Toast.makeText(context,  e.message, Toast.LENGTH_SHORT).show()
        }

    }


    fun getData(
        context: Context,
        data: (UserProfileModel) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        // Load data from cache initially
        val cachedData = SharePref.getUserData(context)
        if (cachedData != null) {
            data(cachedData)
        } else {
            // Show loading indicator while data is being fetched
            withContext(Dispatchers.Main) {
                // Show loading indicator
            }
        }

        // Use snapshot listener to listen for changes to user's document
        val userProfileRef = Firebase.firestore.collection("UserDetails")
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val userId =  getUniqueUserId(context)
        userProfileRef.document(userId).addSnapshotListener { document, error ->
            if (document != null && document.exists()) {
                val updatedUserData = document.toObject(UserProfileModel::class.java)
                // Update local cache with new data
                SharePref.storeData(updatedUserData!!, context)
                // Update UI with new data
                data(updatedUserData)
            } else {
                // Handle error or no data available
            }
        }
    }
}

