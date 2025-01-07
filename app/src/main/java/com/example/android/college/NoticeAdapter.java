package com.example.android.college;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import com.example.android.college.data.noticeInfo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NoticeAdapter extends FirebaseRecyclerAdapter<noticeInfo,NoticeAdapter.NoticeViewHolder> {


    public NoticeAdapter(@NonNull FirebaseRecyclerOptions<noticeInfo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull noticeInfo model) {
        holder.noticeDate.setText(model.getNoticeDate());
        holder.noticeName.setText(model.getNoticeName());
        holder.noticeName.setOnClickListener(v -> {
            Intent pdfOpen = new Intent(holder.noticeName.getContext(),viewPdf.class);      // Handling the view option of each notice in app
            pdfOpen.putExtra("noticeUrl",model.getNoticeUrl());
            pdfOpen.putExtra("Parent Activity",1);
            pdfOpen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.noticeName.getContext().startActivity(pdfOpen);
        });
        holder.downloadNotice.setOnClickListener(v -> {     //  Handling the download option of each notice
            Intent downloadNoticeIntent = new Intent(Intent.ACTION_VIEW);
            downloadNoticeIntent.setDataAndType(Uri.parse(model.getNoticeUrl()),"application/pdf");
            holder.noticeName.getContext().startActivity(downloadNoticeIntent);
        });
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticegrid,parent,false);
        return new NoticeViewHolder(view);

    }

    static  class NoticeViewHolder extends RecyclerView.ViewHolder
    {
        TextView noticeName, noticeDate,downloadNotice;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeName = itemView.findViewById(R.id.noticeName);
            noticeDate = itemView.findViewById(R.id.noticeDate);
            downloadNotice=itemView.findViewById(R.id.download);
        }
    }
}
