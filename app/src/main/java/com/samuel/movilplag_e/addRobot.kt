package com.samuel.movilplag_e


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.common.api.ApiException


class addRobot : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_robot)

        val codeEditText: EditText = findViewById(R.id.codeEditText)
        val clickButton: Button = findViewById(R.id.clickButton)

        clickButton.setOnClickListener {
            val code = codeEditText.text.toString()
            makePostRequest(code)
        }
    }

    private fun makePostRequest(code: String) {

        var googleId = ""
        try {
            googleId = SharedData().GoogleId(this@addRobot)
        } catch (e: ApiException){
            Log.e("Error", "An error occurred while retrieving Google ID: ${e.message}")
        }
        val baseUrl = "https://plag-7cpancfkj-0marcontreras.vercel.app/api/users/${googleId}/link/${code}"
        val url = "$baseUrl$code"

        val queue: RequestQueue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Request.Method.POST, baseUrl,
            Response.Listener { response ->
                Log.d("POST_REQUEST", "Response: $response")

                Toast.makeText(this@addRobot, "Successful. Redirecting",
                    Toast.LENGTH_LONG).show();

                val intent = Intent(this@addRobot, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            Response.ErrorListener { error ->
                Log.e("POST_REQUEST", "Error: $error")
                Toast.makeText(this@addRobot, "Robot not found",
                    Toast.LENGTH_LONG).show();
            }) {}

        queue.add(stringRequest)
    }
}
