package com.nita.nit_a.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nita.nit_a.Models.FacultyModel
import com.nita.nit_a.utils.Constant.FACULTY
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import java.util.UUID

class FacultyViewModel: ViewModel() {
    private val facultyRef = Firebase.firestore.collection(FACULTY)
    private val storageRef = Firebase.storage.reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted:LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted:LiveData<Boolean> = _isDeleted

    private val _facultyList = MutableLiveData<List<FacultyModel>>()
    val facultyList:LiveData<List<FacultyModel>> = _facultyList

    private val _categoryList = MutableLiveData<List<String>>()
    val categoryList:LiveData<List<String>> = _categoryList

    private val _loading =MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading

    fun saveFaculty(uri:Uri, name:String,
                    position:String, branch:String,
                    interest:String,email:String,
                    catName:String) {

        _isPosted.postValue(false)

        val randomUid = UUID.randomUUID().toString()
        val imageRef = storageRef.child("${FACULTY}/${randomUid}.jpg")
        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                uploadFaculty(it.toString(),randomUid,
                    name, position,
                    branch, interest,
                    email,catName
                )

            }
        }

    }

    private fun uploadFaculty(imageUrl:String,docId:String,
                              name:String,position:String,
                              branch:String,
                              interest:String,
                              email:String,
                              catName:String){
        val map = mutableMapOf<String, Any>()
        map["imageUrl"] = imageUrl
        map["docId"] = docId
        map["name"] = name
        map["position"] = position
        map["branch"] = branch
        map["interest"] = interest
        map["email"] = email
        map["catName"] = catName
        map["timestamp"] = FieldValue.serverTimestamp()

        facultyRef.document(catName).collection("teacher").document(docId).set(map).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener{
            _isPosted.postValue(false)
        }

    }



    fun uploadCategory(category:String ) {
        val map = mutableMapOf<String, Any>()
        map["catName"] = category
        map["timestamp"] = FieldValue.serverTimestamp()


        facultyRef.document(category).set(map).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener{
            _isPosted.postValue(false)
        }



    }
    fun getFaculty(catName:String){

        _loading.postValue(true)

        facultyRef.document(catName).collection("teacher")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get().addOnSuccessListener {
            val list = mutableListOf<FacultyModel>()

            for(i in it) {
                list.add(i.toObject(FacultyModel::class.java))
            }
            _facultyList.postValue(list)
                _loading.postValue(false)
        }
    }

    fun getCategory() {
        _loading.postValue(true)
        facultyRef.orderBy("timestamp", Query.Direction.ASCENDING)
            .get().addOnSuccessListener {
            val list = mutableListOf<String>()

            for (doc in it) {
                list.add(doc.get("catName").toString())
            }
            _categoryList.postValue(list)
            _loading.postValue(false)
        }
    }

    fun deleteFaculty(facultyModel:  FacultyModel) {


        facultyRef.document(facultyModel.catName!!).collection("teacher").document( facultyModel.docId!!).delete().addOnSuccessListener {
            Firebase.storage.getReferenceFromUrl(facultyModel.imageUrl!!)
            _isDeleted.postValue(true)
        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }
    }

    fun deleteCategory(category: String) {


        facultyRef.document(category).delete().addOnSuccessListener {

            _isDeleted.postValue(true)
        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }
    }




}