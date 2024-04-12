package com.example.smsapp.data.model

// SRP-> class has a single responsibility of representing a message
data class Message(
    var phoneNumber: String="",
    var content: String=""

)