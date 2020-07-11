package com.example.khata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diveInButton: Button = findViewById(R.id.button_DiveIn)
        diveInButton.setOnClickListener()
        {
            val toast = Toast.makeText(this, "Diving In ", Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)

        }
    }

}