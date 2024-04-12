// MessageReceiver.kt
package com.example.smsapp.utils.smsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import com.example.smsapp.utils.Constants
import com.example.smsapp.utils.NotificationHandler
import javax.inject.Inject

class MessageReceiver @Inject constructor() : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationHandler = NotificationHandler(context!!)

        val data = intent!!.extras
        val pdus = data!!["pdus"] as Array<*>?
        for (i in pdus!!.indices) {
            val smsMessage: SmsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray?)
            //init the SMS_MESSAGE . show it in receive fragment textView
            Constants.SMS_MESSAGE = smsMessage.messageBody
            // check if the saved number matches the incoming SMS
            if (Constants.number == smsMessage.displayOriginatingAddress.replace("+98", "0"))
                notificationHandler.showNotification(smsMessage)


        }
    }
}
