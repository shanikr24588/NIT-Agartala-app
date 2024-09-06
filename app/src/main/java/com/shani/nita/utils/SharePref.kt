package com.shani.nita.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.shani.nita.Models.UserProfileModel
import java.util.UUID

object SharePref {

    private const val PREFS_NAME = "UserDetailsPrefs"
    private const val PREF_KEY_USER_ID = "user_id"

    fun getUniqueUserId(context: Context): String {
        val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        var userId = sharedPrefs.getString(PREF_KEY_USER_ID, null)
        if (userId == null) {
            userId = UUID.randomUUID().toString()
            sharedPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply()
        }
        return userId
    }

    fun storeData( userProfileModel: UserProfileModel,

                  context: Context){

        val sharePreferences = context.getSharedPreferences("UsersDetails", MODE_PRIVATE)
        val editor = sharePreferences.edit()
        editor.putString("name",  userProfileModel.name)
        editor.putString("enrollment", userProfileModel.enrollment)
        editor.putString("hostel", userProfileModel.hostel)
        editor.putString("department", userProfileModel.department)
        editor.putString("year", userProfileModel.year)

        editor.apply()

    }

    fun getUserData(context: Context): UserProfileModel? {
        val sharedPreferences = context.getSharedPreferences("UserDetails", MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val enrollment = sharedPreferences.getString("enrollment", "")
        val hostel = sharedPreferences.getString("hostel", "")
        val department = sharedPreferences.getString("department", "")
        val year = sharedPreferences.getString("year", "")

        return if (name != null && enrollment != null && hostel != null && department != null && year != null) {
            UserProfileModel(name, enrollment, hostel, department, year)
        } else {
            null
        }
    }


}