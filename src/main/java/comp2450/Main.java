package comp2450;

import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Exceptions.InvalidNameException;
import comp2450.Model.Person.ExerciseTracker;
import comp2450.Model.Map.Dimensions;
import comp2450.Model.Map.Map;
import comp2450.UI.TrackerDisplay;

public class Main {

    public static void main(String[]args){

        Map map = null;

        try{
            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder builder = new Map.MapBuilder();
            map = builder.createName("Winnipeg").dimensions(dim).build();

        }
        catch (InvalidNameException | InvalidCoordinatesException ice) {
        }

        ExerciseTracker myTracker = new ExerciseTracker();

        TrackerDisplay display = new TrackerDisplay(myTracker, map);

        display.startRecording();
    }
}
