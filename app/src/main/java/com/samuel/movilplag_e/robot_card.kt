package com.samuel.movilplag_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class robot_card : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.robot_card)

        val btn_loc = findViewById<Button>(R.id.btn_go_to_map)

        btn_loc.setOnClickListener {
            openMapActivity()
        }
    }

    private fun openMapActivity() {
        val intent = Intent(this, map::class.java)
        startActivity(intent)
    }
}
