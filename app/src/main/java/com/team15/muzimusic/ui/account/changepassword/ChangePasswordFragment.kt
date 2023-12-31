package com.team15.muzimusic.ui.account.changepassword

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.team15.muzimusic.common.Helper.showHidePassword
import com.team15.muzimusic.data.models.ChangePasswordModal
import com.team15.muzimusic.databinding.FragmentChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : DialogFragment() {

    private lateinit var binding: FragmentChangePasswordBinding
    private val viewModel by viewModels<ChangePasswordViewModel>()

    private var isCurPassValid = false
    private var isNewPassValid = false
    private var isConfirmPassValid = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)


        textWatchers()
        observers()

        binding.btnDone.setOnClickListener {
            val modal = getModal()
            modal?.let { viewModel.changePassword(it) }
        }

        return binding.root
    }

    private fun observers() {
        viewModel.isLoading.observe(this) {
            binding.btnDone.isEnabled = !it
            isCancelable = !it
        }

        viewModel.status.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(context, viewModel.message, Toast.LENGTH_SHORT).show()
                    dismiss()
                } else {
                    Toast.makeText(context, viewModel.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.status.postValue(null)
            }
        }
    }

    private fun getModal(): ChangePasswordModal? {
        if (isCurPassValid && isNewPassValid && isConfirmPassValid) {
            return ChangePasswordModal(
                binding.edtCurrentPassword.text.toString().trim(),
                binding.edtNewPassword.text.toString().trim()
            )
        }
        return null
    }

    private fun textWatchers() {
        validateCurPass()
        validateNewPass()
        validateConfirmPass()
    }

    private fun validateCurPass() {
        binding.edtCurrentPassword.addTextChangedListener {
            it?.let {
                binding.tvPasswordError.apply {
                    isCurPassValid = false
                    when {
                        it.isEmpty() -> {
                            text = "Current Password do not leave empty"
                            visibility = View.VISIBLE
                        }
                        else -> {
                            isCurPassValid = true
                            visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun validateNewPass() {
        binding.edtNewPassword.addTextChangedListener {
            it?.let {
                binding.tvNewPasswordError.apply {
                    isNewPassValid = false
                    when {
                        it.toString().trim().length < 6 -> {
                            text = "At least 6 character"
                            visibility = View.VISIBLE
                        }
                        else -> {
                            isNewPassValid = true
                            visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun validateConfirmPass() {
        binding.edtConfirmPassword.addTextChangedListener {
            it?.let {
                binding.tvConfirmPasswordError.apply {
                    isConfirmPassValid = false
                    when {
                        it.toString().isEmpty() -> {
                            text = "Do not leave empty"
                            visibility = View.VISIBLE
                        }
                        it.toString() != binding.edtNewPassword.text?.trim().toString() -> {
                            text = "Do not macth with new password"
                            visibility = View.VISIBLE
                        }
                        else -> {
                            isConfirmPassValid = true
                            visibility = View.GONE
                        }
                    }
                }
            }
        }
    }





}