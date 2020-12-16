package ru.startandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    TextView text;
    ImageView photo;
    Context context;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
