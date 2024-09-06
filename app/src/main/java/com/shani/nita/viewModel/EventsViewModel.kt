package com.shani.nita.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shani.nita.Models.EventsModel
import com.shani.nita.utils.Constant.EVENTS
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import java.util.UUID

class EventsViewModel: ViewModel() {

    private val eventsRef = FirebaseFirestore.getInstance().collection(EVENTS)
    private val storageRef = FirebaseStorage.getInstance().reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted:LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted:LiveData<Boolean> = _isDeleted

    private val _eventsList = MutableLiveData<List<EventsModel>>()
    val eventsList:LiveData<List<EventsModel>> = _eventsList

    private val _loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading

    fun saveEvents(uri: Uri, description : String ){
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()
        val imageRef = storageRef.child("${EVENTS}/${randomUid}.jpg")
        val uploadTask = imageRef.putFile(uri)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                uploadEvents(it.toString(), randomUid, description)
            }
        }

    }

    private fun uploadEvents(imageUrl:String, docId:String, description: String){
        val map = mutableMapOf<String, Any>()
        map["imageUrl"] = imageUrl
        map["docId"] = docId
        map["description"] = description
        map["timestamp"] = FieldValue.serverTimestamp()

        eventsRef.document(docId).set(map).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener{
            _isPosted.postValue(false)
        }
    }

    fun getEvents(){

        _loading.postValue(true)
        eventsRef.orderBy("timestamp", Query.Direction.DESCENDING)
            .get().addOnSuccessListener {
            val list = mutableListOf<EventsModel>()
            for(item in it){
                list.add(item.toObject(EventsModel::class.java))

            }
            _eventsList.postValue(list)
                _loading.postValue(false)

        }
    }


    fun deleteEvents(eventsModel:EventsModel) {
        eventsRef.document(eventsModel.docId!!).delete().addOnSuccessListener {
            Firebase.storage.getReferenceFromUrl(eventsModel.imageUrl!!)
            _isDeleted.postValue(true)


        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }


    }

}