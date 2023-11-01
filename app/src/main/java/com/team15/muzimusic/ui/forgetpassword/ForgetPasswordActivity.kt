package com.team15.muzimusic.ui.forgetpassword

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.aceinteract.android.stepper.StepperNavListener
import com.team15.muzimusic.R
import com.team15.muzimusic.base.activities.BaseActivity
import com.team15.muzimusic.databinding.ActivityForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordActivity : BaseActivity(), StepperNavListener {

    private lateinit var binding: ActivityForgetPasswordBinding
    private val viewModel by viewModels<ForgetPasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

        setupStepper()
    }


    private fun setupStepper() {
        binding.stepper.apply {
            setupWithNavController(findNavController(R.id.frame_stepper))
            stepperNavListener = this@ForgetPasswordActivity

            viewModel.actionNextStep.observe(this@ForgetPasswordActivity) {
                if (it) {
                    viewModel.currentStep++
                    goToNextStep()
                    viewModel.actionNextStep.value = false
                }
            }
        }
    }


    override fun onBackPressed() {
        if (viewModel.currentStep == 0) finish()
        else {
            viewModel.currentStep--
            binding.stepper.goToPreviousStep()
        }
    }

    override fun onCompleted() {

    }

    override fun onStepChanged(step: Int) {
    }
}