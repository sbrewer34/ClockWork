package com.samantha.clockwork;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    //EditText currentLength;= (EditText) findViewById(R.id.currentlength);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button_calculate;
        button_calculate=(Button) findViewById(R.id.button_calculate);
        if(button_calculate!=null)
            button_calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getChange();
                }
            });
    }

    public void getChange() {

        /*
        *The following code obtains the actual Beats per minute(not necessarily the correct bpm)
        * based off the pendulum length that the user input.
         */
        TextView tvCurrentBPM = (TextView) findViewById(R.id.currentminute);//TextView of current beats per minute
        String currentBPMText = "0";

        TextView tvCurrentLength = (TextView) findViewById(R.id.currentlength); //Length of the pendulum currently
        String currentLengthText = "0"; //Length of pendulum currently converted from textview to string. Set to 0 to prevent null pointer.
        double currentBPM, currentLength; //current beats per minute and current length of pendulum. converted from string to double
        if (tvCurrentLength != null)  //if statement prevents possible null pointer
            currentLengthText = tvCurrentLength.getText().toString(); //convert current length from textview to string
        currentLength = Double.parseDouble(currentLengthText); //convert current length from string to double
        currentBPM = Math.sqrt(141120 / currentLength); //calculates current bpm
        currentBPMText = Double.toString(Math.round(currentBPM*10)/10.0 ); //converts current BPM from double to strong and rounds it to tenth decimal place
        tvCurrentBPM.setText(currentBPMText); //displays current BPM in the correct textview

        /*
        *The following code obtains the current beats per hour
         */
        TextView tvCurrentBPH = (TextView) findViewById(R.id.currenthour); //currentBPH textview
        String currentBPHText;

        double currentBPH = currentBPM*60;
        currentBPHText= Integer.toString((int)Math.round(currentBPH*10)/10);
        tvCurrentBPH.setText(currentBPHText);

        /*
        *The following code takes the length of the pendulum the user input and puts it into a second
        * text view that should also contain the current length of the pendulum.
         */
        TextView tvCurrentLength2 = (TextView) findViewById(R.id.currentlength2);//text view of current length of pendulum in results area
        tvCurrentLength2.setText(currentLengthText);//displays current length of pendulum from user input into the results area


        /*
        *These are variables for how many minutes and seconds the the clock is off by in 24 hours
        * as well as in one hour.
         */
        TextView tvMin24= (TextView) findViewById(R.id.min24); //text view of how many minutes its off by in 24 hours
        int min24=0;
        if(tvMin24!=null)
            min24=Integer.parseInt(tvMin24.getText().toString()); //converted minutes off in 24 hours to int
        TextView tvSec24= (TextView) findViewById(R.id.sec24); //text view of how many second its off by in 24 hours
        int sec24=0;
        if(tvSec24!=null)
            sec24=Integer.parseInt(tvSec24.getText().toString()); //converts second off in 24 hours to int
        TextView tvMin1= (TextView) findViewById(R.id.min1); //text view of how many minutes it's off by in 1 hour
        int min1=0;
        if(tvMin1!=null)
            min1=Integer.parseInt(tvMin1.getText().toString());
        TextView tvSec1= (TextView) findViewById(R.id.sec1); //text view of how many seconds it's off by in 1 hour
        int sec1=0;
        if(tvSec1!=null)
            sec1=Integer.parseInt(tvSec1.getText().toString());

        int totalSecPerDay= (min24 * 60) + sec24;
        int totalSecPerHour= totalSecPerDay/24;

        /*
        *The following code calculates the different from the clocks actual time to the
        * desired time. The change needed.
         */
        double change; //The adjustment needed to produce desired time
        RadioButton radioFast= (RadioButton) findViewById(R.id.radio_fast); //button selected if the clock is fast
        //RadioButton radioSlow= (RadioButton) findViewById(R.id.radio_slow); //button selected if the clock is slow
        RadioButton radioSlow= (RadioButton)CalculatorActivity.this.findViewById(R.id.radio_slow);

        if(radioFast.isChecked())
            change= (1440 - (min24 + (sec24 / 60))) / 1440.0;
        else
            change= (1440 + (min24 + (sec24/60)))/1440.0;

        /*
        *The following code uses the the change to calculate the desired beats of the clock per hour.
         */
        TextView tvDesiredBPH= (TextView) findViewById(R.id.desiredhour);
        double desiredBPH= currentBPH*change;
        String desiredBPHText= Integer.toString((int)Math.round(desiredBPH*10)/10);
        tvDesiredBPH.setText(desiredBPHText);

        /*
        *The following code calculates the desired beats per minute by dividing desired BPH by 60.
         */
        TextView tvDesiredBPM= (TextView) findViewById(R.id.desiredminute);
        double desiredBPM= desiredBPH/60;
        tvDesiredBPM.setText(Double.toString(Math.round(desiredBPM*10)/10.0));

        /*
        Calculates desired length of pendulum
         */
        TextView tvDesiredLength= (TextView) findViewById(R.id.desiredlength);
        double desiredLength= 141120/(desiredBPM*desiredBPM);
        tvDesiredLength.setText(Double.toString(Math.round(desiredLength*100)/100.00));

        /*
        Calculates the change that need to be made to the length of the pendulum in order to obtain
        desirable results.
         */
        TextView tvChange=(TextView) findViewById(R.id.neededchange);
        double neededChange= desiredLength-currentLength;

        int changeInt;
        String sign;
        double changeDec;
        if(neededChange>=0){
            changeInt=(int)Math.floor(neededChange);
            changeDec=neededChange-changeInt;
            sign="+";
        }
        else {
            changeInt = (int) Math.ceil(neededChange);
            changeDec = changeInt - neededChange;
            changeInt = (int)Math.abs(changeInt);
            sign="-";
        }
            int fraction= (int) Math.round(changeDec * 16);
            String changeFrac= "";
            switch(fraction) {
                case 0:
                    changeFrac="0";
                    break;
                case 2:
                    changeFrac= "1/8";
                    break;
                case 4:
                    changeFrac="1/4";
                    break;
                case 6:
                    changeFrac="3/8";
                    break;
                case 8:
                    changeFrac="1/2";
                    break;
                case 10:
                    changeFrac="5/8";
                    break;
                case 12:
                    changeFrac= "3/4";
                    break;
                case 14:
                    changeFrac= "7/8";
                    break;
                default:
                    changeFrac= " " + fraction + "/16";
                    break;
            }
        tvChange.setText(sign+Integer.toString(changeInt)+" "+changeFrac);

        //TextView tv3= (TextView) findViewById(R.id.currentlength2);
    }

}
