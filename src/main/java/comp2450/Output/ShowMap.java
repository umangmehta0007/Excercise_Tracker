package comp2450.Output;

import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.IMapDataType;
import comp2450.Model.Person.*;
import java.util.Calendar;

/**
 * Displays a map of activities and obstacles within a date range (week, lifetime, month ) custom chosen.
 * Prints the legend, the activity grid, total distance, and total calories burnt.
 */
public class ShowMap {
    final private char OBS = '*';
    final private char ROUTE = '>';
    final private char EMPTY = '.';

    final private IMapDataType[][]MY_GRID_ACTIVITIES;
    final private Calendar startingDate;
    final private Calendar endingDate;
    final private Person person;



    /**
     * Initializes ShowMap with a grid of {@link Activity}, activity list, and date range using {@link Calendar}.
     * @param MY_GRID_ACTIVITIES the 2D grid of {@link IMapDataType} representing activities
     * @param person the list of {@link Person} objects
     * @param startingDate the start date for statistics
     * @param endingDate the end date for statistics
     */
    public ShowMap(IMapDataType[][] MY_GRID_ACTIVITIES, Person person, Calendar startingDate, Calendar endingDate) {
        this.MY_GRID_ACTIVITIES = MY_GRID_ACTIVITIES;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.person = person;
    }

    /**
     * Prints the map with legend, grid, and statistics (total distance and calories burnt).
     */
    public void printMap(){

        MapLegend.printLegend(EMPTY, ROUTE, OBS);
        MapLegend.printGrid(MY_GRID_ACTIVITIES, EMPTY, ROUTE, OBS);

        System.out.println("The Total distance travelled was "+ person.totalDistance(startingDate, endingDate )+ "KM.");
        System.out.println("The Total Calories burnt were  "+ person.totalCalories(startingDate, endingDate )+ " kcal.");

    }


}
