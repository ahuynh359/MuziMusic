package com.team15.muzimusic.ui.forgetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.ForgetEmailModal
import com.team15.muzimusic.databinding.FragmentStepEmailBinding

class StepEmailFragment : Fragment() {

    private lateinit var binding: FragmentStepEmailBinding
    private val viewModel by viewModels<ForgetPasswordViewModel>({ requireActivity() })
    private var isEmailValid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStepEmailBinding.inflate(inflater, container, false)

        binding.btnDone.setOnClickListener {
            if (isEmailValid) {
                viewModel.email = binding.edtEmail.text.toString().trim()
                viewModel.forgetEmail(ForgetEmailModal(viewModel.email))
            }
        }

        viewModel.loadingEmail.observe(viewLifecycleOwner) {
            binding.btnDone.isEnabled = !it
        }

        viewModel.emailStatus.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    viewModel.actionNextStep.postValue(true)
                } else {
                    binding.tvConfirmEmailError.text = viewModel.message
                    binding.tvConfirmEmailError.visibility = View.VISIBLE

                    Toast.makeText(context, viewModel.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.emailStatus.value = null
            }
        }

        binding.edtEmail.addTextChangedListener {
            it?.let {
                isEmailValid = Helper.validateEmail(it.toString().trim())
                binding.tvConfirmEmailError.visibility = if (isEmailValid) View.GONE else View.VISIBLE
            }
        }

        return binding.root
    }

}