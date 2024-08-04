package com.nita.nit_a.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nita.nit_a.Models.CalenderModel
import com.nita.nit_a.utils.Constant.NOTICE
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class CalenderViewModel:ViewModel() {

    private val calenderRef = FirebaseFirestore.getInstance().collection( "Calender")
    private val storageRef = FirebaseStorage.getInstance().reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted:LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted:LiveData<Boolean> = _isDeleted

    private val _calender = MutableLiveData<CalenderModel>()
    val calender:LiveData< CalenderModel> = _calender

    private val _loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading

    @OptIn(DelicateCoroutinesApi::class)
    fun saveCalender(uri:Uri)  {
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()
        val pdfRef = storageRef.child("${NOTICE}/${randomUid}.pdf")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val uploadTask = pdfRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    pdfRef.downloadUrl.addOnSuccessListener {
                        uploadCalender(it.toString(), randomUid)
                    }
                }
            } catch (e: Exception) {
                 null
            }
        }
    }

    private fun uploadCalender(url:String, docId:String) {
        val map = mutableMapOf<String, Any>()
        map["url"] =  url
        map["docId"] = docId
        map["timestamp"] = FieldValue.serverTimestamp()
        calenderRef.document(docId).set(map).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener{
            _isPosted.postValue(false)
        }
    }

    fun getCalender() {
        _loading.postValue(true)
        calenderRef.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .get().addOnSuccessListener {
                if (it.documents.isNotEmpty()) {
                    val doc = it.documents[0]
                    val map = doc.data as Map<String, Any>
                    val url = map["url"] as String
                    val docId = map["docId"] as String
                    _calender.postValue(CalenderModel(url, docId))
                }
                _loading.postValue(false)
            }
    }

    fun deleteCalender(calenderModel:CalenderModel) {
        calenderRef.document(calenderModel.docId!!).delete().addOnSuccessListener {
            Firebase.storage.getReferenceFromUrl(calenderModel.url!!).delete()
            _isDeleted.postValue(true)
        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }
    }
}