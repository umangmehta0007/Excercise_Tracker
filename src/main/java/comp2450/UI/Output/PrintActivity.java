package comp2450.UI.Output;

import comp2450.Model.Activity.Activity;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PrintActivity {

    final private Activity activity;

    public PrintActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Prints the summary of my activity, including total distance of this {@link Activity}
     */
    public void printSummary(){

        String summary = "";
        String activityName = activity.getName();
        LocalDateTime date = activity.getCalendar();
        double distance = activity.getDistance();
        LocalTime time = date.toLocalTime();


        Gears gears = activity.getGears();
        summary = activityName+ " was recorded on "+ time
                +".\nThe total distance covered was "+ distance
                +"kcal.\nThe gear used: "+gears.getName()+".";

        System.out.println(summary);
    }
}
