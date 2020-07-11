package com.example.khata
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Product(
    var customerName: String? = "",
    var phoneNumber: String? = ""
)