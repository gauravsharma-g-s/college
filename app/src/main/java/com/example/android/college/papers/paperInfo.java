package com.example.android.college.papers;

public class paperInfo {
    private String paperName;

    private String paperUrl;

    private int paperYear;
    public paperInfo() {
    }

    public paperInfo(String paperName, String paperUrl,int paperYear) {
        this.paperName = paperName;
        this.paperYear = paperYear;
        this.paperUrl = paperUrl;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }


    public int getPaperYear() {
        return paperYear;
    }

    public void setPaperYear(int paperYear) {
        this.paperYear = paperYear;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }
}
