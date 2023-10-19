package com.example.zad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        val editTextUserInput: EditText = findViewById(R.id.editTextUserInput)

        buttonSubmit.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("username", editTextUserInput.text.toString())
            startActivity(intent)
        }
    }
}
