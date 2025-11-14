package comp2450.Output;

import comp2450.Model.Activity.Activity;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class PrintActivity {

    final private Activity activity;
    final private Person person;

    public PrintActivity(Activity activity, Person person) {
        this.activity = activity;
        this.person = person;
    }

    /**
     * Prints the summary of my activity, including total distance,calories burnt of this {@link Activity}
     */
    public void printSummary(){

        String summary = "";
        String activityName = activity.getName();
        LocalDateTime date = activity.getCalendar();
        double distance = activity.getDistance();
        double caloriesBurnt = activity.caloriesBurnt(person);
        LocalTime time = date.toLocalTime();


        Gears gears = activity.getGears();
        summary = activityName+ " was recorded on "+ time
                +".\nThe total distance covered was "+ distance
                +".\nAnd the total calories burnt were "+ caloriesBurnt
                +"kcal.\nThe gear used: "+gears.getName()+".";


        System.out.println(summary);
    }
}
