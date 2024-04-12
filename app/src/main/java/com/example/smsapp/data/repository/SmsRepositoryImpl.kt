package com.example.smsapp.data.repository

import com.example.smsapp.data.model.Message
import com.example.smsapp.data.source.SmsDataSource
import javax.inject.Inject

//OCP->It implements the sendMessage method defined in the SmsRepository interface without modifying the interface itself
//DIP->t relies on the SmsRepository interface to define the contract for sending SMS messages
class SmsRepositoryImpl @Inject constructor(private val smsDataSource: SmsDataSource) :
    SmsRepository {

    override fun sendMessage(message: Message) {
        smsDataSource.sendSms(message)
    }
}