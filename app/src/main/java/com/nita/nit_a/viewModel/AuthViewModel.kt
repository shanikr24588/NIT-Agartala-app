package com.nita.nit_a.viewModel


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.EmailAuthProvider
import com.nita.nit_a.Models.AuthModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class AuthViewModel: ViewModel() {




    private val _editenrollment = MutableLiveData<String?>()
    var editenrollment: LiveData<String?> = _editenrollment

    private val _editemail = MutableLiveData<String?>()
    var editemail: LiveData<String?> = _editemail

    val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    val userRef = db.collection("Users")

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> = _firebaseUser

    private val _isPosted = MutableLiveData<Boolean>(false)
    val isPosted: LiveData<Boolean> = _isPosted

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading



    init {
        _firebaseUser.value = auth.currentUser
    }




    fun register(context: Context, enrollmentNo: String, email: String, password: String) {
        _isLoading.postValue(true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context.mainExecutor) { task ->
                _isLoading.postValue(false)
                if (task.isSuccessful) {
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnSuccessListener(context.mainExecutor) {
                            // Do not sign in the user here. Instead, display a message asking them to verify their email.
                            showToast(context, "Please Verify Your Email")
                            saveUserData(enrollmentNo, email)
                        }?.addOnFailureListener(context.mainExecutor) {
                            showToast(context, "Something Went Wrong")
                        }
                } else {
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthUserCollisionException) {
                        // Sign in the user with the email and password
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(context.mainExecutor) { signInTask ->
                                if (signInTask.isSuccessful) {
                                    val user = auth.currentUser
                                    if (user?.isEmailVerified == false) {
                                        // Send the verification email again
                                        user.sendEmailVerification()
                                            ?.addOnSuccessListener(context.mainExecutor) {
                                                showToast(context, "Please Verify Your Email")
                                            }?.addOnFailureListener(context.mainExecutor) {
                                                showToast(context, "Something Went Wrong")
                                            }
                                    } else {
                                        showToast(context, "Account Already Exist")
                                    }
                                } else {
                                    showToast(context, "An error occurred: ${signInTask.exception?.message}")
                                }
                            }
                    } catch (e: Exception) {
                        showToast(context, "An error occurred: ${e.message}")
                    }
                }
            }
    }

    fun login(context: Context, email: String, password: String) {
        _isLoading.postValue(true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context.mainExecutor) { task ->
                _isLoading.postValue(false)

                if (task.isSuccessful) {
                    val verification = auth.currentUser?.isEmailVerified
                    if (verification == true) {
                        _firebaseUser.postValue(auth.currentUser)

                        showToast(context, "You Are LoggedIn")

                    } else {
                        // If the user's email is not verified, do not sign them in. Instead, display a message asking them to verify their email.
                        showToast(context, "Please Verify Your Email")
                        auth.signOut()
                    }

                } else {
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidUserException) {
                        showToast(context, "User does not exist")
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        showToast(context, "Invalid credentials")
                    } catch (e: Exception) {
                        showToast(context, "An error occurred: ${e.message}")
                    }
                }
            }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }




    private fun saveUserData(enrollmentNo : String, email:String ){


        val userId =  auth.currentUser?.uid

        val user =  mutableMapOf<String, Any>()


        user["enrollmentNo"] = enrollmentNo

        user["email"] = email

        userRef.document(userId!!).set(user).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener{
            _isPosted.postValue(false)
        }

    }

    fun forgotPassword(context:Context, email:String){
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            Toast.makeText(context, "Check Your Email", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(context, "Verify Your Email", Toast.LENGTH_SHORT).show()
        }
    }






    fun getUserData(){
        userRef.document(auth.currentUser?.uid.toString()).get().addOnSuccessListener {
            if (it.exists()) {
                val user = it.toObject(AuthModel::class.java)
                if (user != null) {

                    _editenrollment.value = user.enrollmentNo


                    _editemail.value = user.email
                }
            }
        }
    }









    fun logOut(){
        _isLoading.postValue(true)
        auth.signOut()
        _firebaseUser.postValue(null)
        _isLoading.postValue(false)

    }
}
