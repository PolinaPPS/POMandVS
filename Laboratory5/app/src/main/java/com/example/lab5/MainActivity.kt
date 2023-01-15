package com.example.lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var countPhotos: EditText;
    lateinit var radioButton9x12: RadioButton;
    lateinit var radioButton10x15: RadioButton;
    lateinit var radioButton18x24: RadioButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countPhotos = findViewById(R.id.countPhotos)
        radioButton9x12 = findViewById(R.id.radioButton_9x12);
        radioButton10x15 = findViewById(R.id.radioButton_10x15);
        radioButton18x24 = findViewById(R.id.radioButton_18x24);

    }
    fun onClickButton(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        var count: Int = countPhotos.text.toString().toInt();
        if (radioButton9x12.isChecked) count *= 100;
        if (radioButton10x15.isChecked) count *= 150;
        if (radioButton18x24.isChecked) count *= 300;
        intent.putExtra("price", count)
        startActivity(intent)
    }
}