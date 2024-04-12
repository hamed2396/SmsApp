package com.example.smsapp.data.repository

import com.example.smsapp.data.model.Message
// OCP, it's open for extension by adding more methods for additional SMS-related operations in the future
interface SmsRepository {
    fun sendMessage(message: Message)
}