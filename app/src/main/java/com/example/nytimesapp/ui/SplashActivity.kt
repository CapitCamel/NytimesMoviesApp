package com.example.nytimesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.nytimesapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        newAct()
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }

    fun newAct() = lifecycleScope.launch {
        delay(3000)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}