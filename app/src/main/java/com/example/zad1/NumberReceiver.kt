package com.example.zad1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class NumberReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val username = intent.getStringExtra("username")
        val counter = intent.getIntExtra("counter", 0)
        Log.d("NumberReceiver", "Username: $username, Counter: $counter")

        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "number-database"
        ).build()

        CoroutineScope(Dispatchers.IO).launch {
            db.numberDao().insertAll(NumberEntity(UUID.randomUUID().toString(), username ?: "", counter))
        }
    }
}