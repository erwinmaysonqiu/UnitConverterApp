package au.edu.unsw.infs3634.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Declare and initialize the xml elements in the layout

        Button btnReturn = findViewById(R.id.btnReturn);


        //Set the textviews to display the UnitFirst, UnitSecond and also the result


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
