package com.samuel.movilplag_e

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Vector

class tutorials : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    private var youtubeVideos = Vector<youTubeVideos>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorials)
        title = "KotlinApp"
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        youtubeVideos.add(youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/D0hp7uXmC0Q\" frameborder=\"0\" allow=\"accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"))
        youtubeVideos.add(youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8WPD7mGIWVw\" frameborder=\"0\" allow=\"accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"))
        youtubeVideos.add(youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WX6SRLPj8CU\" frameborder=\"0\" allow=\"accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"))

        val videoAdapter = VideoAdapter(youtubeVideos)
        recyclerView.adapter = videoAdapter
    }
}