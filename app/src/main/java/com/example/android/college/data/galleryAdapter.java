package com.example.android.college.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.college.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class galleryAdapter extends FirebaseRecyclerAdapter<String, galleryAdapter.galleryViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public galleryAdapter(@NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull galleryViewHolder holder, int position, @NonNull String model) {
        Glide.with(holder.galleryImage.getContext()).load(model).into(holder.galleryImage);
    }

    @NonNull
    @Override
    public galleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.galleryimage,parent,false);
        return new galleryViewHolder(view);
    }

    static class galleryViewHolder extends RecyclerView.ViewHolder{
        private final ImageView galleryImage;
        public galleryViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryImage=itemView.findViewById(R.id.galleryImageView);
        }
    }
}
