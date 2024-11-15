package com.example.mysteryShopper.data.model

data class Response<T>(
    val data: T,
    val message_ar: String,
    val message_en: String,
    val status: Boolean,
    val status_code: Int,
    val status_message_ar: String,
    val status_message_en: String
)