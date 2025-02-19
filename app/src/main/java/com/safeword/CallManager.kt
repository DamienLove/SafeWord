package com.safeword

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.TelephonyManager

class CallManager(private val context: Context) {

    fun makeEmergencyCall(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(callIntent)
    }

    fun isCallActive(): Boolean {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.callState != TelephonyManager.CALL_STATE_IDLE
    }
}
