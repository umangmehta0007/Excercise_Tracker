package comp2450.Model.Person;


import comp2450.Model.Activity.Activity;

import com.google.common.base.Preconditions;
import comp2450.Model.Activity.ActivityTracker;

import java.util.*;

/**
 * Person class represents an individual doing {@link Activity}
 * A person has a name and weight along with A list of {@link Activity} and a list of {@link Gears} equipped.
 * Uses a {@link TreeSet} for {@link #gearsEquipped} to ensure gears are always unique.
 * A {@link Gears}, {@link Activity} can be added/removed from the person's list.
 * {@link #weight} can be updated anytime during the Program.
 */
public class Person implements ActivityTracker {

    final private String name;
    private double weight;
    final private List<Activity> myActivityList;
    final private TreeSet<Gears> gearsEquipped;// Uses TreeSet to ensure unique and sorted gears


    private void checkPerson(){
    Preconditions.checkNotNull(name, "Name of the person can never be null");
    Preconditions.checkState(name.length()>=1, "Person should be assigned a non empty name");
    Preconditions.checkNotNull(myActivityList, "Activity List should never be null");
    Preconditions.checkNotNull(gearsEquipped, "List of gears should never be null");
    Preconditions.checkState(weight>0, "Weight of person should always be greater than zero");

    for(Activity currAct: myActivityList ){
        Preconditions.checkNotNull(currAct,"Activity in a person should never be null");
    }
    for(Gears currGear: gearsEquipped ){
        Preconditions.checkNotNull(currGear,"Gears for a person should never be null");
    }

}

    /**Constructor
     * Creates a new Person with the user-defined name and weight.
     *
     * @param name   the person's name
     * @param weight the person's weight, must be greater than 0
     */
    public Person(String name, double weight){

        this.weight = weight;
        this.name = name;
        this.myActivityList = new ArrayList<>();
        this.gearsEquipped = new TreeSet<>();


        checkPerson(); //Post condition to check if a valid person has been made;
    }

    /**
     * Updates {@link #myActivityList} and adds {@link Activity}
     * @param activity is the {@link Activity} to be added to the list
     */
    public void addActivity(Activity activity){
        checkPerson(); //Precondition check
        myActivityList.add(activity);
        checkPerson();//Postcondition check

    }

    /**
     * Removes this {@link Activity} from the {@link #myActivityList}
     * @param index To be removed from the list.
     */
    public void removeActivity(int index){
        checkPerson();
        myActivityList.remove(index);
        checkPerson();
    }

    /**
     * Updates {@link #gearsEquipped} and adds {@link Gears}
     * @param gear is the {@link Gears} to be added to the list
     */
    public void addGear(Gears gear){
        checkPerson();

        gearsEquipped.add(gear);
        checkPerson();

    }

    /**
     * Remove the {@link Gears} from the list {@link #gearsEquipped}
     * @param index, removes the {@link Gears} at that index
     */
    public void removeGear(int index){
        checkPerson();

        gearsEquipped.remove(index);
        checkPerson();

    }

    /**
     * Update the {@link #weight} to the new weight of the person
     * @param weight is the next weight.
     */
    public void setWeight(double weight) {
        checkPerson();

        this.weight = weight;
        checkPerson();

    }


    private ArrayList<Activity> activities(final Calendar startDate, final Calendar endDate){

        ArrayList<Activity> activitiesInRange = new ArrayList<>();

        for(Activity activity: myActivityList){

            Calendar activityDate = activity.getCalendar();

            if(activityDate.after(startDate) && activityDate.before(endDate)){
                activitiesInRange.add(activity);
            }

        }
        return activitiesInRange;
    }

    public double totalCalories( final Calendar startDate, final Calendar endDate) {

        ArrayList<Activity> myActivities = activities(startDate, endDate);

        double totalCalories = 0;
        for(Activity act: myActivities){
            totalCalories+=act.getcaloriesBurnt();
        }

        return totalCalories;
    }
    public double totalDistance(final Calendar startDate, final Calendar endDate){

        ArrayList<Activity> myActivities = activities(startDate, endDate);

        double totalDistance = 0;
        for(Activity act: myActivities){
            totalDistance+=act.getDistance();
        }

        return totalDistance;
    }

    public String getName() {
        return name;
    }
    public double getWeight(){
        return weight;
    }
    public List<Activity> getMyActivityList() {
        return myActivityList;
    }
    public TreeSet<Gears> getGearsEquipped() {
        return gearsEquipped;
    }

}
