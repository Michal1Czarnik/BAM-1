package com.example.zad1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {

    companion object {
        val jobs = mutableMapOf<Int, Job>()
    }

    private var serviceId = 0
    private var counter = 0
    private var username: String? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        username = intent?.getStringExtra("username")
        serviceId = startId
        val job = CoroutineScope(Dispatchers.IO).launch {
            counter = 0
            while (isActive) {
                delay(1000L)
                counter++
                Log.d("MyService", "Service $startId: $counter")
            }
        }
        jobs[startId] = job
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        jobs[serviceId]?.cancel()
        Intent("com.example.broadcast.NUMBER_ACTION").apply {
            putExtra("username", username)
            putExtra("counter", counter)
        }.also { sendBroadcast(it) }
        jobs.remove(serviceId)
    }
}