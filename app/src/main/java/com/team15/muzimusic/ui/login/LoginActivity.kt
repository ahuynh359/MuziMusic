package com.team15.muzimusic.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.team15.muzimusic.R
import com.team15.muzimusic.base.activities.BaseActivity
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.LoginModal
import com.team15.muzimusic.databinding.ActivityLoginBinding
import com.team15.muzimusic.ui.forgetpassword.ForgetPasswordActivity
import com.team15.muzimusic.ui.home.HomeActivity
import com.team15.muzimusic.ui.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isLoading.observe(this) {
            binding.btnLogIn.isEnabled = !it
        }

        viewModel.isLoginSuccess.observe(this) {
            it?.let {
                if (it) {
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    showErrorDialog(viewModel.message!!)
                }
                viewModel.isLoginSuccess.postValue(null)
            }
        }

        binding.btnLogIn.setOnClickListener {
            val modal = getLoginModal()
            modal?.let { viewModel.login(it) }
        }

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java).apply {
                putExtra(Constants.Email, binding.edtEmail.text.toString().trim())
            })
        }
    }

    //Reset lai edt email va password
    override fun onStart() {
        super.onStart()
        val modal = viewModel.getSavedLoginModal()
        modal?.let {
            binding.edtEmail.setText(it.email)
            binding.edtPassword.setText(it.password)
        }
    }

    // Khi button login nhan check loi email va pass
    private fun getLoginModal(): LoginModal? {
        if (!Helper.validateEmail(binding.edtEmail.text.toString())) {
            showErrorDialog("Email is not validate")
            return null
        }
        if (binding.edtEmail.text.toString().isEmpty()) {
            showErrorDialog("Do not leave email empty")
            return null
        }
        if (binding.edtPassword.text.toString().isEmpty()) {
            showErrorDialog("Do not leave password empty")
            return null
        }
        return LoginModal(
            binding.edtEmail.text.toString().trim(),
            binding.edtPassword.text.toString()
        )
    }
}