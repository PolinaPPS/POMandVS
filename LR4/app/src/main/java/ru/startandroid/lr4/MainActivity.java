package ru.startandroid.lr4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cat murzik = new Cat();
        murzik.name = "Мурзик";
        murzik.age = 9;
        murzik.color = Color.YELLOW;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        TextView tx = findViewById(R.id.textView);
        tx.setText(gson.toJson(murzik));

        String jsonText = "{\"name\":\"Барсик\",\"color\":-16777216,\"age\":7}";
        Cat barsik = gson.fromJson(jsonText, Cat.class);
        tx.setText("Имя: " + barsik.name + " Возраст: " + barsik.age + " Цвет: ");
        View cat_color = findViewById(R.id.view);
        cat_color.setBackgroundColor(barsik.color);
    }
}