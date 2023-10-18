package com.example.zad1;

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.Executors
import java.util.concurrent.Future

class CountingService : Service() {

    private val executorService = Executors.newCachedThreadPool()
    private var runningTasks = mutableMapOf<Int, Future<*>>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val username = intent?.getStringExtra("username")
        val future = executorService.submit {
            var i = 0
            while (true) {
                Log.d("CountingService", "Username: $username, Time elapsed: $i seconds")
                i++
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    val broadcastIntent = Intent(".com.example.COUNT_ACTION")
                    broadcastIntent.putExtra("username", username)
                    broadcastIntent.putExtra("number", i)
                    sendBroadcast(broadcastIntent)
                    break
                }
            }
        }
        runningTasks[startId] = future
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        runningTasks.forEach { (startId, future) ->
            future.cancel(true)
            stopSelf(startId)
        }
        executorService.shutdownNow()
    }
}