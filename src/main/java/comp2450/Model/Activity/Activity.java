package comp2450.Model.Activity;

import comp2450.Model.Map.IMapDataType;
import comp2450.Model.Person.Gears;
import comp2450.Model.Map.Coordinates;
import com.google.common.base.Preconditions;
import comp2450.Model.Person.Person;

import java.util.*;

/**
 * Represents an {@link Activity} performed by a person.
 * This has a {@link #name}, a {@link #routeTaken}, the {@link #gearsUsed},
 * the {@link #date} of the activity when it was done using {@link Calendar}, {@link #distance} covered, and the
 *
 * {@link #distance}.
 */
public class Activity implements IMapDataType {

    final private double CALORIES_CONSTANT = 1.36;
    final private String name;
    final private Gears gearsUsed;
    final private List<Coordinates> routeTaken;
    final private Calendar date;
    final private double distance;
    //Array List can never be nul, all elements should never be null when we loop through them.

    public void checkActivity(){
        Preconditions.checkNotNull(name, "Name of the Activity can never be null");
        Preconditions.checkState(name.length()>=1, "An Activity should be assigned a non empty name");

        Preconditions.checkNotNull(gearsUsed, "Gear can never be null");
        Preconditions.checkNotNull(routeTaken, "Route Taken can never be null");
        Preconditions.checkState(routeTaken.size()>=1,"Route has to be non empty");


        Preconditions.checkNotNull(date, "Date can never be null");
        Preconditions.checkState(distance>0,"Distance has to be greater than zero");
        for(Coordinates cood: routeTaken){
            Preconditions.checkNotNull(cood, "A coordinates of a Route can never be null");
        }

    }

    public Activity(String name, List<Coordinates> routeTaken,  Calendar date, double distance,Gears gear){

        this.name = name;
        this.gearsUsed= gear;
        this.routeTaken = routeTaken;
        this.date = date;
        this.distance = distance;
        // one postcondition: trainer is now a valid trainer.
        checkActivity();
    }


    /*
    To be worked on but first let's have gear and coordiantes builder
     */
//    public static class ActivityBuilder{
//        final private String name;
//        final private Gears gearsUsed;
//        final private List<Coordinates> routeTaken;
//        final private Calendar date;
//        final private double distance;
//        final private double currWeight;
//        private double caloriesBurnt;
//
//    }

    public Gears getGears(){
        return gearsUsed;
    }
    public Calendar getCalendar(){
        return date;
    }

    public String getName(){

        return this.name;
    }
    public double getDistance(){

        return this.distance;
    }

    /**
     * Calculates and returns the total calories burnt during this activity.
     * Using {@link #distance} , {@link #CALORIES_CONSTANT }
     */

    public double caloriesBurnt(Person p) {

        Preconditions.checkNotNull(p, "Person can never be null");
        double burnt = CALORIES_CONSTANT * p.getWeight() * distance;
        checkActivity();
        return burnt;
    }

    @Override
    public List<Coordinates> getMappingObject() {
        return Collections.unmodifiableList(routeTaken);
    }

}