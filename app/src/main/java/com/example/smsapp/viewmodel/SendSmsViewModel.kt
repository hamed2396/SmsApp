package com.example.smsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smsapp.data.model.Message
import com.example.smsapp.data.repository.SmsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SendSmsViewModel @Inject constructor(private val repository: SmsRepositoryImpl) : ViewModel() {

    fun sendMessage(message: Message) {
         repository.sendMessage(message)
    }
}