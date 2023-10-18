package com.example.zad1;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.usernameEditText)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val username = usernameEditText.text.toString()

            val intent = Intent(this, UserActivity::class.java).apply {
                putExtra("username", username)
            }
            startActivity(intent)
        }
    }
}