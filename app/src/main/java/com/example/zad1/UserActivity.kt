package com.example.zad1


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.IntentFilter
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log

class UserActivity : AppCompatActivity() {

    private lateinit var numberReceiver: NumberReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val username: String? = intent.getStringExtra("username")
        val textViewUsername: TextView = findViewById(R.id.textViewUsername)
        textViewUsername.text = username

        val buttonStartService: Button = findViewById(R.id.buttonStartService)
        val buttonStopService: Button = findViewById(R.id.buttonStopService)
        val buttonReadData: Button = findViewById(R.id.buttonReadData) // Dodaj ten przycisk do layoutu

        numberReceiver = NumberReceiver()
        registerReceiver(numberReceiver, IntentFilter("com.example.broadcast.NUMBER_ACTION"))
        buttonStartService.setOnClickListener {
            val serviceIntent = Intent(this, MyService::class.java).apply {
                putExtra("username", username)
            }
            startService(serviceIntent)
        }

        buttonStopService.setOnClickListener {
            MyService.jobs.values.forEach {
                it.cancel()
                val serviceIntent = Intent(this, MyService::class.java)
                stopService(serviceIntent)
            }
            MyService.jobs.clear()
        }

        buttonReadData.setOnClickListener {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "number-database"
            ).build()

            CoroutineScope(Dispatchers.IO).launch {
                val data = db.numberDao().getAll()
                data.forEach {
                    Log.d("Database", "Username: ${it.username}, Counter: ${it.counter}")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(numberReceiver)
    }
}