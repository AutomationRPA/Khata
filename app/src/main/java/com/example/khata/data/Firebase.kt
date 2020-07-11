package com.example.khata.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.khata.Customer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Firebase(context: Context) {

    val appContext = context
    private val currentUserID = "SharanBaradi"
    private val firebaseInstance = FirebaseDatabase.getInstance()
    var firebaseReference = firebaseInstance.reference.child("UsersDB").child(currentUserID)

    fun writeNewCustomer(customer: Customer): Boolean {
        var returnBoolean = false
        //Firebase.database.setPersistenceEnabled(true)
        firebaseReference = firebaseReference.child("CustomersDB")

        firebaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var maxCustomerID: Long = snapshot.child("MaxCustomerID").value.toString().toLong()
                println("MaxCustomerID : $maxCustomerID")
                maxCustomerID += 1

                firebaseReference.child(maxCustomerID.toString())
                    .setValue(customer).addOnSuccessListener {
                        firebaseReference.child("MaxCustomerID").setValue(maxCustomerID)
                        Log.d("AppTest_Customers : ", "Customer Added")
                        Toast.makeText(appContext, "Customer Added", Toast.LENGTH_SHORT).show()
                        returnBoolean = true
                    }.addOnFailureListener {
                        Log.d("AppTest_Customers : ", "Error adding customer")
                        Toast.makeText(appContext, "Error adding customer\n$it", Toast.LENGTH_SHORT)
                            .show()
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(appContext, "Error", Toast.LENGTH_SHORT).show()
                println(error)
            }
        })
        return returnBoolean
    }

    fun readFromDatabaseTest(): String {

        var snapshotStr = ""

        // Read from the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customer")
        myRef.setValue("MyCust1")

        database.reference.child("UsersDB").child("SharanBaradi").child("CustomerDB")
            .child("MaxCustomerID").setValue(20)


        database.reference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                println(p0)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                children.forEach {
                    snapshotStr += it.toString()
                    println(it.toString())
                }
                println("\n\n")
                val customerStr = snapshot.child("Customer").value
                println("\n" + customerStr)
                val tempInt = snapshot.child("UsersDB").child("SharanBaradi").child("CustomersDB")
                    .child("MaxCustomerID").value
                println("\n" + tempInt.toString())
            }
        })

        return snapshotStr
    }

    // Read from the database
    /* firebaseReference.addValueEventListener(object : ValueEventListener {
         override fun onDataChange(dataSnapshot: DataSnapshot) {
             // This method is called once with the initial value and again
             // whenever data at this location is updated.
             val value =
                 dataSnapshot.getValue(String::class.java)!!
             Log.d("AppTest_Customers : ", "Value is: $value")
         }

         override fun onCancelled(error: DatabaseError) {
             // Failed to read value
             Log.w("AppTest_Customers : ", "Failed to read value.", error.toException())
         }
     })

     */
/*
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value =
                    dataSnapshot.getValue(String::class.java)!!
                Log.d("AppTest_Customers : ", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("AppTest_Customers : ", "Failed to read value.", error.toException())
            }
        })

    }
    */
    /*fun readFromDatabase() {

        // Read from the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customer")
        myRef.setValue("MyCust1")

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value =
                    dataSnapshot.getValue(String::class.java)!!
                Log.d("AppTest_Customers : ", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("AppTest_Customers : ", "Failed to read value.", error.toException())
            }
        })
    }
    */
}


class FirebaseInitilize {

    private val currentUserID = "SharanBaradi"
    private val firebaseInstance = FirebaseDatabase.getInstance()
    private var firebaseReference = firebaseInstance.reference.child("UsersDB").child(currentUserID)


    fun initUserFirebaseDB() {
        firebaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                //Prints the whole SnapShot
                val children = snapshot.children
                println("SnapShot : \n")
                children.forEach {
                    println(it.toString())
                }

                val path = snapshot.ref.toString()
                println("Ref  : $path")
                Log.d(
                    "AppTest_Init : ",
                    "Checking Nodes : " + "\nNotes : " + snapshot.hasChild("Notes") + "\nMaxCustomerID : " +
                            snapshot.child("CustomersDB").hasChild("MaxCustomerID")
                )

                if (snapshot.child("Notes").exists()) {
                    Log.d("AppTest_Init : ", "Firebase - Already Initilized UserDB - Notes")
                } else {
                    Log.d("AppTest_Init : ", "Initilizing UserDB - Notes")
                    firebaseReference.child("Notes").setValue("User Initilized")
                }

                /*
                 if (snapshot.hasChild("")) {
                    Log.d("AppTest_Init : ", "Initilizing UserDB - Notes")
                } else {
                    Log.d("AppTest_Init : ", "Firebase Already Initilized UserDB - Notes")
                }
                 */

                if (snapshot.child("CustomersDB").child("MaxCustomerID").exists()) {
                    Log.d(
                        "AppTest_Init : ",
                        "Firebase - Already Initilized /CustomerDB/MaxCustomerID"
                    )
                } else {
                    Log.d("AppTest_Init : ", "Initilizing /CustomerDB/MaxCustomerID")
                    firebaseReference.child("CustomersDB").child("MaxCustomerID").setValue(0)
                }
            }
        })
    }
}

/*
--FireBase Data Structure--


UsersDB
    +UserName_UserId
        +CustomersDB
            MaxCustomerID
            ...
            +CustomerID
                Name
                Email
        +Products
            MaxProductID
            ...
        +Invoices
            MaxInvoiceID
            ...



 */