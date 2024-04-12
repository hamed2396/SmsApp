package com.example.smsapp.ui.receiveFragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.smsapp.R
import com.example.smsapp.databinding.FragmentReceiveSmsBinding
import com.example.smsapp.utils.Constants
import com.example.smsapp.utils.SaveNumberStore
import com.example.smsapp.utils.base.BaseFragment
import com.example.smsapp.utils.extensions.textString
import com.example.smsapp.viewmodel.ReceiveSmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReceiveSmsFragment :
    BaseFragment<FragmentReceiveSmsBinding>(FragmentReceiveSmsBinding::inflate) {

    private val viewModel by viewModels<ReceiveSmsViewModel>()

    @Inject
    lateinit var saveNumberStore: SaveNumberStore
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            showTxt.text = Constants.SMS_MESSAGE

            getReceivePermission()
            saveBtn.setOnClickListener {
                val number = phoneEdt.textString().replace("+98", "0")
                if (number.isNotEmpty())
                    viewModel.savePhoneNumber(number)
                else Toast.makeText(requireContext(), getString(R.string.fillFields), Toast.LENGTH_SHORT).show()



            }

        }


    }

    private fun getReceivePermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECEIVE_SMS),
                202
            )
        }
    }



}