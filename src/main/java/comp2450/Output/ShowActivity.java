package comp2450.Output;

import comp2450.Model.Map.IMapDataType;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.Obstacle;

/**
 * Shows a visual representation of a single {@link Activity} on the map grid.
 * Prints both the legend and the grid using symbols for empty spaces, Route for my {@link Activity}, and {@link Obstacle}.
 */
public class ShowActivity {


    final private IMapDataType[][] MY_GRID_ACTIVITY;
    final private char OBS = '*';
    final private char ROUTE = '>';
    final private char EMPTY = '.';


    public ShowActivity(IMapDataType[][] MY_GRID_ACTIVITY) {
        this.MY_GRID_ACTIVITY = MY_GRID_ACTIVITY;
    }

    public void printActivity(){
       MapLegend.printLegend(EMPTY, ROUTE, OBS);
       MapLegend.printGrid(MY_GRID_ACTIVITY, EMPTY, ROUTE, OBS);
   }
}
