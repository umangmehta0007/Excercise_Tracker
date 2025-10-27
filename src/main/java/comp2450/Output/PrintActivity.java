package comp2450.Output;

import comp2450.Model.Activity.Activity;
import comp2450.Model.Person.Gears;

import java.util.Calendar;

public class PrintActivity {

    final private Activity activity;


    public PrintActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Prints the summary of my activity, including total distance,calories burnt of this {@link Activity}
     */
    public void printSummary(){

        String summary = "";
        String activityName = activity.getName();
        Calendar date = activity.getCalendar();
        double distance = activity.getDistance();
        double caloriesBurnt = activity.getcaloriesBurnt();

        Gears gears = activity.getGears();
        summary = activityName+ " was recorded on "+ date.getTime()
                +".\nThe total distance covered was "+ distance
                +".\nAnd the total calories burnt were "+ caloriesBurnt
                +"kcal.\nThe gear used: "+gears.getName()+".";


        System.out.println(summary);
    }
}
