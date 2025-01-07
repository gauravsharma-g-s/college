package com.example.android.college.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.college.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class teachersAdapter extends FirebaseRecyclerAdapter<dataHolder, teachersAdapter.teacherViewHolder> {
    public teachersAdapter(@NonNull FirebaseRecyclerOptions<dataHolder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull teacherViewHolder holder, int position, @NonNull dataHolder model) {
        holder.teacherNameView.setText(model.getName());
        holder.teacherQualificationView.setText(model.getQualification());
        if (model.getImageUrl() != null) {
            Glide.with(holder.teacherImageView.getContext()).load(model.getImageUrl()).into(holder.teacherImageView);
        } else {
            holder.teacherImageView.setImageResource(R.drawable.sample_teacher_image);
        }

    }

    @NonNull
    @Override
    public teacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachers_list, parent, false);
        return new teacherViewHolder(view);
    }

    class teacherViewHolder extends RecyclerView.ViewHolder {
        CircleImageView teacherImageView;
        TextView teacherNameView,teacherQualificationView;

        public teacherViewHolder(View itemView) {
            super(itemView);
            teacherImageView = itemView.findViewById(R.id.profile_image);
            teacherNameView = itemView.findViewById(R.id.teacher_name);
            teacherQualificationView = itemView.findViewById(R.id.teacherDegree);
        }

    }
}
