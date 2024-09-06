package com.shani.nita.viewModel

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore

class AdminAuthViewModel:ViewModel() {

    val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _isAdminPosted = MutableLiveData<Boolean>(false)
    val isAdminPosted: LiveData<Boolean> = _isAdminPosted

    private val _isAdminLoading = MutableLiveData(false)
    val isAdminLoading: LiveData<Boolean> = _isAdminLoading

    private val _isAdminLoggedIn = MutableLiveData<Boolean>(false)
    val isAdminLoggedIn: LiveData<Boolean> = _isAdminLoggedIn



    fun adminLogin(context: Context, email: String, password: String) {
        _isAdminLoading.postValue(true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isAdminLoading.postValue(false)
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        val uid = user.uid
                        val ref = db.collection("users").document(uid)
                        ref.get()
                            .addOnCompleteListener { documentTask ->
                                if (documentTask.isSuccessful) {
                                    val document = documentTask.result
                                    if (document != null && document.exists()) {
                                        val role = document.getString("role")
                                        if (role != null && role == "admin") {
                                            _isAdminLoggedIn.postValue(true)
                                            showToast(context, "Admin logged in successfully")
                                        } else {
                                            showToast(context, "You are not an admin")
                                        }
                                    } else {
                                        showToast(context, "User not found")
                                    }
                                } else {
                                    showToast(context, "Failed to login")
                                }
                            }
                    }
                } else {
                    showToast(context, "Failed to login: ${task.exception?.message}")
                }
            }
    }

    private fun showToast(context: Context, message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun isAdminLoggedIn(): Boolean {
        return _isAdminLoggedIn.value!!
    }

     fun adminLogout(context:Context, navController: NavController) {
        auth.signOut()
        _isAdminLoggedIn.postValue(false)
        Toast.makeText(context, "Admin logged out successfully", Toast.LENGTH_SHORT).show()
         navController.navigate("home"){
             navController.popBackStack()
         }

    }
}