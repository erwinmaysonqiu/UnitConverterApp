package au.edu.unsw.infs3634.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;


public class OptionActivity extends AppCompatActivity {

    //Declare attributes

    //Logging Purposes
    private static final String TAG = "OptionActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        //Declare XML elements
        Spinner spinnerFirst = findViewById(R.id.spinnerUnit1);
        Spinner spinnerSecond = findViewById(R.id.spinnerUnit2);
        Switch switchManualUnits = findViewById(R.id.switchManual);
        Button btnReturn = findViewById(R.id.btnReturn1);


        //Instantiate a new unit and setting to access class methods/attributes
        Unit unit = new Unit();
        Setting setting = new Setting();




        //Declare and link the Adapter for the Spinner of dropdown items in Options
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(OptionActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.units));

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirst.setAdapter(spinnerAdapter);
        spinnerSecond.setAdapter(spinnerAdapter);

        //onCreate, disable the Spinner upon loading the Option activity
        //NB: cannot disable it via XML
        spinnerFirst.setEnabled(false);
        spinnerSecond.setEnabled(false);
        Log.d(TAG, "Spinners have been disabled by default");

        //Check if manual unit control was toggled previously by the user
        if (setting.isBooleanManual() == true) {
            switchManualUnits.setChecked(true);
            spinnerFirst.setEnabled(true);
            spinnerSecond.setEnabled(true);
            Log.d(TAG, "Spinners have been enabled, because they were enabled previously by the user!");

            //Set the items selected as the ones last picked by the user
            //Note, the setSelection method only works if called
            spinnerFirst.setSelection(setting.getUnitFirst());
            spinnerSecond.setSelection(setting.getUnitSecond());
        } else {
            ;
        }


        //Listener to check when an Item has been selected in the first spinner
        spinnerFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Assign the item position of the select unit as UnitFirst and UnitSecond
                setting.setUnitFirst(spinnerFirst.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Even if the user does not select a unit, if the user has enabled Manual control, whatever is currently selected in...
                //spinnerFirst should be selected as the first unit
                if (setting.isBooleanManual() == true) {
                    setting.setUnitFirst(spinnerFirst.getSelectedItemPosition());
                } else {
                    ;
                }
            }
        });

        //Listener to check when an Item has been selected in the second spinner
        spinnerSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Assign the item position of the select unit as UnitFirst and UnitSecond
                setting.setUnitSecond(spinnerSecond.getSelectedItemPosition());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Even if the user does not select a unit, if the user has enabled Manual control, whatever is currently selected in
                // spinnerSecond should be selected as the second unit
                if (setting.isBooleanManual() == true) {
                    setting.setUnitSecond(spinnerSecond.getSelectedItemPosition());
                } else {
                    ;
                }
            }
        });

        //Declare a listener to check if the Randomized toggle is on
        //Set an OnClickListener that will change the UI to black, and text white, if user clicks on it
        switchManualUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setting.isBooleanManual() == false) {
                    spinnerFirst.setEnabled(true);
                    spinnerSecond.setEnabled(true);
                    setting.setBooleanManual(true);

                    //Even if the user does not select an item, whatever is currently selected in the spinner should be the selected unit
                    setting.setUnitFirst(spinnerFirst.getSelectedItemPosition());
                    setting.setUnitSecond(spinnerSecond.getSelectedItemPosition());

                } else {
                    spinnerFirst.setEnabled(false);
                    spinnerSecond.setEnabled(false);
                    setting.setBooleanManual(false);
                }
            }
        });


        //Navigate to Main Activity screen
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, MainActivity.class);
                startActivity(intent);
                //Need to finish this Activity so we can access our OnCreate method once we resume
                finish();
            }
        });
    }
}
