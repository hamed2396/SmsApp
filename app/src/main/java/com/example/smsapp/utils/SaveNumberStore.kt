package com.example.smsapp.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


 class SaveNumberStore @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private const val NUMBER = "number"

        private const val DATA_STORE = "data_store"
        private val Context.dataStore by preferencesDataStore(DATA_STORE)
        private val phoneNumber = stringPreferencesKey(NUMBER)

    }

    suspend fun saveNumber(number: String) = context.dataStore.edit {
        it[phoneNumber] = number

    }

    fun getNumber() = context.dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences()) else throw it
    }.map {
        it[phoneNumber] ?: ""
    }


}