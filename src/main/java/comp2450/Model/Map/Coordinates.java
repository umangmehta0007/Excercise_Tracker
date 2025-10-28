package comp2450.Model.Map;

import com.google.common.base.Preconditions;

/**
 * The coordinates (x and y) are of {@link Obstacle} or Route in {@Activity} to be presented on {@link Map}
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
