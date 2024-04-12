package com.example.smsapp.data.repository

import com.example.smsapp.data.model.Message
import com.example.smsapp.data.source.SmsDataSource
import javax.inject.Inject

class SmsRepositoryImpl @Inject constructor(private val smsDataSource: SmsDataSource) : SmsRepository {

    override fun sendMessage(message: Message){
         smsDataSource.sendSms(message)
    }
}