package com.nita.nit_a.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.nita.nit_a.Models.UserProfileModel

object SharePref {

    fun storeData( userProfileModel: UserProfileModel,

                  context: Context){

        val sharePreferences = context.getSharedPreferences("UsersDetails", MODE_PRIVATE)
        val editor = sharePreferences.edit()
        editor.putString("name",  userProfileModel.name)
        editor.putString("hostel", userProfileModel.hostel)
        editor.putString("department", userProfileModel.department)
        editor.putString("year", userProfileModel.year)
        editor.apply()

    }

    fun getUserData(context: Context): UserProfileModel? {
        val sharedPreferences = context.getSharedPreferences("UserDetails", MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val hostel = sharedPreferences.getString("hostel", "")
        val department = sharedPreferences.getString("department", "")
        val year = sharedPreferences.getString("year", "")
        return if (name != null && hostel != null && department != null && year != null) {
            UserProfileModel(name, hostel, department, year)
        } else {
            null
        }
    }

//    fun getName(context: Context):String{
//
//        val sharedPreferences = context.getSharedPreferences("UserDetails", MODE_PRIVATE)
//        return sharedPreferences.getString("name", "")!!
//
//    }
//
//    fun getHostel(context: Context):String{
//
//        val sharedPreferences = context.getSharedPreferences("UserDetails", MODE_PRIVATE)
//        return sharedPreferences.getString("hostel", "")!!
//
//    }
//
//    fun getDepartment(context: Context):String{
//
//        val sharedPreferences = context.getSharedPreferences("UserDetails", MODE_PRIVATE)
//        return sharedPreferences.getString("department", "")!!
//
//    }
//
//    fun getYear(context: Context):String{
//
//        val sharedPreferences = context.getSharedPreferences("UserDetails", MODE_PRIVATE)
//        return sharedPreferences.getString("year", "")!!
//
//    }
}