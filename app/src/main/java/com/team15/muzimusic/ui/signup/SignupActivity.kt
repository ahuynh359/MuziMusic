package com.team15.muzimusic.ui.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.team15.muzimusic.R
import com.team15.muzimusic.base.activities.BaseActivity
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.SignupModal
import com.team15.muzimusic.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : BaseActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel>()

    private var isEmailValid = false
    private var isAccountNameValid = false
    private var isPassValid = false
    private var isConfirmPassValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.tvLogin.setOnClickListener{
            finish()
        }

        binding.btnSignUp.setOnClickListener {
            val modal = getSignupModal()
            modal?.let { viewModel.signup(it) }
        }

        viewModel.isLoading.observe(this) {
            binding.btnSignUp.isEnabled = !it
        }

        viewModel.status.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(this, viewModel.message, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    showErrorDialog(viewModel.message ?: "Server Error")
                }
                viewModel.status.postValue(null)
            }
        }

        textWatchers()
    }

    private fun getSignupModal(): SignupModal? {
        if (isEmailValid && isAccountNameValid && isPassValid && isConfirmPassValid) {
            return SignupModal(
                binding.edtEmail.text.toString().trim(),
                binding.edtUser.text.toString().trim(),
                binding.edtPassword.text.toString().trim(),
                binding.edtConfirmPassword.text.toString().trim()
            )
        }
        return null

    }

    private fun textWatchers() {
        validateEmail()
        validateAccountName()
        validatePass()
        validateConfirmPass()
    }

    private fun validateEmail() {
        binding.edtEmail.addTextChangedListener {
            it?.let {
                binding.tvEmailError.apply {
                    isEmailValid = false
                    when {
                        it.toString().isEmpty() -> {
                            text = context.getString(R.string.do_not_leave_email_empty)
                            visibility = View.VISIBLE
                        }
                        !Helper.validateEmail(it.toString()) -> {
                            text = context.getString(R.string.email_format_is_not_validate)
                            visibility = View.VISIBLE
                        }
                        else -> {
                            isEmailValid = true
                            visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun validateAccountName() {
        binding.edtUser.addTextChangedListener {
            it?.let {
                binding.tvUserError.apply {
                    isAccountNameValid = false
                    when {
                        it.toString().isEmpty() -> {
                            text = context.getString(R.string.do_not_leave_user_empty)
                            visibility = View.VISIBLE
                        }
                        else -> {
                            isAccountNameValid = true
                            visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun validatePass() {
        binding.edtPassword.addTextChangedListener {
            it?.let {
                binding.tvPasswordError.apply {
                    isPassValid = false
                    when {
                        it.toString().length < 6 -> {
                            text = context.getString(R.string.password_has_at_least_6_characters)
                            visibility = View.VISIBLE
                        }
                        else -> {
                            isPassValid = true
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
                            text = context.getString(R.string.do_not_leave_confirm_password_empty)
                            visibility = View.VISIBLE
                        }
                        it.toString() != binding.edtPassword.text.toString() -> {
                            text = context.getText(R.string.not_match)
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