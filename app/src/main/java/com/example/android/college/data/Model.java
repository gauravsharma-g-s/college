package com.example.android.college.data;

public class Model {
    private String mUri;

    public Model() {

    }

    public Model(String uri) {
        mUri = uri;
    }

    public String getImageUrl() {
        return mUri;
    }

    public void setImageUrl(String uri) {
        mUri = uri;
    }
}
