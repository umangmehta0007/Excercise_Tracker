package comp2450;

import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Exceptions.InvalidNameException;
import comp2450.Logic.MapLogic;
import comp2450.Logic.PersonLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Logic.Tracker;
import comp2450.Model.Person.ExerciseTracker;
import comp2450.Model.Map.Dimensions;
import comp2450.Model.Map.Map;
import comp2450.UI.FeedDisplay;
import comp2450.UI.PersonManagementDisplay;
import comp2450.UI.TrackerDisplay;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {


    /*
    As per the dependency inversion, we did is using the technique known as dependency Injection.

    We injected the UI/Logic that we initially were giving power to know what's it doing.
    UI should only care how to even construct instance of dependency and shouldn't have responsibility to construct.
    Because that will tell how it works internally.

    Using injection, we'll do it. At least we're going to use interface type in java.
    We're going to change classes to accept instance of other classes
    Accept instance of dependencies.

    Main class constructs and passes the dependencies.

    UI should only care what it can do.
     */
    public static void main(String[]args) throws IOException {

        ExerciseTracker myManager = new ExerciseTracker();

        Map map = null;
        /*
        I was trying to do somehting like createMap(map, ml) and inside that update ml as ml = new Maplogic(Map)
        Which was highly incorrect as I was creating a local insitance and was going out of scope as my that ended.
         */
        MapLogic ml = createMap(map);
        PersonLogic pl = new PersonLogic();
        RouteLogic rl = new RouteLogic(pl, ml);


        /*
        *Initially Person logic had two arguments, which we won't know unless we run the program
        * So now instead we removed all parameters and made setter for those which our pl logic layer will call
        * Doing this our route logic won't have null entries in here too.
         */

        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(Path.of("activity.txt"));

        Tracker tl = new Tracker(myManager);

        PersonManagementDisplay personDisplay= new PersonManagementDisplay(sc, pl, ml, rl);
        FeedDisplay feed = new FeedDisplay(sc,pl, ml, personDisplay);
        TrackerDisplay display = new TrackerDisplay(sc, tl, feed );

        display.startRecording();
    }

    public static MapLogic  createMap(Map map){

        try {
            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder builder = new Map.MapBuilder();
            map = builder.createName("Winnipeg").dimensions(dim).build();

        } catch (InvalidNameException | InvalidCoordinatesException ice) {
        }


        return new MapLogic(map);
    }
}
