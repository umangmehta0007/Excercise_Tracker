package comp2450.Model.Person;

import comp2450.Model.Activity.Activity;
import comp2450.Output.ShowMap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * This concludes statistics for a person based on their {@link Activity} list.
 * Has all the static methods and includes total calories burnt and total distance within a date range using {@link Calendar}.
 */
public class PersonStats {


    Person person;

    List<Activity>myList;
    public PersonStats(Person person){
        this.person = person;
        this.myList = person.getMyActivityList();
    }

    private ArrayList<Activity> activities(final Calendar startDate, final Calendar endDate){

        ArrayList<Activity> activitiesInRange = new ArrayList<>();

        for(Activity activity: myList){

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
}
