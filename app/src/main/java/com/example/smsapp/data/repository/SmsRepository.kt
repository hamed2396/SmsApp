package com.example.smsapp.data.repository

import com.example.smsapp.data.model.Message

interface SmsRepository {
    fun sendMessage(message: Message)
}