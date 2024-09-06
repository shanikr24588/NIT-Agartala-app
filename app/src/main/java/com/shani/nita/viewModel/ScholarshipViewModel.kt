package com.shani.nita.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shani.nita.Models.ScholarshipModel
import com.shani.nita.utils.Constant.SCHOLARSHIP
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import java.util.UUID

class ScholarshipViewModel: ViewModel() {

    private val scholarshipRef = FirebaseFirestore.getInstance().collection(SCHOLARSHIP)
    private val storageRef = FirebaseStorage.getInstance().reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted: LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted:LiveData<Boolean> = _isDeleted

    private val _scholarshipList = MutableLiveData<List<ScholarshipModel>>()
    val scholarshipList:LiveData<List<ScholarshipModel>> = _scholarshipList

    private val _loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading

    fun saveScholarship(uri:Uri, name: String, eligibity:String, date:String, url:String ) {
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()
        val imageRef = storageRef.child("${SCHOLARSHIP}/${randomUid}.jpg")
        val uploadTask = imageRef.putFile(uri)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                uploadScholarship(it.toString(), randomUid, name, eligibity, date, url)
            }

        }



    }

    private fun uploadScholarship(imageUrl:String,
                                  docId:String,
                                  name:String,
                                  eligibity:String,
                                  date:String,
                                  url:String){
        val map = mutableMapOf<String, Any>()
        map["imageUrl"] = imageUrl
        map["docId"] = docId
        map["name"] = name
        map["eligibity"] = eligibity
        map["date"] = date
        map["url"] = url
        map["timestamp"] = FieldValue.serverTimestamp()

        scholarshipRef.document(docId).set(map).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener{
            _isPosted.postValue(false)
        }

    }

    fun getScholarship(){

        _loading.postValue(true)

        scholarshipRef.orderBy("timestamp", Query.Direction.DESCENDING)
            .get().addOnSuccessListener {
            val list = mutableListOf<ScholarshipModel>()
            for(item in it){
                list.add(item.toObject(ScholarshipModel::class.java))

            }
            _scholarshipList.postValue(list)
                _loading.postValue(false)

        }
    }

    fun deleteScholarship(scholarshipModel:ScholarshipModel) {
        scholarshipRef.document(scholarshipModel.docId!!).delete().addOnSuccessListener {
            Firebase.storage.getReferenceFromUrl(scholarshipModel.imageUrl!!)
            _isDeleted.postValue(true)


        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }


    }

}