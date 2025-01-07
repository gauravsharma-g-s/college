package com.example.android.college.papers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.college.R;
import com.example.android.college.WrapLinearLayoutManager;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class semester2 extends Fragment {
    private papersAdapter mySem2PaperAdapter;

    public semester2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_semester1, container, false);

        RecyclerView papersRecyclerView = rootView.findViewById(R.id.sem1_recView);

        FirebaseRecyclerOptions<paperInfo> sem2Questionpapers = new FirebaseRecyclerOptions.Builder<paperInfo>().setQuery(FirebaseDatabase.
                getInstance().getReference().child("papers").child("semester2"), paperInfo.class).build();
        WrapLinearLayoutManager linearLayoutManager = new WrapLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        papersRecyclerView.setLayoutManager(linearLayoutManager);
        mySem2PaperAdapter = new papersAdapter(sem2Questionpapers);
        papersRecyclerView.setAdapter(mySem2PaperAdapter);
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        mySem2PaperAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mySem2PaperAdapter.stopListening();
    }

}