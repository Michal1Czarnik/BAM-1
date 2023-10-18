package com.example.zad1;

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var numberReceiver: NumberReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val usernameTextView = findViewById<TextView>(R.id.usernameTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)

        val username = intent.getStringExtra("username")

        usernameTextView.text = username



        startButton.setOnClickListener {
            val intent = Intent(this, CountingService::class.java).apply {
                putExtra("username", username)
            }
            startService(intent)
        }

        stopButton.setOnClickListener {
            val intent = Intent(this, CountingService::class.java)
            stopService(intent)
        }

        numberReceiver = NumberReceiver()
        registerReceiver(numberReceiver, IntentFilter("com.example.COUNT_ACTION"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(numberReceiver)
    }
}