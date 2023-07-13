package com.samuel.movilplag_e

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


private lateinit var lbl_corX: TextView
private lateinit var lbl_corY: TextView
private lateinit var btn_buy: ImageButton
private lateinit var btn_map: ImageButton
private lateinit var btn_tuto: ImageButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        lbl_corX = findViewById(R.id.name_robot)
        lbl_corY = findViewById(R.id.test)
        btn_buy = findViewById(R.id.btn_buy)
        btn_map = findViewById(R.id.btn_map)
        btn_tuto = findViewById(R.id.btn_tutorials)

        val robotId = "000001"
        consulta(robotId)

        btn_buy.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, buy::class.java)
            startActivity(intent)
        })

        btn_map.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, map::class.java)
            startActivity(intent)
        })

        btn_tuto.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, tutorials::class.java)
            startActivity(intent)
        })

    }

    private lateinit var requestQueue: RequestQueue


    private fun consulta(robotId: String){


        requestQueue = Volley.newRequestQueue(this)

        var url = "https://plag-api.vercel.app/api/robots/$robotId/coordinates"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                Log.d("RESPUESTA",response.toString())
                val corX = response.getString("x")
                val corY = response.getString("y")

                lbl_corX.text = "X: $corX"
                lbl_corY.text = "Y: $corY"

            },
            { error ->
                // Manejar el error
                Log.e(ContentValues.TAG, "Error en la solicitud: $error")
                lbl_corX.text = "ERROR EN LA CONSULTA"
                lbl_corY.text = "ERROR EN LA CONSULTA"
            })

        requestQueue.add(request)
    }



}


