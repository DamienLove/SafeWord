package com.safeword

import android.app.Service
import android.content.Intent
import android.os.IBinder

class SafeWordService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // TODO: Implement voice recognition logic here
        return START_STICKY
    }
}
