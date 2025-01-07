package com.example.android.college;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import com.example.android.college.papers.PapersFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class QuestionPapers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#85ED68")));
        setContentView(R.layout.activity_question_papers);
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout =findViewById(R.id.tab_layout);
        PapersFragmentPagerAdapter pagerAdapter  = new PapersFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}