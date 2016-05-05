package com.samantha.clockwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //private  Button button_calculator, button_settings, button_measure, button_collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button_calculator;
        button_calculator=(Button) findViewById(R.id.button_calculator);
        if(button_calculator!=null)
            button_calculator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                }
         });

        Button button_measure=(Button) findViewById(R.id.button_measure);
        if(button_measure!=null)
            button_measure.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startActivity(new Intent(MainActivity.this, MeasurementActivity.class));
                }
            });

        Button button_collection=(Button) findViewById(R.id.button_collection);
        if(button_collection!=null)
            button_collection.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startActivity(new Intent(MainActivity.this,CollectionActivity.class));
                }
            });

        Button button_settings;
        button_settings=(Button) findViewById(R.id.button_settings);
        if(button_settings!=null)
                button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
             }
         });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
