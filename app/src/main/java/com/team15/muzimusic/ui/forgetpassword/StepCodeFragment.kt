package com.team15.muzimusic.ui.forgetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.team15.muzimusic.data.models.ForgetCodeModal
import com.team15.muzimusic.data.models.ForgetEmailModal
import com.team15.muzimusic.databinding.FragmentStepCodeBinding


class StepCodeFragment : Fragment() {

    private lateinit var binding: FragmentStepCodeBinding
    private val viewModel by viewModels<ForgetPasswordViewModel>({ requireActivity() })

    private lateinit var listEdCode: List<EditText>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStepCodeBinding.inflate(inflater, container, false)

        listEdCode = listOf(
            binding.edt1,
            binding.edt2,
            binding.edt3,
            binding.edt4,
            binding.edt5,
            binding.edt6
        )

        binding.btnSend.setOnClickListener {
            val code = getInputCode()
            code?.let {
                viewModel.code = it
                viewModel.forgetCode(ForgetCodeModal(viewModel.email, it))
            }
        }

        binding.tvResend.setOnClickListener {
            viewModel.forgetEmail(ForgetEmailModal(viewModel.email))
        }

        viewModel.loadingCode.observe(viewLifecycleOwner) {
            binding.btnSend.isEnabled = !it
        }

        viewModel.codeStatus.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    viewModel.actionNextStep.postValue(true)
                } else {
                    Toast.makeText(context, viewModel.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.codeStatus.value = null
            }
        }

        setupEditTextCode()

        return binding.root
    }

    private fun getInputCode(): String? {
        var code = ""
        for (ed in listEdCode) {
            code += ed.text.toString()
        }
        if (code.length != 6) {
            Toast.makeText(context, "Enter full OTP", Toast.LENGTH_SHORT).show()
            return null
        }
        return code
    }

    private fun setupEditTextCode() {
        for (i in listEdCode.indices) {
            listEdCode[i].addTextChangedListener {
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        if (i < listEdCode.size - 1) listEdCode[i + 1].requestFocus()
                    }
                }
            }
            listEdCode[i].doOnTextChanged { text, start, before, count ->
                if (before > start) {
                    if (i > 0) listEdCode[i - 1].requestFocus()
                }
            }
        }
    }

}