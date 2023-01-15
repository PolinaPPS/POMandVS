package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val resultTextView: TextView = findViewById(R.id.textView_answer)
        val trueAnswers: String? = intent.getStringExtra("trueAnswers")
        resultTextView.text = trueAnswers
    }

    fun onClickButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}