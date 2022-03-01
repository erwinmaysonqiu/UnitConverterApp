package au.syd.project.unitconverter.app;

import java.util.ArrayList;

public class Unit {


    //1. Declare the attributes of Unit
    private int position;
    private String unitName;


    //2. Declare the constructor for Units
    public Unit(int pos, String name) {
        this.position = pos;
        this.unitName = name;
    }

    public Unit() {
    }

    //3. Declare the getters and setters for Units
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    //4. Declare a publicly accessable arrayList called Unit
    // NB: Static, so it doesn't change
    public static ArrayList<Unit> unitArrayList = new ArrayList<>();

    //5. Method to populate arrayList; must be executed at the start of MainActivity
    public void addUnits() {
        unitArrayList.add(new Unit(0, "millimetres"));
        unitArrayList.add(new Unit(1, "centimetres"));
        unitArrayList.add(new Unit(2, "metres"));
        unitArrayList.add(new Unit(3, "kilometres"));
    }

    //6. Method to return unitsArrayList if needed
    public ArrayList<Unit> getUnits() {
        return unitArrayList;
    }

    //7. Declare a publicly accessible data store for input, final result from conversion and unit names
    public static float conversionInput;
    public static float conversionResult;
    public static String conversionUnit1;
    public static String conversionUnit2;
  }
