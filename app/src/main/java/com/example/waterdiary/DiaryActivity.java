package com.example.waterdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        setTitle(R.string.DiaryActivity);

        updateButtonStatus(index);
        if (entries != null && entries.size()>0) {
            DisplayDetails(index);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.help:
                onPause();
                startActivity(new Intent(this,HelpActivity.class));
                break;

            case R.id.review:
                onPause();
                startActivity(new Intent(this,ReviewActivity.class));
                break;

            default:
                Toast.makeText(this,"Hello",Toast.LENGTH_LONG);
        }
        return super.onOptionsItemSelected(item);
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
        OverviewActivity.lastSeen = 0;
        finish();
    }

    public void onCalculatorButtonClick(View view){
        finish();
        Intent intent = new Intent(DiaryActivity.this, CalculatorActivity.class);
        intent.putExtra("class",2);
        OverviewActivity.lastSeen = 2;
        startActivity(intent);
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
