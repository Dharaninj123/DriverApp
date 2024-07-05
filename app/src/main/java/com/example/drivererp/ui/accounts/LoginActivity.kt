package com.example.drivererp.ui.accounts

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drivererp.R
import com.example.drivererp.ui.HomeActivity


class LoginActivity : AppCompatActivity() {

    private var loginMobileNumber: EditText? = null
    private var loginPassword: EditText? = null
    private var loginButton: Button? = null
    private var forgotPasswordButton: Button? = null
    private var signupButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginMobileNumber = findViewById(R.id.login_mobile_number)
        loginPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.Login_button)
        forgotPasswordButton = findViewById(R.id.forgotpassword)
        signupButton = findViewById(R.id.signup)


        loginButton?.setOnClickListener { v ->
            val mobileNumber = loginMobileNumber?.text.toString().trim { it <= ' ' }
            val password = loginPassword?.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(mobileNumber) || TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@LoginActivity,
                    "Mobile Number/Password Required.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val isValid = validateInfo(mobileNumber, password)
                if (isValid) {
                    val blinkAnimation = AnimationUtils.loadAnimation(this@LoginActivity, R.anim.blink_animation)
                    v.startAnimation(blinkAnimation)

                    blinkAnimation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {
                            // Perform login or navigate to the next screen
                        }
                        override fun onAnimationRepeat(animation: Animation) {}
                    })
                }
            }
        }

        forgotPasswordButton?.setOnClickListener {
            val intent = Intent(this@LoginActivity, SendOTPActivity::class.java)
            startActivity(intent)
        }

        signupButton?.setOnClickListener {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun storeTokenLocally(token: String) {
        val editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit()
        editor.putString("FCMToken", token)
        editor.apply()
    }

    private fun validateInfo(number: String, password: String): Boolean {
        return when {
            number.isEmpty() -> {
                loginMobileNumber?.apply {
                    requestFocus()
                    error = "FIELD CANNOT BE EMPTY"
                }
                false
            }
            number.length != 10 -> {
                loginMobileNumber?.apply {
                    requestFocus()
                    error = "INVALID MOBILE NUMBER"
                }
                false
            }
            !number.matches("[0-9]+".toRegex()) -> {
                loginMobileNumber?.apply {
                    requestFocus()
                    error = "ENTER ONLY NUMBERS"
                }
                false
            }
            password.isEmpty() -> {
                loginPassword?.apply {
                    requestFocus()
                    error = "PASSWORD REQUIRED"
                }
                false
            }
            password.length < 6 -> {
                loginPassword?.apply {
                    requestFocus()
                    error = "MINIMUM 6 CHARACTERS REQUIRED"
                }
                false
            }
            !password.matches(".*\\d.*".toRegex()) -> {
                loginPassword?.apply {
                    requestFocus()
                    error = "PASSWORD MUST CONTAIN AT LEAST ONE DIGIT"
                }
                false
            }
            !password.matches(".*[a-zA-Z].*".toRegex()) -> {
                loginPassword?.apply {
                    requestFocus()
                    error = "PASSWORD MUST CONTAIN AT LEAST ONE LETTER"
                }
                false
            }
            !password.matches(".*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?].*".toRegex()) -> {
                loginPassword?.apply {
                    requestFocus()
                    error = "PASSWORD MUST CONTAIN AT LEAST ONE SPECIAL CHARACTER"
                }
                false
            }
            else -> true
        }
    }

    private fun updateNavBar(studentName: String?) {
        val navBarTextView = findViewById<TextView>(R.id.nameView)
        navBarTextView.text = studentName
    }
}
