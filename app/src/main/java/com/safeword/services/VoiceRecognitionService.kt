package com.safeword.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.safeword.VoiceRecognition

class VoiceRecognitionService : Service() {
    private lateinit var voiceRecognition: VoiceRecognition

    override fun onCreate() {
        super.onCreate()

        voiceRecognition = VoiceRecognition(this) { recognizedText ->
            // Handle recognized speech
            println("Recognized Text: $recognizedText")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        voiceRecognition.startListening()
        return START_STICKY
    }

    override fun onDestroy() {
        voiceRecognition.stopListening()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
