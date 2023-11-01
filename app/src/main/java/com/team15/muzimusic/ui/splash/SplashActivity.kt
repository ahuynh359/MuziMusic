package com.team15.muzimusic.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.team15.muzimusic.R
import com.team15.muzimusic.common.DataLocal
import com.team15.muzimusic.databinding.ActivitySplashBinding
import com.team15.muzimusic.ui.getstarted.GetStartedActivity
import com.team15.muzimusic.ui.home.HomeActivity
import com.team15.muzimusic.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<SplashViewModel>()
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash)

        if(viewModel.isFirstTime()){
            viewModel.saveFirstTime()
            startActivity(Intent(this, GetStartedActivity::class.java))
            finish()
        } else {
            // Lấy tài khoản đã đăng nhập trước đó phủ edittext
            viewModel.getSavedData()
            if (DataLocal.IS_LOGGED_IN) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }



    }
}