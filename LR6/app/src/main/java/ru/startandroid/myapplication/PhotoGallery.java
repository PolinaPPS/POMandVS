package ru.startandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import ru.startandroid.myapplication.api.FlickrAPI;
import ru.startandroid.myapplication.api.ServiceAPI;
import ru.startandroid.myapplication.model.Photo;
import ru.startandroid.myapplication.model.Response;

public class PhotoGallery extends AppCompatActivity {

    RecyclerView rv;
    Context context;
    Response rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        rv = findViewById(R.id.rv_photo);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        Retrofit rf = ServiceAPI.getRetrofit();
        context = this;
        rf.create(FlickrAPI.class).getRecent().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                rs = response.body();
                List<Photo> photo = rs.getPhotos().getPhoto();
                PhotoAdapter pha = new PhotoAdapter(photo,context);
                rv.setAdapter(pha);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}