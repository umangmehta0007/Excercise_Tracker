package comp2450.UI;

import comp2450.Logic.Tracker;
import comp2450.Model.ExerciseTracker;

import java.util.Scanner;

public class TrackerDisplay {

    private Tracker tracker;
    private final Scanner sc;


    public TrackerDisplay(ExerciseTracker tracker) {
        sc = new Scanner(System.in);
        this.tracker = new Tracker(tracker);
    }

    
}
