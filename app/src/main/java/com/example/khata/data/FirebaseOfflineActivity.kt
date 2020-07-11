package com.example.khata.data

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FirebaseOfflineActivity : AppCompatActivity(){

   fun enablePersistence() {
        // [START rtdb_enable_persistence]
        Firebase.database.setPersistenceEnabled(true)
        // [END rtdb_enable_persistence]
    }

    private fun keepSynced() {
        // [START rtdb_keep_synced]
        val scoresRef = Firebase.database.getReference("scores")
        scoresRef.keepSynced(true)
        // [END rtdb_keep_synced]

        // [START rtdb_undo_keep_synced]
        scoresRef.keepSynced(false)
        // [END rtdb_undo_keep_synced]
    }
}