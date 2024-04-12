package com.example.smsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsapp.utils.SaveNumberStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiveSmsViewModel @Inject constructor(private val saveNumberStore: SaveNumberStore) :
    ViewModel() {
    fun savePhoneNumber(number: String) {
        viewModelScope.launch {
            saveNumberStore.saveNumber(number)

        }
    }
}