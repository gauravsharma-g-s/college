package com.example.android.college;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;


import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;


import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import android.content.Intent;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.android.college.data.dataHolder;
import com.example.android.college.data.galleryAdapter;
import com.example.android.college.data.noticeInfo;
import com.example.android.college.data.teachersAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;


import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ScrollView sc;
    private teachersAdapter mAdapter;
    private RecyclerView noticesRecyclerView,galleryRecyclerView;
    private NoticeAdapter noticeAdapter;
    private galleryAdapter stringGalleryAdapter;
    private Button ViewPapers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hiding the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);
        noticesRecyclerView=findViewById(R.id.recView);
        Button noticeBoardButton = findViewById(R.id.noticeBoard);
        ImageSlider imageslider = findViewById(R.id.image_slider);
        ViewPapers = findViewById(R.id.papersButton);
        sc = findViewById(R.id.scrollView);
        RecyclerView teacherDisplayView = findViewById(R.id.teachers_list);

        Button button = findViewById(R.id.teachers_button);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        images.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        images.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        images.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        images.add(new SlideModel(R.drawable.image5, ScaleTypes.FIT));
        imageslider.setImageList(images, ScaleTypes.FIT);



    // Clicking on Teachers Button
        button.setOnClickListener(v -> sc.smoothScrollTo(0, (int) teacherDisplayView.getY()));
        RecyclerView.LayoutManager manager = new WrapLinearLayoutManager(this, WrapLinearLayoutManager.HORIZONTAL, false);
        teacherDisplayView.setLayoutManager(manager);

        // Fetching the Teachers data
        FirebaseRecyclerOptions<dataHolder> options = new FirebaseRecyclerOptions.Builder<dataHolder>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("teachers"), dataHolder.class).build();

        // Creating and populating views for all items fetched into options
        mAdapter = new teachersAdapter(options);
        teacherDisplayView.setAdapter(mAdapter);
        //Handling action with NoticeBoardButton
        noticeBoardButton.setOnClickListener(v -> sc.smoothScrollBy(0,(int)noticesRecyclerView.getY()));

        FirebaseRecyclerOptions<noticeInfo> notices = new FirebaseRecyclerOptions.Builder<noticeInfo>().setQuery(FirebaseDatabase.getInstance().getReference()
                .child("notices").limitToLast(15), noticeInfo.class).build();
        WrapLinearLayoutManager linearLayoutManager = new WrapLinearLayoutManager(this,RecyclerView.HORIZONTAL,true);
        linearLayoutManager.setStackFromEnd(true);
        noticesRecyclerView.setLayoutManager(linearLayoutManager);

        noticeAdapter = new NoticeAdapter(notices);
        noticesRecyclerView.setAdapter(noticeAdapter);

        // Handling the Gallery Option
        Button galleryButton = findViewById(R.id.galleryButton);
        galleryRecyclerView=findViewById(R.id.recViewGallery);
        galleryButton.setOnClickListener(v -> sc.smoothScrollTo(0,(int)galleryRecyclerView.getY()));

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_EVENLY);
        galleryRecyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<String > galleryImages = new FirebaseRecyclerOptions.Builder<String>().setQuery(FirebaseDatabase.getInstance().getReference()
                .child("gallery"),String.class).build();

        stringGalleryAdapter = new galleryAdapter(galleryImages);
        galleryRecyclerView.setAdapter(stringGalleryAdapter);

        // Opening the college site link
        TextView collegeSiteLink = findViewById(R.id.collegeSiteLink);
        collegeSiteLink.setMovementMethod(LinkMovementMethod.getInstance());

        /*
        Handling the calling & Mailing Clicks here
         */
        LinearLayout phone, email;
        phone=findViewById(R.id.calling);
        email=findViewById(R.id.mailing);
        phone.setOnClickListener(v -> {
            String number = "01892222215";
            Intent callIntent = new Intent();
            callIntent.setAction(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+number));
            try{
                startActivity(callIntent);
            }
            catch (SecurityException e){
                Toast.makeText(MainActivity.this,"Unable to open Dialer",Toast.LENGTH_SHORT).show();
            }

        });

        email.setOnClickListener(v -> {
            Intent mailIntent = new Intent();
            mailIntent.setAction(Intent.ACTION_SENDTO);
            mailIntent.setData(Uri.parse("mailto:"+"gdcbtechcs@gmail.com"));
           try{
                startActivity(mailIntent);
            }
            catch(Exception e){
                Toast.makeText(MainActivity.this,"No App Found",Toast.LENGTH_SHORT).show();
            }
        });

        Button aboutButton=findViewById(R.id.button_about);
        aboutButton.setOnClickListener(v -> sc.smoothScrollTo(0,(int)phone.getY()));

        // Opening the screen for Viewing question papers
        ViewPapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuestionPapers = new Intent(MainActivity.this,QuestionPapers.class);
                startActivity(openQuestionPapers);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    noticeAdapter.startListening();
    stringGalleryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
       noticeAdapter.stopListening();
       stringGalleryAdapter.stopListening();
    }
}