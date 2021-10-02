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

        float input, output;
        int num1, num2;
        String unit1Name, unit2Name;


        //1. Generate 2 random integers between 0 and 3 to identify which units to use
        //NB: ArrayList index starts from 0, so we generate an integer between 0 and 3 to identify which of the 4 units to use

        Random random = new Random();
        num1 = random.nextInt(3) + 0;
        num2 = random.nextInt(3) + 0;

        //      Control: Selected units cannot be the same.
        if (num1 == num2) {
            while (num1 == num2) {
                num2 = random.nextInt(3) + 0;
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

        //Set texts of TextViews to the unitNames of the 1st and 2nd unit
        tvUnitFirst.setText(unit1Name);
        tvUnitSecond.setText(unit2Name);


        //3. Create an onClick listener which will generate a result and take the user to the detail activity via an intent
        // Generate the result depending on the units selected

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent, to take user to DetailActivity
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                //start the activity, and pass in intent as the argument
                startActivity(intent);
            }
        });
    }
}