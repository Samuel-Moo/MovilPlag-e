package com.samuel.movilplag_e

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.samuel.movilplag_e.databinding.ActivityMapBinding

class map : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding
    private lateinit var requestQueue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        requestQueue = Volley.newRequestQueue(this)

        val url = "https://plag-7cpancfkj-0marcontreras.vercel.app/api/robots/000001/coordinates"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                Log.d("RESPUESTA",response.toString())
                val corX = response.getDouble("x")
                val corY = response.getDouble("y")


                // Add a marker in Sydney and move the camera
                val robot = LatLng(corX, corY)
                mMap.addMarker(MarkerOptions().position(robot).title("Robot"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(robot))

            },
            { error ->
                // Manejar el error
                Log.e(ContentValues.TAG, "Error en la solicitud: $error")

            })

        requestQueue.add(request)


    }
}