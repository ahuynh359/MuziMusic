package com.team15.muzimusic.ui.getstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team15.muzimusic.R
import com.team15.muzimusic.databinding.ActivityGetStartedBinding
import com.team15.muzimusic.ui.login.LoginActivity

class GetStartedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}