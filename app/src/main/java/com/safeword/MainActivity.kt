package com.safeword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.safeword.services.VoiceRecognitionService
import com.safeword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var safewordEnable: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Use ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ToggleButton
        safewordEnable = binding.SafewordEnable

        // Load saved state from SharedPreferences
        val sharedPreferences = getSharedPreferences("SafeWordPrefs", MODE_PRIVATE)
        val isEnabled = sharedPreferences.getBoolean("isSafeWordEnabled", false)
        safewordEnable.isChecked = isEnabled

        // ✅ Set up button clicks
        binding.startButton.setOnClickListener {
            startVoiceRecognition()
        }

        binding.stopButton.setOnClickListener {
            stopVoiceRecognition()
        }

        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


        // Handle ToggleButton changes
        safewordEnable.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit {
                putBoolean("isSafeWordEnabled", isChecked)
                apply()
            }
            if (isChecked) {
                startSafeWordService()
                Toast.makeText(this, "SafeWord enabled", Toast.LENGTH_SHORT).show()
            } else {
                stopSafeWordService()
                Toast.makeText(this, "SafeWord disabled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startVoiceRecognition() {
        val intent = Intent(this, VoiceRecognitionService::class.java)
        startService(intent)
    }

    private fun stopVoiceRecognition() {
        val intent = Intent(this, VoiceRecognitionService::class.java)
        stopService(intent)
    }

    // Function to start VoiceRecognitionService
    private fun startSafeWordService() {
        val serviceIntent = Intent(this, VoiceRecognitionService::class.java)
        startService(serviceIntent)
    }

    // Function to stop VoiceRecognitionService
    private fun stopSafeWordService() {
        val serviceIntent = Intent(this, VoiceRecognitionService::class.java)
        stopService(serviceIntent)
    }
}
