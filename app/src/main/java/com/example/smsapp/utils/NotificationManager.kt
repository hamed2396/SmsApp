package com.example.smsapp.utils


import android.app.NotificationManager
import android.content.Context
import android.telephony.SmsMessage
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.smsapp.R
import com.example.smsapp.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationHandler(@ApplicationContext private val context: Context) {

    fun showNotification(smsMessage: SmsMessage) {
        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_main)
            .setDestination(R.id.receiveSmsFragment)
            .createPendingIntent()

        val notification = NotificationCompat.Builder(context, "running_channel")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setContentTitle(smsMessage.displayOriginatingAddress)
            .setContentText(smsMessage.messageBody)
            .build()

        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, notification)
        //saving sms message

    }
}
