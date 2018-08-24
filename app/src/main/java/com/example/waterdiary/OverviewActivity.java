package com.example.waterdiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database.DiaryEntryDataSource;
import Utilities.DiaryEntry;
import Utilities.DiaryEntryAdapter;

import static Utilities.DiaryEntryAdapter.getSummary;

public class OverviewActivity extends AppCompatActivity {

    //region Global Variables
    public static Context mcontext;
    List<DiaryEntry> diaryEntries = null;
    public static Bundle appState;
    public static DiaryEntryDataSource mDataSource;
    public static int lastSeen = 0;
    //endregion

    //region Utility Methods
    public void onCalculatorButtonClick(View view) {

        Intent intent = new Intent(OverviewActivity.this,CalculatorActivity.class);
        intent.putExtra("class",1);
        onPause();
        startActivity(intent);
    }

    private void updateSummary() {
        TextView tvOut = findViewById(R.id.SummaryTV);
        tvOut.setText(String.format("%-10s:\n", getString(R.string.summary)));
        tvOut.append(getSummary());
    }

    private void setUpListView() {
        DiaryEntryAdapter adapter = new DiaryEntryAdapter(this);
        ListView listOut = findViewById(R.id.DiaryEntriesLView);
        listOut.setAdapter(adapter);

        listOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaryActivity.index = position;
                onPause();
                startActivity(new Intent(OverviewActivity.this, DiaryActivity.class));
            }
        });
    }
    //endregion

    //region App cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        setTitle(R.string.overView);
        //<editor-fold desc="Check provided state">
        if (savedInstanceState == null) {
            appState = new Bundle();
        }else{appState = savedInstanceState;}
        //</editor-fold>

        //<editor-fold desc="Create database connection">
        mcontext= this;
        mDataSource = new DiaryEntryDataSource(this);
        mDataSource.open();
        //</editor-fold>

        //<editor-fold desc="Read in Data and update views">
        diaryEntries = mDataSource.getAllEntries();
        DiaryEntryAdapter.diaryEntries = diaryEntries;
        if (diaryEntries!=null) {
            updateSummary();
            setUpListView();
        }
        //</editor-fold>
        if(lastSeen==1){startActivity(new Intent(this,DiaryActivity.class));}
        else if (lastSeen==2){startActivity(new Intent(this,CalculatorActivity.class));}

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

    @Override
    public void onPause() {
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
    //endregion
}
