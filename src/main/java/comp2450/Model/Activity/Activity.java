package comp2450.Model.Activity;

import comp2450.Model.Map.IMapping;
import comp2450.Model.Person.Gears;
import comp2450.Model.Map.Coordinates;
import com.google.common.base.Preconditions;

import java.util.*;

/**
 * Represents an {@link Activity} performed by a person.
 * This has a {@link #name}, a {@link #routeTaken}, the {@link #gearsUsed},
 * the {@link #date} of the activity when it was done using {@link Calendar}, {@link #distance} covered, and the
 * {@link #currWeight} of the person during the activity.
 *
 * The class can calculate {@link #caloriesBurnt} based on {@link #CALORIES_CONSTANT},
 * {@link #currWeight}, and {@link #distance}.
 */
public class Activity implements IMapping {

    final private double CALORIES_CONSTANT = 1.36;
    final private String name;
    final private Gears gearsUsed;
    final private List<Coordinates> routeTaken;
    final private Calendar date;
    final private Coordinates startingCoordinate;
    final private double distance;
    final private double currWeight;
    private double caloriesBurnt;


    //Array List can never be nul, all elements should never be null when we loop through them.

    public void checkActivity(){
        Preconditions.checkNotNull(name, "Name of the Activity can never be null");
        Preconditions.checkState(name.length()>=1, "An Activity should be assigned a non empty name");

        Preconditions.checkNotNull(gearsUsed, "Gear can never be null");
        Preconditions.checkNotNull(routeTaken, "Route Taken can never be null");
        Preconditions.checkNotNull(startingCoordinate, "Coordinates to begin with can never be null");
        Preconditions.checkState(routeTaken.size()>=1,"Route has to be non empty");


        Preconditions.checkNotNull(date, "Date can never be null");
        Preconditions.checkState(distance>0,"Distance has to be greater than zero");
        Preconditions.checkState(currWeight>0,"Weight can never be negative");
        Preconditions.checkState(caloriesBurnt>=0,"caloriesBurnt can never be negative");

        for(Coordinates cood: routeTaken){
            Preconditions.checkNotNull(cood, "A coordinates of a Route can never be null");
        }

    }

    public Activity(String name, Coordinates startingCoordinate,  Calendar date, double distance, double currWeight,Gears gear){

        this.name = name;
        this.gearsUsed= gear;
        this.routeTaken = new ArrayList<>();
        this.date = date;
        this.distance = distance;
        this.currWeight = currWeight;
        this.startingCoordinate = startingCoordinate;
        this.routeTaken.add(startingCoordinate);
        // one postcondition: trainer is now a valid trainer.
        checkActivity();
    }

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
    * Using {@link #distance} , {@link #CALORIES_CONSTANT } and {@link #currWeight} during activity
    */
    public double getcaloriesBurnt(){
        this.caloriesBurnt = CALORIES_CONSTANT*this.currWeight*distance;
        checkActivity();
        return this.caloriesBurnt;
    }

    public void addCoordinates(Coordinates coordinates){
        Preconditions.checkNotNull(coordinates, "Coordinates to be added to be entered can never be null");
        checkActivity();
        routeTaken.add(coordinates);
        checkActivity();

    }

    @Override
    public List<Coordinates> getMappingObject() {
        return routeTaken;
    }

}
