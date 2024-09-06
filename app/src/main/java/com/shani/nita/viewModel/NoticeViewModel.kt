package com.shani.nita.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shani.nita.Models.NoticeModel
import com.shani.nita.utils.Constant.NOTICE
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class NoticeViewModel : ViewModel() {

    private val noticeRef = FirebaseFirestore.getInstance().collection(NOTICE)
    private val storageRef = FirebaseStorage.getInstance().reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted: LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _noticeList = MutableLiveData<List<NoticeModel>>()
    val noticeList: LiveData<List<NoticeModel>> = _noticeList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun saveNotice(uri: Uri, title: String) {
        _isPosted.value = false
        val randomUid = UUID.randomUUID().toString()
        val pdfRef = storageRef.child("${NOTICE}/${randomUid}.pdf")
        viewModelScope.launch {
            try {
                val uploadTask = pdfRef.putFile(uri).await()
                val downloadUrl = pdfRef.downloadUrl.await()
                uploadNotice(downloadUrl.toString(), randomUid, title)
            } catch (e: Exception) {
                e.printStackTrace()
                _isPosted.value = false
            }
        }
    }

    private fun uploadNotice(url: String, docId: String, title: String) {
        val map = mutableMapOf<String, Any>()
        map["url"] = url
        map["docId"] = docId
        map["title"] = title
        map["timestamp"] = FieldValue.serverTimestamp()
        viewModelScope.launch {
            try {
                noticeRef.document(docId).set(map).await()
                _isPosted.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _isPosted.value = false
            }
        }
    }

    fun getNotice() {
        _loading.value = true
        _isRefreshing.value = true
        viewModelScope.launch {
            try {
                val snapshot = noticeRef.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING).get().await()
                val list = snapshot.mapNotNull { doc ->
                    doc.toObject(NoticeModel::class.java).apply {
                        isNew = !isSeen
                    }
                }
                _noticeList.value = list
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
                _isRefreshing.value = false
            }
        }
    }

    fun markNoticeAsSeen(noticeModel: NoticeModel) {
        viewModelScope.launch {
            try {
                noticeRef.document(noticeModel.docId!!).update("isSeen", true).await()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteNotice(noticeModel: NoticeModel) {
        viewModelScope.launch {
            try {
                noticeRef.document(noticeModel.docId!!).delete().await()
                Firebase.storage.getReferenceFromUrl(noticeModel.url!!).delete().await()
                _isDeleted.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _isDeleted.value = false
            }
        }
    }
}
