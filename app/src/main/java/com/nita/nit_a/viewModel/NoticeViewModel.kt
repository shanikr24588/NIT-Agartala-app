package com.nita.nit_a.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nita.nit_a.Models.NoticeModel
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

class NoticeViewModel:ViewModel() {

    private val noticeRef = FirebaseFirestore.getInstance().collection(NOTICE)
    private val storageRef = FirebaseStorage.getInstance().reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted:LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted:LiveData<Boolean> = _isDeleted

    private val _noticeList = MutableLiveData<List<NoticeModel>>()
    val noticeList:LiveData<List<NoticeModel>> = _noticeList

    private val _loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    @OptIn(DelicateCoroutinesApi::class)
    fun saveNotice(uri:Uri, title: String)  {
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()
        val pdfRef = storageRef.child("${NOTICE}/${randomUid}.pdf")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val uploadTask = pdfRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    pdfRef.downloadUrl.addOnSuccessListener {
                        uploadNotice(it.toString(), randomUid, title)
                    }
                }
            } catch (e: Exception) {
                 null
            }
        }
    }

    private fun uploadNotice(url:String, docId:String, title:String) {
        val map = mutableMapOf<String, Any>()
        map["url"] =  url
        map["docId"] = docId
        map["title"] = title
        map["timestamp"] = FieldValue.serverTimestamp()
        noticeRef.document(docId).set(map).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener{
            _isPosted.postValue(false)
        }
    }

    fun getNotice() {

        _loading.postValue(true)
        _isRefreshing.postValue(true)
        noticeRef.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .get().addOnSuccessListener {
            val list = mutableListOf<NoticeModel>()
            for(doc in it){
                list.add(doc.toObject(NoticeModel::class.java))
            }
            _noticeList.postValue(list)
                _loading.postValue(false)
                _isRefreshing.postValue(false)
        }
    }
    fun deleteNotice(noticeModel:NoticeModel) {
        noticeRef.document(noticeModel.docId!!).delete().addOnSuccessListener {
            Firebase.storage.getReferenceFromUrl(noticeModel.url!!).delete()
            _isDeleted.postValue(true)
        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }
    }
}