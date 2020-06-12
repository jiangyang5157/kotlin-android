package com.gmail.jiangyang5157.example_biometric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        check28()
        init28()
        btn_auth_28.setOnClickListener { auth28() }
    }

    private fun check28() {
        when (BiometricManager.from(this).canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                Log.d("####", "App can authenticate using biometrics.")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Log.e("####", "No biometric features available on this device.")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Log.e("####", "Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Log.e(
                    "####", "The user hasn't associated " +
                        "any biometric credentials with their account."
                )
        }
    }

    private fun init28() {
        biometricPrompt = BiometricPrompt(
            this,
            Executors.newSingleThreadExecutor(),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        Log.d("####", "onAuthenticationError\nUser clicked negative button")
                    } else {
                        Log.d("####", "onAuthenticationError\n$errorCode\n$errString")
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Log.d("####", "onAuthenticationSucceeded\n$result")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.d("####", "onAuthenticationFailed")
                }
            })
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("This is Title: Biometric login")
            .setSubtitle("This is subtitle: Using your biometric credential")
            .setDescription("This is Description")
            .setNegativeButtonText("Cancel")
            .setConfirmationRequired(true)
            .build()
    }

    private fun auth28() {
        biometricPrompt.authenticate(promptInfo)
    }
}
