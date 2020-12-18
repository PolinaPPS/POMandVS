package ru.startandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import ru.startandroid.myapplication.api.FlickrAPI;
import ru.startandroid.myapplication.api.ServiceAPI;
import ru.startandroid.myapplication.db.PhotosDB;
import ru.startandroid.myapplication.db.PhotosDao;
import ru.startandroid.myapplication.model.Photo;
import ru.startandroid.myapplication.model.Response;

public class PhotoGallery extends AppCompatActivity {

    RecyclerView rv;
    Context context;
    Response rs;
    PhotoAdapter adapter;
    PhotosDao dao;
    PhotosDB db;
    List<Photo> photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        rv = findViewById(R.id.rv_photo);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        Retrofit rf = ServiceAPI.getRetrofit();
        context = this;
        db = Room.databaseBuilder(context,PhotosDB.class,"db_photos").allowMainThreadQueries().build();
        dao = db.photoDao();
        rf.create(FlickrAPI.class).getRecent().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                rs = response.body();
                photo = rs.getPhotos().getPhoto();
                adapter  = new PhotoAdapter(photo,context,dao,rv);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView search = (SearchView)item.getActionView();
        rv = findViewById(R.id.rv_photo);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        Retrofit rt = ServiceAPI.getRetrofit();
        context=this;
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                rt.create(FlickrAPI.class).getSearchPhotos(query).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        rs = response.body();
                        photo = rs.getPhotos().getPhoto();
                        adapter = new PhotoAdapter(photo, context, dao, rv);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        photo = dao.LoadAll();
        adapter = new PhotoAdapter(photo,context,dao,rv);
        rv.setAdapter(adapter);
        return super.onOptionsItemSelected(item);
    }
}