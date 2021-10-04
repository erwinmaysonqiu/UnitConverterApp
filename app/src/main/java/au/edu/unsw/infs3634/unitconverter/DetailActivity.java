package au.edu.unsw.infs3634.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Declare and initialize the xml elements in the layout
        TextView tvUnitFirst = findViewById(R.id.tvUnitFirst);
        TextView tvUnitSecond = findViewById(R.id.tvUnitSecond);
        EditText txtInput2 = findViewById(R.id.txtInput2);
        EditText txtOutput2 = findViewById(R.id.txtOutput2);

        Button btnReturn = findViewById(R.id.btnReturn);


        //Set the textviews to display the name of the units, and also the final result
        //Unit names and conversion result were stored in publicly accessible variables from the Unit class, which acted as data stores
        tvUnitFirst.setText(Unit.conversionUnit1);
        tvUnitSecond.setText(Unit.conversionUnit2);
        txtInput2.setText(String.valueOf(Unit.conversionInput));
        txtOutput2.setText(String.valueOf(Unit.conversionResult));


        //Create an onClick listener that will take the user back to the MainActivity
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent, to take user to DetailActivity
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                //start the activity, and pass in intent as the argument
                startActivity(intent);
            }
        });


    }
}
