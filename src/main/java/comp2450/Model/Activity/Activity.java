package comp2450.Model.Activity;

import comp2450.Model.Exceptions.InvalidDistanceException;
import comp2450.Model.Exceptions.InvalidNameException;
import comp2450.Model.Exceptions.InvalidRouteException;
import comp2450.Model.Map.IMapDataType;
import comp2450.Model.Person.Gears;
import comp2450.Model.Map.Coordinates;
import com.google.common.base.Preconditions;
import comp2450.Model.Person.Person;

import java.time.LocalDateTime;
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
    final private LocalDateTime date;
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

    private Activity(String name, List<Coordinates> routeTaken,  LocalDateTime date, double distance,Gears gear){

        this.name = name;
        this.gearsUsed= gear;
        this.routeTaken = routeTaken;
        this.date = date;
        this.distance = distance;
        // one postcondition: trainer is now a valid trainer.
        checkActivity();
    }
    /*
    To be worked on but first let's have gear and coordinates builder
     */
    public static class ActivityBuilder{
        private String name;
        private Gears gearsUsed;
        private List<Coordinates> routeTaken;
        private LocalDateTime date;
        private double distance;


        public ActivityBuilder(){}

        public ActivityBuilder name(String getName) throws InvalidNameException {

            Preconditions.checkNotNull(getName, "Name cannot be null");

            if(name.isBlank()){
                throw new InvalidNameException();
            }
            this.name = getName;

            return this;
        }

        public ActivityBuilder gears(Gears gears){

            Preconditions.checkNotNull(gears, "Gear to be added cannot be null");

            this.gearsUsed = gears;
            return this;
        }
        public ActivityBuilder calendar(LocalDateTime ldt){
            Preconditions.checkNotNull(ldt, "Time-Date to be added cannot be null");

            this.date = ldt;

            return this;
        }
        public ActivityBuilder route(List<Coordinates> route) throws InvalidRouteException {
            Preconditions.checkNotNull(route, "route to be added cannot be null");

            if(route.size() <1 ){
                throw new InvalidRouteException();
            }
            this.routeTaken = route;
            return this;

        }
        public ActivityBuilder distance(double distance) throws InvalidDistanceException{

            if(distance<=0){
                throw new InvalidDistanceException();
            }
            return this;
        }

        public Activity build(){
            return new Activity(name,routeTaken, date, distance,gearsUsed );
        }
    }

    public Gears getGears(){
        return gearsUsed;
    }
    public LocalDateTime getCalendar(){
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