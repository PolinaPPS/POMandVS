package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val price:Int = intent.getIntExtra("price",0)
        priceTextView.text = price.toString()
    }
}