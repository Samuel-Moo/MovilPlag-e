package com.samuel.movilplag_e

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class Login : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var sharedPreferences: SharedPreferences
    private var isInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("298722206986-4jo87fbqtcffdl88t1qq1sa40b0o18os.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this@Login, gso)
        val googleLoginButton = findViewById<Button>(R.id.google_login_btn)
        googleLoginButton.setOnClickListener {
            signIn()
        }
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        Log.i("SharedPreferences", sharedPreferences.toString())

        isInitialized = true
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, 9001
        )

    }

    fun signOut() {
        if (isInitialized) { // Check if mGoogleSignInClient has been initialized
            mGoogleSignInClient.signOut()
                .addOnCompleteListener(this) {

                    SharedData().clean(this)
                }
        }
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID", googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)

            val editor = sharedPreferences.edit()
            editor.putString("google_id", googleId)
            editor.apply()
            makePostRequest(googleId, googleFirstName)


            /* myIntent.putExtra("google_id", googleId)
            myIntent.putExtra("google_first_name", googleFirstName)
            myIntent.putExtra("google_last_name", googleLastName)
            myIntent.putExtra("google_email", googleEmail)
            myIntent.putExtra("google_profile_pic_url", googleProfilePicURL)
            myIntent.putExtra("google_id_token", googleIdToken)*/
        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }

    private fun makePostRequest(code: String, display: String) {
        val url = "https://plag-7cpancfkj-0marcontreras.vercel.app/api/users/${code}/${display}"

        val queue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("POST_REQUEST", "Response: $response")

                Toast.makeText(this@Login, "Successful. Redirecting",
                    Toast.LENGTH_LONG).show()
                val myIntent = Intent(this, MainActivity::class.java)

                this.startActivity(myIntent)
                finish()
            },
            { error ->
                Log.e("POST_REQUEST", "Error: $error")
                Toast.makeText(this@Login, "User Is Already Registered",
                    Toast.LENGTH_LONG).show()
                val myIntent = Intent(this, MainActivity::class.java)

                this.startActivity(myIntent)
            })

        queue.add(stringRequest)
    }

}

