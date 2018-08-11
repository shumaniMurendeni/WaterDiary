package com.example.waterdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import Utilities.DiaryEntry;
import Utilities.DiaryEntryAdapter;

public class DiaryActivity extends AppCompatActivity {

    List<DiaryEntry> entries = DiaryEntryAdapter.diaryEntries;
    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //index = getParentActivityIntent().getIntExtra("selected_item",0);
        updateButtonStatus(index);
        if (entries != null && entries.size()>0) {
            DisplayDetails(index);
        }
    }

    public void onNextClick(View view){

        if (index < entries.size()-1) {
            index++;
            DisplayDetails(index);
            updateButtonStatus(index); //PLease update this later
        }
    }

    public void onPreviousClick(View view){

        if (index>0){
            index--;
            DisplayDetails(index);
            updateButtonStatus(index);
        }
    }

    public void onOverviewButtonClick(View view){
        Button overviewButton = findViewById(R.id.overviewbutton);
        finish();
        //DiaryEntryAdapter.saveEntries();
    }

    public void onCalculatorButtonClick(View view){
        finish();
        startActivity(new Intent(DiaryActivity.this, CalculatorActivity.class));
        //DisplayDetails(index);
    }

    private void updateButtonStatus(int position) {
        if((position==0)|| (position == entries.size()-1)){
            if (position==0){
                findViewById(R.id.previousbutton).setVisibility(View.INVISIBLE);
            }
            if (position == entries.size()-1){
                findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
            }
        }
        else{
            findViewById(R.id.previousbutton).setVisibility(View.VISIBLE);
            findViewById(R.id.nextbutton).setVisibility(View.VISIBLE);

        }
    }

    private void DisplayDetails(int index) {
        DiaryEntry diaryEntry = entries.get(index);
        TextView textView = findViewById(R.id.DiaryEntryTView);
        textView.setText(String.format("%-12s : %10s\n", getString(R.string.date),diaryEntry.getDate()));
        textView.append(diaryEntry.getDetails());
    }
}
