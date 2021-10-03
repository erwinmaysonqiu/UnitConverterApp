package au.edu.unsw.infs3634.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //0. Declare and initialize the xml elements, and variables to store values, and identify units
        EditText txtInput = findViewById(R.id.txtInput);
        TextView tvUnitFirst = findViewById(R.id.tvUnitFirst);
        TextView tvUnitSecond = findViewById(R.id.tvUnitSecond);
        Button btnConvert = findViewById(R.id.btnReturn);

        int num1;
        int num2;
        String unit1Name, unit2Name;


        //1. Generate 2 random integers between 0 and 3 to identify which units to use
        //NB: ArrayList index starts from 0, so we generate an integer between 0 and 3 to identify which of the 4 units to use
        //The nextInt function excludes the max, so we always do the max value we want + 1; (i.e. let 4 be our upper, and 0 be our lower)

        Random random = new Random();
        num1 = random.nextInt(4 - 0) + 0;
        num2 = random.nextInt(4 - 0) + 0;

        //      Control: Selected units cannot be the same.
        if (num1 == num2) {
            while (num1 == num2) {
                num2 = random.nextInt(4 - 0) + 0;
            }
        } else {
            //do nothing
            ;
        }

        //2. Set the textViews to display the selected Units
        //Initialize the unitArrayList
        Unit unitTemp = new Unit();
        unitTemp.addUnits();

        unit1Name = Unit.unitArrayList.get(num1).getUnitName();
        unit2Name = Unit.unitArrayList.get(num2).getUnitName();

        //Store names in DataStore so can be accessed by DetailActivity;
        Unit.conversionUnit1 = unit1Name;
        Unit.conversionUnit2 = unit2Name;


        //Set texts of TextViews to the unitNames of the 1st and 2nd unit
        tvUnitFirst.setText(unit1Name);
        tvUnitSecond.setText(unit2Name);


        //3. Create an onClick listener which will generate a result and take the user to the detail activity via an intent
        // Generate the result depending on the units selected
        //mm, cm, m, km
        //10mm = 1cm
        //100cm = 1m
        //1000m = 1km
        int finalNum = num2;
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 1: Convert input to mm, using a tempNum
                float input = Integer.parseInt(String.valueOf(txtInput.getText()));
                float tempNum, output;

                //Unit 1 is cm
                if (num1 == 1) {
                    tempNum = input * 10;
                //Unit 1 is m
                } else if (num1 == 2) {
                    tempNum = input * (10 * 100);
                //Unit 1 is km
                } else if (num1 == 3) {
                    tempNum = input * (10 * 100 * 1000);
                //Unit 1 is mm
                } else {
                    tempNum = input;
                }

                //Step 2: Convert mm to final output unit
                //Unit 2 is mm, so do nothing
                if (finalNum == 0) {
                    output = tempNum;
                //Unit 2 is cm
                } else if (finalNum == 1) {
                    output = tempNum / 10;
                //Unit 2 is m
                } else if (finalNum == 2) {
                    output = tempNum / (10*100);
                //Unit 2 is km
                } else {
                    output = tempNum / (10 * 100 * 1000);
                }

                //Store the result in conversionResult, so it can be accessed by DetailActivity
                Unit.conversionResult = output;

                //Take user to DetailActivity to see result
                //Create a new intent, to take user to DetailActivity
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                //start the activity, and pass in intent as the argument
                startActivity(intent);

                //finish the activity, so that when press Return in DetailActivity, Activity is created again
                finish();
            }
        });
    }
}