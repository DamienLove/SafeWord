package com.safeword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.core.content.ContextCompat

class VoiceRecognition(
    private val context: Context,
    private val onResult: (String) -> Unit,
    private val onError: (Int) -> Unit
) : RecognitionListener {

    private val speechRecognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    init {
        speechRecognizer.setRecognitionListener(this)
    }

    fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)
        }
        speechRecognizer.startListening(intent)
    }

    fun stopListening() {
        speechRecognizer.stopListening()
    }

    fun destroy() {
        speechRecognizer.destroy()
    }

    // RecognitionListener overrides

    override fun onReadyForSpeech(params: Bundle?) {
        Log.d("VoiceRecognition", "onReadyForSpeech")
    }

    override fun onBeginningOfSpeech() {
        Log.d("VoiceRecognition", "onBeginningOfSpeech")
    }

    override fun onRmsChanged(rmsdB: Float) {
        // No action needed
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        // No action needed
    }

    override fun onEndOfSpeech() {
        Log.d("VoiceRecognition", "onEndOfSpeech")
    }

    override fun onError(error: Int) {
        Log.e("VoiceRecognition", "onError: $error")
        onError(error)
    }

    override fun onResults(results: Bundle?) {
        results?.getStringArrayList(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()?.let {
            Log.d("VoiceRecognition", "onResults: $it")
            onResult(it)
        }
    }

    override fun onPartialResults(partialResults: Bundle?) {
        partialResults?.getStringArrayList(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()?.let {
            Log.d("VoiceRecognition", "onPartialResults: $it")
        }
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        // No action needed
    }
}
