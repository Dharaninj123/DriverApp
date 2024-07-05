package com.example.drivererp.ui.accounts

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class VerifyOTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.verify_otp_activity)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val inputCode1 = findViewById<EditText>(R.id.inputCode1)
        val inputCode2 = findViewById<EditText>(R.id.inputCode2)
        val inputCode3 = findViewById<EditText>(R.id.inputCode3)
        val inputCode4 = findViewById<EditText>(R.id.inputCode4)
        val buttonVerify = findViewById<Button>(R.id.buttonVerify)

        // Move focus to next input code automatically
        setEditTextChangeListener(inputCode1, inputCode2)
        setEditTextChangeListener(inputCode2, inputCode3)
        setEditTextChangeListener(inputCode3, inputCode4)

        buttonVerify.setOnClickListener { view ->
            val code1 = inputCode1.text.toString().trim { it <= ' ' }
            val code2 = inputCode2.text.toString().trim { it <= ' ' }
            val code3 = inputCode3.text.toString().trim { it <= ' ' }
            val code4 = inputCode4.text.toString().trim { it <= ' ' }

            if (code1.isNotEmpty() && code2.isNotEmpty() && code3.isNotEmpty() && code4.isNotEmpty()) {
                // Start the blink animation
                val blinkAnimation = AnimationUtils.loadAnimation(
                    this@VerifyOTPActivity,
                    R.anim.blink_animation
                )
                view.startAnimation(blinkAnimation)

                // Proceed to verify OTP after the animation ends
                blinkAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {
                        // Optional: actions to perform when the animation starts
                    }

                    override fun onAnimationEnd(animation: Animation) {
                        val otp = code1 + code2 + code3 + code4
                        // Handle OTP verification logic here
                        handleOTPVerification(otp)
                    }

                    override fun onAnimationRepeat(animation: Animation) {
                        // Optional: actions to perform when the animation repeats
                    }
                })
            } else {
                // Display a message indicating all fields must be filled
                Toast.makeText(this@VerifyOTPActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleOTPVerification(otp: String) {
        // This function handles the OTP verification logic
        // For example, you can compare the OTP with a pre-defined value or move to the next screen
        if (otp == "1234") {
            Toast.makeText(this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
            // Proceed to the next activity or home screen
        } else {
            Toast.makeText(this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    // Method to move focus to the next EditText when a digit is entered
    private fun setEditTextChangeListener(currentEditText: EditText, nextEditText: EditText) {
        currentEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    // If a digit is entered, move focus to the next EditText
                    nextEditText.requestFocus()
                }
            }
        })
    }
}
