package com.example.khata


import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.khata.data.Firebase
import kotlinx.android.synthetic.main.customer_addnewdetails.*
import kotlinx.android.synthetic.main.customers.*


class Customers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customers)

        val snapshotTextView: TextView = findViewById(R.id.textView_Snapshot)

        val firabaseClass = Firebase(applicationContext)
        val customer = Customer("Baradi", "sharan.sai5@gmail.com")

        button_Read.setOnClickListener {
            val snapshotStr = firabaseClass.readFromDatabaseTest()
            snapshotTextView.text = snapshotStr
            Log.d("AppTest_Customers : ", "snapshotStr value \n$snapshotStr")

        }
        button_Write.setOnClickListener {

        }
        button_AddCustomer.setOnClickListener {
            firabaseClass.writeNewCustomer(customer)
        }

        imageButton_AddNewCustomer.setOnClickListener {
            setContentView(R.layout.customer_addnewdetails)

            button_SubmitCustomer.setOnClickListener {
                customer.name = editText_CustomerName.text.toString()
                customer.email = editText_CustomerEmail.text.toString()

                firabaseClass.writeNewCustomer(customer)

 /*               if (firabaseClass.writeNewCustomer(customer)) {
                Toast.makeText(
                    this,
                    "Customer Details : " + customer.name + "  " + customer.email,
                    Toast.LENGTH_SHORT
                ).show()
            }
                else {
                Toast.makeText(
                    this,
                    "Could not add customer : write new customer false " ,
                    Toast.LENGTH_SHORT
                ).show()

            }

  */

                setContentView(R.layout.customers)
            }
        }
    }
}