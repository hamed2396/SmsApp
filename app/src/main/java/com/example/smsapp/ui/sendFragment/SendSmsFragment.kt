package com.example.smsapp.ui.sendFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smsapp.R
import com.example.smsapp.data.model.Message
import com.example.smsapp.databinding.FragmentSendSmsBinding
import com.example.smsapp.utils.base.BaseFragment
import com.example.smsapp.utils.extensions.textString
import com.example.smsapp.viewmodel.SendSmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SendSmsFragment : BaseFragment<FragmentSendSmsBinding>(FragmentSendSmsBinding::inflate) {

    private val viewModel by viewModels<SendSmsViewModel>()

    @Inject
    lateinit var message: Message
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            receiveBtn.setOnClickListener { findNavController().navigate(R.id.actionSendToReceive) }
            sendBtn.setOnClickListener {
                message.phoneNumber = phoneEdt.textString()
                message.content = messageEdt.textString()

                if (message.phoneNumber.isNotEmpty() and message.content.isNotEmpty())
                    viewModel.sendMessage(message)
                else Toast.makeText(
                    requireContext(),
                    getString(R.string.fillFields),
                    Toast.LENGTH_SHORT
                ).show()


            }

        }

    }


}




