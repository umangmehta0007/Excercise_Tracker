package comp2450.Model.Map;


import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Obstacle on a Map.
 * An Obstacle has a name and a list of {@link Coordinates} it occupies.
 * Implements {@link IMapDataType}.
 */
public class Obstacle implements IMapDataType, IObjectsWithCoordinates{

    final private String name;
    final private List<Coordinates> coordinatesCovered;


    private void checkObstacle(){
        Preconditions.checkNotNull(name, "Name of the Obstacle can never be null");
        Preconditions.checkState(name.length()>=1, "An Obstacle should be assigned a non empty name");

        Preconditions.checkNotNull(coordinatesCovered, "Coordinates covered by Obstacles can never be null");
        for(Coordinates cood: coordinatesCovered){
            Preconditions.checkNotNull(cood, "A coordinates of a Obstacle can never be null");
        }

    }
    public Obstacle(String name){
        this.name = name;
        this.coordinatesCovered = new ArrayList<>();
        checkObstacle();
    }


    /**
     * Adds {@link Coordinates} to the list of my all {@link #coordinatesCovered}
     * which includes all {@link Coordinates}  covered by {@link Obstacle}
     * @param coordinates this is the current Coordinate to be added to the list.
     */
    public void addCoordinates(Coordinates coordinates){

        Preconditions.checkNotNull(coordinates, "Coordinates to be added can never be null");
        checkObstacle();
        coordinatesCovered.add(coordinates);
        checkObstacle();
    }

    public String getName(){
        return this.name;
    }

    public List<Coordinates> getMappingObject(){

        return coordinatesCovered;
    }
}
