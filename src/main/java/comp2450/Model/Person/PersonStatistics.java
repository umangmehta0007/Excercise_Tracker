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
public class PersonStatistics {


    /**
     * This is a private method Used in {@link #totalCalories} and {@link #totalDistance}
     * This takes a list of {@link Activity} to include only those within the given date range.
     * @param myActivities the list of activities to filter from the list in {@link Person}
     * @param startDate  the start date (exclusive)
     * @param endDate    the end date (exclusive)
     * @return a new list containing only activities within the date range which is used later.
     */
    private static ArrayList<Activity> activities(final List<Activity> myActivities, final Calendar startDate, final Calendar endDate){

        ArrayList<Activity> activitiesInRange = new ArrayList<>();

        for(Activity activity: myActivities){

            Calendar activityDate = activity.getCalendar();

            if(activityDate.after(startDate) && activityDate.before(endDate)){
                activitiesInRange.add(activity);
            }

        }
        return activitiesInRange;
    }

    /**
     * Calculates the total calories burnt in the given list of activities we got from {@link #activities} within the date range.
     *
     * @param activities the list of activities
     * @param startDate  the start date (exclusive)
     * @param endDate    the end date (exclusive)
     * @return the total calories burnt
     */
    public static double totalCalories(final List<Activity> activities, final Calendar startDate, final Calendar endDate) {

        ArrayList<Activity> myActivities = activities(activities, startDate, endDate);

        double totalCalories = 0;
        for(Activity act: activities){
            totalCalories+=act.getcaloriesBurnt();
        }

        return totalCalories;
    }

    /**
     * Calculates the total distance covered in the given list of activities we got from {@link #activities} of activities within the date range.
     *
     * @param activities the list of activities
     * @param startDate  the start date (exclusive)
     * @param endDate    the end date (exclusive)
     * @return the total distance
     */
    public static double totalDistance(final List<Activity> activities, final Calendar startDate, final Calendar endDate){

        ArrayList<Activity> myActivities = activities(activities, startDate, endDate);

        double totalDistance = 0;
        for(Activity act: activities){
            totalDistance+=act.getDistance();
        }

        return totalDistance;
    }
}
