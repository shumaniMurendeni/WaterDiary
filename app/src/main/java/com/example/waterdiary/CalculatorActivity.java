package com.example.waterdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Utilities.DiaryEntry;
import Utilities.DiaryEntryAdapter;

public class CalculatorActivity extends AppCompatActivity {

    private static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_calculator);
        setTitle(R.string.calculator);

        configureBackButton();
        configureSubmitButton();
    }

    private void configureSubmitButton(){
        Button submitButton = findViewById(R.id.Submitbutton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView date = findViewById(R.id.dateEText);
                TextView shower = findViewById(R.id.ShowerEText);
                TextView toilet = findViewById(R.id.toiletEText);
                TextView hygiene = findViewById(R.id.hydieneEText);
                TextView laundry = findViewById(R.id.laundryEText);
                TextView dishes = findViewById(R.id.dishesEText);
                TextView cooking =findViewById(R.id.cookingEText);
                TextView cleaning = findViewById(R.id.cleaningEText);
                TextView other = findViewById(R.id.otherEText);

                if (!date.getText().equals("")) {
                    try {
                        DiaryEntryAdapter.addEntry(new DiaryEntry(date.getText().toString(),
                                Double.parseDouble(shower.getText().toString())
                                ,Double.parseDouble(toilet.getText().toString())
                                ,Double.parseDouble(hygiene.getText().toString())
                                ,Double.parseDouble(laundry.getText().toString())
                                ,Double.parseDouble(dishes.getText().toString())
                                ,Double.parseDouble(cooking.getText().toString())
                                ,Double.parseDouble(cleaning.getText().toString())
                                ,Double.parseDouble(other.getText().toString())));
                        date.setText(""); shower.setText(""); toilet.setText(""); hygiene.setText("");
                        laundry.setText(""); dishes.setText(""); cooking.setText("");
                        cleaning.setText(""); other.setText("");
                        finish();
                        startActivity(new Intent(CalculatorActivity.this,DiaryActivity.class));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(CalculatorActivity.this,"Please fill in all the fields. You cannot have an empty field",
                                Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(CalculatorActivity.this,"Please Enter the date for the Diary Entry",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void configureBackButton() {
        Button backButton = findViewById(R.id.Backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void onBackClick(View view){
        onSaveInstanceState(bundle);
        finish();
    }

}
