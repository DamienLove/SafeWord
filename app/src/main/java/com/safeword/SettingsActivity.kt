package com.safeword

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = getSharedPreferences("SafeWordPrefs", MODE_PRIVATE)

        val etSafeWord1 = findViewById<EditText>(R.id.etSafeWord1)
        val etSafeWord2 = findViewById<EditText>(R.id.etSafeWord2)
        val etContact1 = findViewById<EditText>(R.id.etContact1)
        val etContact2 = findViewById<EditText>(R.id.etContact2)
        val etSensitivity = findViewById<EditText>(R.id.etSensitivity)
        val btnSave = findViewById<Button>(R.id.btnSave)

        etSafeWord1.setText(prefs.getString("SafeWord1", ""))
        etSafeWord2.setText(prefs.getString("SafeWord2", ""))
        etContact1.setText(prefs.getString("Contact1", ""))
        etContact2.setText(prefs.getString("Contact2", ""))
        etSensitivity.setText(prefs.getInt("Sensitivity", 50).toString())

        btnSave.setOnClickListener {
            prefs.edit().apply {
                putString("SafeWord1", etSafeWord1.text.toString())
                putString("SafeWord2", etSafeWord2.text.toString())
                putString("Contact1", etContact1.text.toString())
                putString("Contact2", etContact2.text.toString())
                putInt("Sensitivity", etSensitivity.text.toString().toIntOrNull() ?: 50)
                apply()
            }
            finish()
        }
    }
}
