package com.shani.nita.Models

data class NoticeModel(
    val url :String? = null,
    val docId: String? = null,
    val title:String? = null,
    var isNew:Boolean = false,
    var isSeen:Boolean = false
)
