package com.example.smsapp.data.source

import android.telephony.SmsManager
import com.example.smsapp.data.model.Message
import javax.inject.Inject
//SRP-> class has a single responsibility of sending a message
@Suppress("DEPRECATION")
class SmsDataSource @Inject constructor() {
    fun sendSms(message: Message) {
        val smsManager = SmsManager.getDefault()
        val parts = smsManager.divideMessage(message.content)
        smsManager.sendMultipartTextMessage(message.phoneNumber, null, parts, null, null)

    }
}
