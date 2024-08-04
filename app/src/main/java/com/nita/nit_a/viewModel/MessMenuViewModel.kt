package com.nita.nit_a.viewModel
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nita.nit_a.Models.MessMenuModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import java.util.UUID


class MessMenuViewModel: ViewModel() {
    private val messmenuRef = Firebase.firestore.collection("MessMenu")


    private val _isPosted = MutableLiveData<Boolean>(false)
    val isPosted: LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _messmenuList = MutableLiveData<List<MessMenuModel>>()
    val messmenuList: LiveData<List<MessMenuModel>> = _messmenuList

    private val _hostelList = MutableLiveData<List<String>>()
    val hostelList: LiveData<List<String>> = _hostelList

    private val _dayList = MutableLiveData<List<String>>()
    val dayList: LiveData<List<String>> = _dayList

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading




    fun saveMessMenu(breakfast:String,
                     lunch:String,
                     dinner:String,
                     hostel:String,
                     day:String){
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()


        uploadMessMenu(breakfast,
            lunch,
            dinner,
            hostel,
            day,
            randomUid
        )

    }

    private fun uploadMessMenu(breakfast: String,
                               lunch: String,
                               dinner: String,
                               hostel:String,
                               day:String,
                               docId:String

                                ) {
        val map = mapOf(

            "breakfast" to breakfast,
            "lunch" to lunch,
            "dinner" to dinner,
            "docId" to docId,
            "day" to day,
            "hostel" to hostel

        )

        // Create the structure MessMenu -> Hostel -> Days -> Items
        messmenuRef.document(hostel)
            .collection("Days").document(day)
            .collection("Items").document(docId).set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }



    fun uploadhostel(hostel: String) {

        _isPosted.postValue(false)

        val map = mutableMapOf<String, Any>()
        map["hostel"] = hostel
        map["timestamp"] = FieldValue.serverTimestamp()

        messmenuRef.document(hostel).set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }

    fun uploadDay(hostel: String, day: String) {
        _isPosted.postValue(false)
        val map = mutableMapOf<String, Any>()

        map["day"] = day
        map["timestamp"] = FieldValue.serverTimestamp()

        // Ensure messmenuRef points to the root collection "MessMenu"
        val hostelRef = messmenuRef.document(hostel)
        val daysCollectionRef = hostelRef.collection("Days")
        val dayDocumentRef = daysCollectionRef.document(day)

        dayDocumentRef.set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }


    fun getMessMenu(hostel: String, day: String) {

        _loading.postValue(true)
        messmenuRef.document(hostel)
            .collection("Days").document(day)
            .collection("Items").get()
            .addOnSuccessListener { querySnapshot ->
                val list = mutableListOf<MessMenuModel>()
                for (document in querySnapshot.documents) {
                    list.add(document.toObject(MessMenuModel::class.java)!!)
                }
                _messmenuList.postValue(list)
                _loading.postValue(false)

            }.addOnFailureListener {
                    exception ->
                _messmenuList.postValue(emptyList())
                _loading.postValue(false) // Hide loading indicator on failure
                Log.e(TAG, "Error getting mess menu for $day", exception)
            }
    }



    fun gethostel() {

        _loading.postValue(true)
        messmenuRef.orderBy("timestamp",Query.Direction.ASCENDING).get().addOnSuccessListener {
            val list = mutableListOf<String>()

            for (item in it) {
                list.add(item.get("hostel").toString())
            }
            _hostelList.postValue(list)
            _loading.postValue(false)
        }
    }

    fun getday(hostel:String) {
        _loading.postValue(true)
        messmenuRef.document(hostel).collection("Days").orderBy("timestamp", Query.Direction.ASCENDING)
            .get().addOnSuccessListener {
            val list = mutableListOf<String>()
            for(item in it){
                list.add(item.get("day").toString())

            }
            _dayList.postValue(list)
                _loading.postValue(false)
        }
    }

    fun deleteMessMenu(hostel:String, day:String, docId:String) {

        messmenuRef
            .document(hostel)
            .collection("Days")
            .document(day)
            .collection("Items")
            .document(docId)
            .delete()
            .addOnSuccessListener {
                _isDeleted.postValue(true)
            }
            .addOnFailureListener { e ->
                _isDeleted.postValue(false)
                Log.e(TAG, "Error deleting document", e)
            }
        _messmenuList.postValue(_messmenuList.value?.filter { it.docId != docId })

    }


    fun deleteHostel(hostel: String) {


        messmenuRef.document(hostel).delete().addOnSuccessListener {

            _isDeleted.postValue(true)
        }.addOnFailureListener{
            _isDeleted.postValue(false)
        }
    }

    fun deleteDay(hostel: String, day: String) {
        messmenuRef
            .document(hostel)
            .collection("Days")
            .document(day)
            .delete()
            .addOnSuccessListener {
                _isDeleted.postValue(true)
            }
            .addOnFailureListener {
                _isDeleted.postValue(false)
            }
    }



}