package com.example.waterdiary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database.DiaryEntryDataSource;
import Utilities.DiaryEntry;
import Utilities.DiaryEntryAdapter;

import static Utilities.DiaryEntryAdapter.*;

public class OverviewActivity extends AppCompatActivity {

    static final String Diary_Entry = "Diary Entries";
    public static Context mcontext;
    List<DiaryEntry> diaryEntries = null;
    public static Bundle appState;
    public static DiaryEntryDataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        if (savedInstanceState == null) {
            appState = new Bundle();
        }else{appState = savedInstanceState;}
        mcontext= this;
        mDataSource = new DiaryEntryDataSource(this);
        mDataSource.open();
        diaryEntries = mDataSource.getAllEntries();
        DiaryEntryAdapter.diaryEntries = diaryEntries;
        if (diaryEntries!=null) {
            updateSummary();
            setUpListView();
        }


    }

    public void onCalculatorButtonClick(View view) {
        //final Button calculator = findViewById(R.id.calculator_launcher_button);

        Intent intent = new Intent(OverviewActivity.this,DiaryActivity.class);

        startActivity(intent);
    }

/*
    public void onSaveInstanceState(Bundle savedInstanceState){

        savedInstanceState.putString(Diary_Entry,"shumani");

        super.onSaveInstanceState(savedInstanceState);
    }*/
    private void updateSummary() {
        TextView tvOut = findViewById(R.id.SummaryTV);
        tvOut.setText(String.format("%-10s:\n", getString(R.string.summary)));
        tvOut.append(getSummary());
    }
    public void onItemSelected(ListView listView){
        final ListView list = findViewById(R.id.DiaryEntriesLView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DiaryActivity.index = i;
                startActivity(new Intent(OverviewActivity.this,DiaryActivity.class));
            }
        });

    }

    private void setUpListView() {
        DiaryEntryAdapter adapter = new DiaryEntryAdapter(this);
        ListView listOut = findViewById(R.id.DiaryEntriesLView);
        listOut.setAdapter(adapter);
    }

    public void onPause() {
        onSaveInstanceState(appState);
        super.onPause();
        mDataSource.close();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setUpListView();
        updateSummary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
        updateSummary();
        setUpListView();

    }
}
