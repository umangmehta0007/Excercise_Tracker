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
import comp2450.Model.Person.Person;
import comp2450.Persistence.NotFoundException;
import comp2450.Persistence.PersonPersistence;
import comp2450.Persistence.json.PersonPersistenceJson;
import comp2450.UI.FeedDisplay;
import comp2450.UI.PersonManagementDisplay;
import comp2450.UI.TrackerDisplay;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PersonPersistence persistence = new PersonPersistenceJson(Path.of("person.json"));

        ExerciseTracker myManager = new ExerciseTracker();

        try {
            Collection<Person> p = persistence.loadList();
            myManager.setList(new ArrayList<Person>(p));
        } catch (NotFoundException e) {

        }
        Map map = null;


        //All of my logic layers should be able to do persistence now


        //Persistence of all/any profiles created
        MapLogic ml = createMap(map);
        PersonLogic pl = new PersonLogic(persistence);
        RouteLogic rl = new RouteLogic(pl, ml);
        Tracker tl = new Tracker(myManager, persistence);

        PersonManagementDisplay personDisplay = new PersonManagementDisplay(sc, pl, ml, rl);
        FeedDisplay feed = new FeedDisplay(sc, pl, ml, personDisplay);
        TrackerDisplay display = new TrackerDisplay(sc, tl, feed);

        display.startRecording();
    }

    public static MapLogic createMap(Map map) {

        try {
            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder builder = new Map.MapBuilder();
            map = builder.createName("Winnipeg").dimensions(dim).build();

        } catch (InvalidNameException | InvalidCoordinatesException ice) {
        }


        return new MapLogic(map);
    }
}
