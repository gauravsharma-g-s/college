package com.example.android.college;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class viewPdf extends AppCompatActivity {
    private ProgressBar pdfProgress;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
               // setting status color from here supported in Api level 21 or above only
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {         // getColor supported in Api level 23 and above only
                window.setStatusBarColor(this.getColor(R.color.light_green));
            }

        pdfView = findViewById(R.id.pdfView);
        pdfProgress = findViewById(R.id.progress_pdf);
        // Getting the intent from adapter
        pdfProgress.setVisibility(View.VISIBLE);
        String noticeUrl = getIntent().getStringExtra("noticeUrl");
        int parentActivity = getIntent().getIntExtra("Parent Activity",1);
        if(parentActivity==1){
            setTitle("Notice");
        }
        else{
            setTitle("Question Paper");
        }
        new pdfOpen().execute(noticeUrl);           // Executing thw asyncTask object to open the pdf
    }

    // Downloading the pdf in background thread to display it using AsyncTask
    private class pdfOpen extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL pdfUrl = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) pdfUrl.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                Toast.makeText(viewPdf.this, "There is some problem loading the notice", Toast.LENGTH_SHORT).show();
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfProgress.setVisibility(View.INVISIBLE);
            pdfView.fromStream(inputStream).load();
        }
    }

}