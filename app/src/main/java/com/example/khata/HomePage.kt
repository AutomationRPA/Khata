package com.example.khata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.khata.data.Firebase
import com.example.khata.data.FirebaseInitilize
import kotlinx.android.synthetic.main.homepage.*

class HomePage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val prefs = getSharedPreferences("com.example.khata", MODE_PRIVATE)
        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false+
            Log.v("AppTest_Main : ", "Firstrun ")
            //requestPermissions()

            //Initilize Firebase
            val firebaseInitClass = FirebaseInitilize()
            firebaseInitClass.initUserFirebaseDB()
            //Set First Rum Completed
            prefs.edit().putBoolean("firstrun", false).apply()
        }

        button_Customers.setOnClickListener()
        {
            val intent = Intent(this, Customers::class.java)
            startActivity(intent)

        }

        button_NewInvoice.setOnClickListener {
            val firebaseInitClass = FirebaseInitilize()
            firebaseInitClass.initUserFirebaseDB()
        }
    }
}