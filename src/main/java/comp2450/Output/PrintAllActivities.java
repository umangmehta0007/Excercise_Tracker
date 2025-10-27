package comp2450.Output;

import comp2450.Model.Activity.Activity;

import java.util.List;
/**
 * Prints summaries for a list of {@link Activity} objects.
 */
public class PrintAllActivities {

    final private List<Activity> activities;


    public PrintAllActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void printActivities(){
        int count = 1;

        for(Activity activity: activities){ //for each loops calls in every single activity

            System.out.print(count+". ");
            new PrintActivity(activity).printSummary(); //calls in print of each indivisual activity


            System.out.println();

            count++;
        }


    }
}
