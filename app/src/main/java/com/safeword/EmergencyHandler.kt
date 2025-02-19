package com.safeword

import android.content.Context
import android.content.Intent
import android.net.Uri

class EmergencyHandler(private val context: Context) {

    fun callEmergencyContact(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(callIntent)
    }
}
