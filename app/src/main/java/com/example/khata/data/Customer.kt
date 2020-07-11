package com.example.khata
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Customer(
    var name: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var pincode: String = ""

)

/*
Customer Details :
Customer ID – Auto Assigned
Customer Name
customer email
Phone Number – Primary, Secondary
Address – House Number, Village/Area, City/Town, Pincode

//Bills History – Total Outstanding Credit, Maximum Credit.

 */