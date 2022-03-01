package au.syd.project.unitconverter.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //0. Declare and initialize the xml elements, and variables to store values, and identify units
        View main = findViewById(R.id.viewMain);
        EditText txtInput = findViewById(R.id.txtInput);
        EditText txtOutput = findViewById(R.id.txtOutput);
        TextView tvUnitFirst = findViewById(R.id.tvUnitFirst);
        TextView tvUnitSecond = findViewById(R.id.tvUnitSecond);
        Button btnConvert = findViewById(R.id.btnConvert);
        ImageButton btnOption = findViewById(R.id.btnOption);
        Switch switchUI = findViewById(R.id.switchUI);

        int num1;
        int num2;
        String unit1Name, unit2Name;

        //Instantiate a Setting to access methods
        Setting setting = new Setting();

        //Check if the User turned on Dark Mode
        if (Setting.isBooleanNight() == true) {
            setting.toDark(main, switchUI, txtInput);
        } else {
            ;
        }

        //Check if the user enabled realtime calculation
        if (Setting.isBooleanRealTime() == true) {
            btnConvert.setVisibility(View.GONE);
        }

        //1. Generate 2 random integers between 0 and 3 to identify which units to use
        //NB: ArrayList index starts from 0, so we generate an integer between 0 and 3 to identify which of the 4 units to use
        //The nextInt function excludes the max, so we always do the max value we want + 1; (i.e. let 4 be our upper, and 0 be our lower)
        //CHECK: First check if the user has enabled Manual Control
        if (setting.isBooleanManual() == true) {
            //If booleanManual is enabled, go with the user's manually selected Units from the Option screen
            num1 = setting.getUnitFirst();
            num2 = setting.getUnitSecond();
        } else {
            //If booleanManual has not been toggled, generate random units
            Random random = new Random();
            num1 = random.nextInt(4 - 0) + 0;
            num2 = random.nextInt(4 - 0) + 0;

            //      Control: Randomly selected units cannot be the same.
            if (num1 == num2) {
                while (num1 == num2) {
                    num2 = random.nextInt(4 - 0) + 0;
                }
            } else {
                ;
            }
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
            public void onClick(View view) throws NumberFormatException {
                //Step 1: Convert input to mm, using a tempNum
                //If the input is not a number, display an error message.
                try {
                    calculate(Float.parseFloat(String.valueOf(txtInput.getText())), num1, finalNum);
                    //Take user to DetailActivity to see result
                    //Create a new intent, to take user to DetailActivity
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    //start the activity, and pass in intent as the argument
                    startActivity(intent);

                    //If the user enters non-numeric data, display an error message.
                } catch (Exception e) {
                    txtInput.setError("Please input a number here, then press 'Convert'.");
                }
            }
        });

        //Set an OnClickListener that will change the UI to black, and text white, if user clicks on it
        switchUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Setting.isBooleanNight() == false) {
                    setting.toDark(main, switchUI, txtInput);
                    Setting.setBooleanNight(true);
                } else {
                    setting.toLight(main, switchUI, txtInput);
                    Setting.setBooleanNight(false);
                }
            }
        });

        //Navigate to Option screen
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OptionActivity.class);
                startActivity(intent);
            }
        });

        //Add a text watcher to calculate unit 2 in real time
        txtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(txtInput.getText().toString()) == false && setting.isBooleanRealTime() == true) {
                    calculate(Float.parseFloat(String.valueOf(txtInput.getText())), num1, finalNum);
                    txtOutput.setEnabled(true);
                    txtOutput.setText(String.valueOf(Unit.conversionResult));
                } else {
                    txtOutput.setText("");
                    txtOutput.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void calculate(float input, int initialNum, int finalNum) {
        //Store the conversionInput so it can be displayed in DetailActivity
        float tempNum, output;
        Unit.conversionInput = input;
        //Unit 1 is cm
        if (initialNum == 1) {
            tempNum = input * 10;
            //Unit 1 is m
        } else if (initialNum == 2) {
            tempNum = input * (10 * 100);
            //Unit 1 is km
        } else if (initialNum == 3) {
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
            output = tempNum / (10 * 100);
            //Unit 2 is km
        } else {
            output = tempNum / (10 * 100 * 1000);
        }

        //Store the result in conversionResult, so it can be accessed by DetailActivity
        Unit.conversionResult = output;
    }
}