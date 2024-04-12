package com.example.smsapp.ui

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smsapp.R
import com.example.smsapp.databinding.ActivityMainBinding
import com.example.smsapp.utils.Constants
import com.example.smsapp.utils.SaveNumberStore
import com.example.smsapp.utils.extensions.launchLifeCycle
import com.example.smsapp.utils.smsreceiver.MessageReceiver
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null

    @Inject
    lateinit var messageReceiver: MessageReceiver
    private val binding get() = _binding!!
    @Inject
    lateinit var saveNumberStore: SaveNumberStore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        //region
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //endregion

        launchLifeCycle {
            saveNumberStore.getNumber().collect {
                Constants.number = it

            }
        }

        getRequiredPermissions()


    }

    private fun getRequiredPermissions() {
        //send sms permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                200
            )
        }

    }

    private fun getNotificationPermissions() {
        //send sms permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    201
                )
            }
        }

    }


    private fun listenToSmsReceive() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED")
        registerReceiver(messageReceiver, intentFilter)
    }

    //check permissions to see if it's accepted or denied
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 200) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getNotificationPermissions()
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.grant_permissions),
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }
        if (requestCode == 201) {
            if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.grant_permissions), Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        listenToSmsReceive()
    }

    override fun onDestroy() {
        super.onDestroy()
        //unregister the receiver to avoid memory leak
        unregisterReceiver(messageReceiver)
        _binding = null
    }
}