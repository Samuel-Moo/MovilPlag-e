package com.samuel.movilplag_e

import android.content.ContentValues
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


private lateinit var lbl_corX: TextView
private lateinit var lbl_corY: TextView
private lateinit var btn_buy: ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lbl_corX = findViewById(R.id.name_robot)
        lbl_corY = findViewById(R.id.test)
        btn_buy = findViewById(R.id.btn_buy)


        Consulta()

        btn_buy.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, buy::class.java)
            startActivity(intent)
        })

    }

    private lateinit var requestQueue: RequestQueue


    private fun Consulta(){


        requestQueue = Volley.newRequestQueue(this)

        var url = "https://plag-api.vercel.app/api/robots/000001/coordinates"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                Log.d("RESPUESTA",response.toString())
                val corX = response.getString("x")
                val corY = response.getString("y")

                lbl_corX.text = "X: ${corX}"
                lbl_corY.text = "Y: ${corY}"

            },
            { error ->
                // Manejar el error
                Log.e(ContentValues.TAG, "Error en la solicitud: $error")

            })

        requestQueue.add(request)
    }

}


