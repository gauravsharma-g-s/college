package com.example.android.college.papers;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.college.QuestionPapers;
import com.example.android.college.R;
import com.example.android.college.viewPdf;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class papersAdapter extends FirebaseRecyclerAdapter<paperInfo,papersAdapter.paperAdapterViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public papersAdapter(@NonNull FirebaseRecyclerOptions<paperInfo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull paperAdapterViewHolder holder, int position, @NonNull paperInfo model) {
        holder.paperNameTextView.setText(model.getPaperName());
        holder.paperYearView.setText(String.valueOf(model.getPaperYear()));
        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pdfOpen = new Intent(holder.paperNameTextView.getContext(), viewPdf.class);      // Handling the view option of each paper in app
                pdfOpen.putExtra("noticeUrl",model.getPaperUrl());
                pdfOpen.putExtra("Parent Activity",2);
                pdfOpen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.paperNameTextView.getContext().startActivity(pdfOpen);
            }
        });

        holder.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent downloadPaperIntent = new Intent(Intent.ACTION_VIEW);
                downloadPaperIntent.setDataAndType(Uri.parse(model.getPaperUrl()),"application/pdf");
                holder.paperNameTextView.getContext().startActivity(downloadPaperIntent);
            }
        });
    }

    @NonNull
    @Override
    public paperAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.papergrid,parent,false);
        return new paperAdapterViewHolder(view);
    }

    static class paperAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView paperNameTextView,paperYearView ;
        Button viewButton, downloadButton;
        public paperAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            paperNameTextView = itemView.findViewById(R.id.paper_name);
            paperYearView = itemView.findViewById(R.id.year_view);
            viewButton = itemView.findViewById(R.id.view_button);
            downloadButton = itemView.findViewById(R.id.download_button);
        }
    }
}
