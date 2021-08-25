package com.example.mapleleaves.ui.splash

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mapleleaves.MainActivity
import com.example.mapleleaves.databinding.ActivitySplashBinding
import kotlin.concurrent.thread


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        thread {
            Thread.sleep(2000)
            MainActivity.startMainActivity(this)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

}