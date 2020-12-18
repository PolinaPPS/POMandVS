package ru.startandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.startandroid.myapplication.model.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private List<Photo> photoList;

    private TextView text;
    private ImageView photo;
    Context context;

    public PhotoAdapter(List<Photo> list, Context con) {
        photoList = list;
        this.context = con;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder{
        public PhotoHolder (View item) {
            super(item);
            text = item.findViewById(R.id.textView);
            photo = item.findViewById(R.id.imageView);
        }
    }
    @NonNull
    @Override
    public PhotoAdapter.PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.rv_photo,parent,false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoHolder holder, int position) {
        String photoName = photoList.get(position).getOwner();
       // String photoName = photoList.get(position).getUrl_s();
        text.setText(photoName);
        Picasso.with(context).load(photoList.get(position).getUrl_s()).into(photo);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
