package com.example.waterdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setTitle(R.string.app_feedback);
    }
}
