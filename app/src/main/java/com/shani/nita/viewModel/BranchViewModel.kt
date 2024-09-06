package com.shani.nita.viewModel

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shani.nita.Models.BranchModel
import com.shani.nita.utils.Constant.DEPARTMENT_DOCS
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class BranchViewModel: ViewModel(){

    private val departmentRef = Firebase.firestore.collection(DEPARTMENT_DOCS)
    private val storageRef = FirebaseStorage.getInstance().reference

    private val _isPosted = MutableLiveData<Boolean>(false)
    val isPosted: LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _notesMap = MutableLiveData<MutableMap<String, List<BranchModel>>>()
    val notesMap: LiveData<MutableMap<String, List<BranchModel>>> = _notesMap

    private val _pyqMap = MutableLiveData<MutableMap<String, List<BranchModel>>>()
    val pyqMap: LiveData<MutableMap<String, List<BranchModel>>> = _pyqMap

    private val _pindocsMap = MutableLiveData<MutableMap<String, List<BranchModel>>>()
    val pindocsMap: LiveData<MutableMap<String, List<BranchModel>>> = _pindocsMap



    private val _notesList = MutableLiveData<List<BranchModel>>()
    val notesList:LiveData<List<BranchModel>> = _notesList

    private val _pyqList = MutableLiveData<List<BranchModel>>()
    val pyqList:LiveData<List<BranchModel>> = _pyqList

    private val _pindocsList = MutableLiveData<List<BranchModel>>()
    val pindocsList:LiveData<List<BranchModel>> = _pindocsList


    private val _branchList = MutableLiveData<List<String>>()
    val branchList: LiveData<List<String>> = _branchList

    private val _semesterList = MutableLiveData<List<String>>()
    val semesterList: LiveData<List<String>> = _semesterList

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading


    fun saveNotes(uri:Uri,
                     title:String,
                     branch:String,
                     semester:String
                      ){
        _isDeleted.postValue(false)
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()
        val pdfRef = storageRef.child("${DEPARTMENT_DOCS}/${randomUid}.pdf")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val uploadTask = pdfRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    pdfRef.downloadUrl.addOnSuccessListener {
                        uploadNotes(it.toString(),
                            randomUid,
                            title,
                            branch,
                            semester
                        )
                    }
                }
            }
            catch (e: Exception) {
                null
            }

        }



    }

    private fun uploadNotes(url: String,
                               docId:String,
                               title: String,
                               branch:String,
                               semester:String


    ) {

        val map = mapOf(

            "url" to url,
            "docId" to docId,
            "title" to title,
            "branch" to branch,
            "semester" to semester,
            "timestamp" to FieldValue.serverTimestamp()
            )

        // Create the structure MessMenu -> Hostel -> Days -> Items
        departmentRef.document(branch)
            .collection("Semester").document(semester)
            .collection("NOTES").document(docId).set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }

    fun savePyq(uri:Uri,
                  title:String,
                  branch:String,
                  semester:String
    ){
        _isDeleted.postValue(false)
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()
        val pdfRef = storageRef.child("${DEPARTMENT_DOCS}/${randomUid}.pdf")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val uploadTask = pdfRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    pdfRef.downloadUrl.addOnSuccessListener {
                        uploadPyq(it.toString(),
                            randomUid,
                            title,
                            branch,
                            semester
                        )
                    }
                }
            }
            catch (e: Exception) {
                null
            }

        }



    }

    private fun uploadPyq(url: String,
                            docId:String,
                            title: String,
                            branch:String,
                            semester:String


    ) {

        val map = mapOf(

            "url" to url,
            "docId" to docId,
            "title" to title,
            "branch" to branch,
            "semester" to semester,
            "timestamp" to FieldValue.serverTimestamp()
        )

        // Create the structure MessMenu -> Hostel -> Days -> Items
        departmentRef.document(branch)
            .collection("Semester").document(semester)
            .collection("PYQ").document(docId).set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }


    fun savePinDocs(uri:Uri,
                title:String,
                branch:String,
                semester:String
    ){   _isDeleted.postValue(false)
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()
        val pdfRef = storageRef.child("${DEPARTMENT_DOCS}/${randomUid}.pdf")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val uploadTask = pdfRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    pdfRef.downloadUrl.addOnSuccessListener {
                        uploadPinDocs(it.toString(),
                            randomUid,
                            title,
                            branch,
                            semester
                        )
                    }
                }
            }
            catch (e: Exception) {
                null
            }

        }



    }

    private fun uploadPinDocs(url: String,
                          docId:String,
                          title: String,
                          branch:String,
                          semester:String


    ) {
        val map = mapOf(

            "url" to url,
            "docId" to docId,
            "title" to title,
            "branch" to branch,
            "semester" to semester,
            "timestamp" to FieldValue.serverTimestamp()
        )

        // Create the structure MessMenu -> Hostel -> Days -> Items
        departmentRef.document(branch)
            .collection("Semester").document(semester)
            .collection("PINDOCS").document(docId).set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }





    fun uploadBranch(branch: String) {

        _isDeleted.postValue(false)

        _isPosted.postValue(false)

        val map = mutableMapOf<String, Any>()
        map["branch"] = branch
        map["timestamp"] = FieldValue.serverTimestamp()

        departmentRef.document(branch).set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }

    fun uploadSemester(branch: String, semester: String) {
        _isDeleted.postValue(false)
        _isPosted.postValue(false)
        val map = mutableMapOf<String, Any>()

        map["semester"] = semester
        map["timestamp"] = FieldValue.serverTimestamp()

        // Ensure messmenuRef points to the root collection "MessMenu"
        val branchRef = departmentRef.document(branch)
        val semesterCollectionRef = branchRef.collection("Semester")
        val semesterDocumentRef = semesterCollectionRef.document(semester)

        semesterDocumentRef.set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }


    fun getNotes(branch: String, semester: String) {
        _loading.postValue(true)
        departmentRef.document(branch)
            .collection("Semester").document(semester)
            .collection("NOTES").orderBy("timestamp", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { querySnapshot ->
                val list = mutableListOf<BranchModel>()
                for (document in querySnapshot.documents) {
                    list.add(document.toObject(BranchModel::class.java)!!)
                }
                val notesMap = _notesMap.value ?: mutableMapOf()
                notesMap[semester] = list
                _notesMap.postValue(notesMap)
                _notesList.postValue(list)
                _loading.postValue(false)
            }.addOnFailureListener { exception ->
                val notesMap = _notesMap.value ?: mutableMapOf()
                notesMap[semester] = emptyList()
                _notesMap.postValue(notesMap)
                _loading.postValue(false) // Hide loading indicator on failure
                Log.e(ContentValues.TAG, "Error getting mess menu for $semester", exception)
            }
    }

    fun getPyq(branch: String, semester: String) {
        _loading.postValue(true)
        departmentRef.document(branch)
            .collection("Semester").document(semester)
            .collection("PYQ").orderBy("timestamp", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { querySnapshot ->
                val list = mutableListOf<BranchModel>()
                for (document in querySnapshot.documents) {
                    list.add(document.toObject(BranchModel::class.java)!!)
                }
                val pyqMap = _pyqMap.value ?: mutableMapOf()
                pyqMap[semester] = list
                _pyqMap.postValue(pyqMap)
                _pyqList.postValue(list)
                _loading.postValue(false)
            }.addOnFailureListener { exception ->
                val pyqMap = _pyqMap.value ?: mutableMapOf()
                pyqMap[semester] = emptyList()
                _pyqMap.postValue(pyqMap)
                _loading.postValue(false) // Hide loading indicator on failure
                Log.e(ContentValues.TAG, "Error getting mess menu for $semester", exception)
            }
    }


//    fun getPyq(branch: String, semester: String) {
//
//        _loading.postValue(true)
//        departmentRef.document(branch)
//            .collection("Semester").document(semester)
//            .collection("PYQ").orderBy("timestamp", Query.Direction.DESCENDING).get()
//            .addOnSuccessListener { querySnapshot ->
//                val list = mutableListOf<BranchModel>()
//                for (document in querySnapshot.documents) {
//                    list.add(document.toObject(BranchModel::class.java)!!)
//                }
//                _pyqList.postValue(list)
//                _loading.postValue(false)
//
//            }.addOnFailureListener {
//                    exception ->
//                _pyqList.postValue(emptyList())
//                _loading.postValue(false) // Hide loading indicator on failure
//                Log.e(ContentValues.TAG, "Error getting mess menu for $semester", exception)
//            }
//    }

    fun getPinDocs(branch: String, semester: String) {
        _loading.postValue(true)
        departmentRef.document(branch)
            .collection("Semester").document(semester)
            .collection("PINDOCS").orderBy("timestamp", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { querySnapshot ->
                val list = mutableListOf<BranchModel>()
                for (document in querySnapshot.documents) {
                    list.add(document.toObject(BranchModel::class.java)!!)
                }
                val pindocsMap = _pindocsMap.value ?: mutableMapOf()
                pindocsMap[semester] = list
                _pindocsMap.postValue(pindocsMap)
                _pindocsList.postValue(list)
                _loading.postValue(false)
            }.addOnFailureListener { exception ->
                val pindocsMap = _pindocsMap.value ?: mutableMapOf()
                pindocsMap[semester] = emptyList()
                _pindocsMap.postValue(pindocsMap)
                _loading.postValue(false) // Hide loading indicator on failure
                Log.e(ContentValues.TAG, "Error getting mess menu for $semester", exception)
            }
    }


//    fun getPinDocs(branch: String, semester: String) {
//
//        _loading.postValue(true)
//        departmentRef.document(branch)
//            .collection("Semester").document(semester)
//            .collection("PINDOCS").orderBy("timestamp", Query.Direction.DESCENDING).get()
//            .addOnSuccessListener { querySnapshot ->
//                val list = mutableListOf<BranchModel>()
//                for (document in querySnapshot.documents) {
//                    list.add(document.toObject(BranchModel::class.java)!!)
//                }
//                _pindocsList.postValue(list)
//                _loading.postValue(false)
//
//            }.addOnFailureListener {
//                    exception ->
//                _pindocsList.postValue(emptyList())
//                _loading.postValue(false) // Hide loading indicator on failure
//                Log.e(ContentValues.TAG, "Error getting mess menu for $semester", exception)
//            }
//    }



    fun getBranch() {

        _loading.postValue(true)
        departmentRef.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnSuccessListener {
            val list = mutableListOf<String>()

            for (item in it) {
                list.add(item.get("branch").toString())
            }
            _branchList.postValue(list)
            _loading.postValue(false)
        }
    }

    fun getSemester(branch:String) {
        _loading.postValue(true)
        departmentRef.document(branch).collection("Semester")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get().addOnSuccessListener {
                val list = mutableListOf<String>()
                for(item in it){
                    list.add(item.get("semester").toString())

                }
                _semesterList.postValue(list)
                _loading.postValue(false)
            }
    }

    fun deleteNotes(branchModel: BranchModel, branch:String, semester:String, docId:String) {
        departmentRef
            .document(branch)
            .collection("Semester")
            .document(semester)
            .collection("NOTES")
            .document(docId)
            .delete()
            .addOnSuccessListener {
                Firebase.storage.getReferenceFromUrl(branchModel.url!!).delete()
                _isDeleted.postValue(true)
            }
            .addOnFailureListener { e ->
                _isDeleted.postValue(false)
                Log.e(ContentValues.TAG, "Error deleting document", e)
            }


    }

    fun deletePyq(branchModel: BranchModel, branch:String, semester:String, docId:String) {
        departmentRef
            .document(branch)
            .collection("Semester")
            .document(semester)
            .collection("PYQ")
            .document(docId)
            .delete()
            .addOnSuccessListener {
                Firebase.storage.getReferenceFromUrl(branchModel.url!!).delete()
                _isDeleted.postValue(true)
            }
            .addOnFailureListener { e ->
                _isDeleted.postValue(false)
                Log.e(ContentValues.TAG, "Error deleting document", e)
            }


    }


    fun deletePinDocs(branchModel: BranchModel, branch:String, semester:String, docId:String) {
        departmentRef
            .document(branch)
            .collection("Semester")
            .document(semester)
            .collection("PINDOCS")
            .document(docId)
            .delete()
            .addOnSuccessListener {
                Firebase.storage.getReferenceFromUrl(branchModel.url!!).delete()
                _isDeleted.postValue(true)
            }
            .addOnFailureListener { e ->
                _isDeleted.postValue(false)
                Log.e(ContentValues.TAG, "Error deleting document", e)
            }


    }


    fun deleteBranch(branch: String) {


        departmentRef.document(branch).delete().addOnSuccessListener {

            _isDeleted.postValue(true)
        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }
    }

    fun deleteSemester(branch: String, semester: String) {
        departmentRef
            .document(branch)
            .collection("Semester")
            .document(semester)
            .delete()
            .addOnSuccessListener {
                _isDeleted.postValue(true)
            }
            .addOnFailureListener {
                _isDeleted.postValue(false)
            }
    }



}

