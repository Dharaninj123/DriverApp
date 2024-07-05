package com.example.drivererp.ui.accounts

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.drivererp.R

class SendOTPActivity : AppCompatActivity() {

    private var inputMobile: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.send_otp_activity)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputMobile = findViewById(R.id.inputMobile)
        val buttonGetOTP = findViewById<Button>(R.id.buttonGetotp)
        buttonGetOTP.setOnClickListener { view ->
            if (validateMobileNumber()) {
                // Start the blink animation
                val blinkAnimation = AnimationUtils.loadAnimation(
                    this@SendOTPActivity,
                    R.anim.blink_animation
                )
                view.startAnimation(blinkAnimation)

                // Proceed to get OTP after the animation ends
                blinkAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {
                        // Optional: actions to perform when the animation starts
                    }

                    override fun onAnimationEnd(animation: Animation) {
                        val mobileNumber = inputMobile?.text.toString().trim { it <= ' ' }
                        // Proceed to the next activity or handle OTP generation logic here
                        Toast.makeText(this@SendOTPActivity, "OTP Sent to $mobileNumber", Toast.LENGTH_SHORT).show()
                    }

                    override fun onAnimationRepeat(animation: Animation) {
                        // Optional: actions to perform when the animation repeats
                    }
                })
            } else {
                Toast.makeText(this@SendOTPActivity, "Invalid Mobile Number.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateMobileNumber(): Boolean {
        val mobileNumber = inputMobile?.text.toString().trim { it <= ' ' }
        if (mobileNumber.length != 10) {
            inputMobile?.error = "Please enter a valid 10-digit mobile number"
            inputMobile?.requestFocus()
            return false
        }
        return true
    }
}
