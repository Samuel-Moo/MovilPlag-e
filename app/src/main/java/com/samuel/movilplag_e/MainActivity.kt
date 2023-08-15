package com.samuel.movilplag_e

import MyAdapter
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.common.api.ApiException
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val googleId = SharedData().GoogleId(this@MainActivity)
        if (googleId.isEmpty()) {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }
        Log.i("GoogleID", googleId)

        listView = findViewById(R.id.listView)


        // Realizar la solicitud HTTP con Volley

        val url = "https://plag-7cpancfkj-0marcontreras.vercel.app/api/users/${googleId}/robots"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    // Procesar la respuesta JSON y construir la lista de objetos MyItem
                    val items: MutableList<UserRobotDataClass> = mutableListOf()
                    for (i in 0 until response.length()) {
                        val itemJson = response.getJSONObject(i)
                        val code = itemJson.getString("code")
                        val name = itemJson.getString("Rname")
                        val waste = itemJson.getInt("waste")
                        val locationJson = itemJson.getJSONObject("location")
                        val locationX = locationJson.getDouble("x")
                        val locationY = locationJson.getDouble("y")

                        val myItem = UserRobotDataClass(code, name, waste, locationX, locationY)
                        items.add(myItem)
                    }

                    // Inicializar el adaptador y configurar el ListView
                    adapter = MyAdapter(this, items)
                    listView.adapter = adapter
                } catch (e: JSONException) {
                    Log.e("MainActivity", "Error al procesar la respuesta JSON", e)
                }
            },
            { error ->
                Log.e("MainActivity", "Error en la solicitud HTTP", error)
            }
        )

        requestQueue.add(request)

        val btn_buy = findViewById<ImageButton>(R.id.btn_buy)
        val btn_map = findViewById<ImageButton>(R.id.btn_map)
        val btn_tuto = findViewById<ImageButton>(R.id.btn_tutorials)
        val btn_add = findViewById<ImageButton>(R.id.btn_add)
        val btn_logOut = findViewById<Button>(R.id.btn_log_out)
        //val btn_go_to_map = findViewById<Button>(R.id.btn_go_to_map)

        //val userId = "117285217356016446690"
        //consulta(userId, x_display, y_display, name_robot, waste_display, code_display)

        btn_logOut.setOnClickListener{
            try {
                val login = Login()
                login.signOut()
                val settings: SharedPreferences =
                    this.getSharedPreferences("MyPrefs", MODE_PRIVATE)
                settings.edit().clear().apply()
                this.cacheDir.deleteRecursively()
                Toast.makeText(this@MainActivity, "Logout Succesful",
                    Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, Login::class.java)
                startActivity(intent)
            } catch (e: ApiException){
                Log.e("Error On Logout", "$e")
                Toast.makeText(this@MainActivity, "Could not logout",
                    Toast.LENGTH_LONG).show()
            }

        }


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

        btn_add.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@MainActivity, addRobot::class.java)
            startActivity(intent)
        })

        /*btn_go_to_map.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, map::class.java )
            startActivity(intent)
        })*/

    }






}


