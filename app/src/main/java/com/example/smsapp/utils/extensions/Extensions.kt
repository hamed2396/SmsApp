package com.example.smsapp.utils.extensions

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Any?.ifNull(block: () -> Unit) {
    if (this == null) block()
}

fun TextInputEditText.textString() = text.toString()
fun <T : AppCompatActivity> T.launchLifeCycle(action: suspend CoroutineScope.() -> Unit) =
    lifecycleScope.launch {
        action()

    }