package com.example.zad1;

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NumberReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val username = intent.getStringExtra("username")
        val number = intent.getIntExtra("number", 0)

        Log.d("NumberReceiver", "Username: $username, Number: $number")
    }
}