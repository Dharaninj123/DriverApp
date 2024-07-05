package com.example.drivererp.ui.accounts

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.drivererp.R
import com.example.drivererp.ui.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spf = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        Handler().postDelayed({
            val accessToken = spf.getString("AccessToken", "")
            if (accessToken == "") {
                val iHome: Intent = Intent(
                    this@SplashScreenActivity,
                    LoginActivity::class.java
                )
                startActivity(iHome)
            } else {
                val iHome: Intent = Intent(
                    this@SplashScreenActivity,
                    HomeActivity::class.java
                )
                startActivity(iHome)
            }
            finish()
        }, 4000)

    }
    }

