package comp2450.Model.Map;

import comp2450.Model.Map.Map;
import comp2450.Model.Map.Obstacle;
import comp2450.Model.Activity.Route;
import com.google.common.base.Preconditions;

/**
 * The coordinates (x and y) are of {@link Obstacle} or {@link Route} to be presented on {@link Map}
 * @param xCoordinates
 * @param yCoordinates
 */
public record Coordinates(int xCoordinates, int yCoordinates) {


    private void checkCoordinates(){
        Preconditions.checkState(xCoordinates>=0,"The coordinates can never be negative and need to be on map");
        Preconditions.checkState(yCoordinates>=0,"The coordinates can never be negative and need to be on map");
    }
    public Coordinates(int xCoordinates, int yCoordinates ){

        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;

        checkCoordinates();
    }

}
