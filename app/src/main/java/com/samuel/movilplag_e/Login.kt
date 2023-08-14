package com.samuel.movilplag_e

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.SignInButton

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var signIn = findViewById<SignInButton>(R.id.sg_button)

    }
}