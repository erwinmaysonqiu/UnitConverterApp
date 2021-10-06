package au.edu.unsw.infs3634.unitconverter;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class Setting {

    //Declare variables stored in setting
    public static boolean booleanNight = false;
    public static boolean booleanManual = false;
    public static int unitFirst;
    public static int unitSecond;

    //Declare constructor
    public Setting() {
        ;
    }

    //Declare a changeUI method, that takes in a View, and changes the switch color and text
    public void toDark(View v, Switch s, EditText e) {
        v.setBackgroundColor(Color.parseColor("#212121"));
        s.setText("Dark Mode");
        s.setTextColor(Color.parseColor("#ffffff"));
        s.setChecked(true);
        e.setHintTextColor(Color.parseColor("#ffffff"));
    }

    public void toLight(View v, Switch s, EditText e) {
        v.setBackgroundColor(Color.parseColor("#ffffff"));
        s.setText("Light Mode");
        s.setTextColor(Color.parseColor("#FF1B1B1B"));
        s.setChecked(false);
        e.setHintTextColor(Color.parseColor("#707070"));
    }

    //Getter and setters
    public static void setBooleanNight(boolean booleanNight) {
        Setting.booleanNight = booleanNight;
    }

    public static boolean isBooleanNight() {
        return booleanNight;
    }

    public static boolean isBooleanManual() {
        return booleanManual;
    }

    public static void setBooleanManual(boolean booleanManual) {
        Setting.booleanManual = booleanManual;
    }

    public static int getUnitFirst() {
        return unitFirst;
    }

    public static int getUnitSecond() {
        return unitSecond;
    }

    public static void setUnitFirst(int unitFirst) {
        Setting.unitFirst = unitFirst;
    }

    public static void setUnitSecond(int unitSecond) {
        Setting.unitSecond = unitSecond;
    }
}
