package com.gmail.jiangyang5157.example_biometric

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var biometricPrompt: BiometricPrompt

    private lateinit var fingerprintManager: FingerprintManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init23()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            init28()
        }

        btn_auth_23.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                auth23()
            } else {
                Log.d("####", "auth23 requires Android M")
            }
        }
        btn_auth_28.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                auth28()
            } else {
                Log.d("####", "auth28 requires Android P")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun init23() {
        fingerprintManager = getSystemService(FingerprintManager::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun init28() {
        biometricPrompt = BiometricPrompt
            .Builder(this)
            .setNegativeButton(
                "NegativeButton",
                this@MainActivity.mainExecutor,
                DialogInterface.OnClickListener { _, _ ->
                    Log.d("####", "BiometricPrompt NegativeButton onClicked")
                }
            )
            .setTitle("This is title")
            .setSubtitle("This is subtitle")
            .setDescription("This is description")
            .setConfirmationRequired(true)
            .setDeviceCredentialAllowed(false)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun auth23() {
        val cancel = CancellationSignal()
        val dialog = FingerprintDialog(object :
            FingerprintDialog.Callback {
            override fun onDismiss() {
                if (!cancel.isCanceled) {
                    cancel.cancel()
                }
            }
        })
        dialog.show(supportFragmentManager, FingerprintDialog.TAG)

        fingerprintManager.authenticate(
            null,
            cancel,
            0,
            object : FingerprintManager.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    dialog.setInfo("onAuthenticationError\n\n$errorCode\n$errString")
                }

                override fun onAuthenticationFailed() {
                    dialog.setInfo("onAuthenticationFailed")
                }

                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                    dialog.setInfo("onAuthenticationHelp\n\n$helpCode\n$helpString")
                }

                override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
                    dialog.setInfo("onAuthenticationSucceeded\n\n$result")
                }
            },
            null
        )
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun auth28() {
        val cancel = CancellationSignal()
        biometricPrompt.authenticate(
            cancel,
            this@MainActivity.mainExecutor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    Log.d("####", "onAuthenticationError\n\n$errorCode\n$errString")
                    cancel.cancel()
                }

                override fun onAuthenticationFailed() {
                    Log.d("####", "onAuthenticationFailed")
                }

                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                    Log.d("####", "onAuthenticationHelp\n\n$helpCode\n$helpString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    Log.d("####", "onAuthenticationSucceeded\n\n$result")
                }
            })
    }
}
