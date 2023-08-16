package com.samuel.movilplag_e

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class buy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        val webViewAmazon: WebView = findViewById(R.id.webview1)
        val webViewML: WebView = findViewById(R.id.webview2)

        webViewAmazon.loadUrl("https://www.amazon.com.mx/")

        webViewML.loadUrl("https://www.mercadolibre.com.mx/")
    }
}