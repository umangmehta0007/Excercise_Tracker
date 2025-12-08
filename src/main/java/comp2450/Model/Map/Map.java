package comp2450.Model.Map;

import comp2450.Model.Activity.Activity;
import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Exceptions.InvalidNameException;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Map that contains {@link Obstacle} created by Everyone.
 * The Map stores a grid of {@link IMapDataType} objects where each cell can be an {@link Obstacle} or {@link Activity}.
 * Each {@link Activity} can add its route to the grid.
 * {@link Obstacle} are added to the Grid.
 */
public class Map {

    final private String name;
    final private List<Obstacle> obstacles;
    //final private List<Activity> activities;
    final private Dimensions dimensions;
    final private IMapDataType[][]myGrid;

    private void checkMap(){
        Preconditions.checkNotNull(name, "Name of the Map> can never be null");
        Preconditions.checkState(name.length()>=1, "A Map should be assigned an empty name");
        Preconditions.checkNotNull(obstacles, "Obstacles cannot be null");
        //Preconditions.checkNotNull(activities, "Activities cannot be null");
        Preconditions.checkNotNull(dimensions, "Dimensions should be assigned for a map");

        Preconditions.checkNotNull(myGrid, "The grid can never be null");
        Preconditions.checkState(myGrid.length >= 1, "Map must have atleast one Row");
        Preconditions.checkState(myGrid[0].length >= 1, "Map must have atleast one Coloumn");

        for (Obstacle o : obstacles) {
            Preconditions.checkNotNull(o, "Obstacle in the list should never be null.");

        }

        for(int i = 0; i < myGrid.length; i++) {
            for (int j = 0; j < myGrid[i].length; j++) {
                Preconditions.checkNotNull(myGrid[i][j],"Grid cell must never be null.");
            }
        }

    }

    private Map(String name, Dimensions dimensions,IMapDataType[][]myGrid) {
        this.name = name;
        this.dimensions =dimensions;
        this.obstacles = new ArrayList<>();
        //this.activities = new ArrayList<>();
        this.myGrid = myGrid;
        checkMap();
    }

    public static class MapBuilder {

        private String name;
        private Dimensions dimensions;
        private IMapDataType[][]myGrid;


        public MapBuilder() {}


        public MapBuilder createName(String name) throws InvalidNameException{

            Preconditions.checkNotNull(name, "Name can never be initialized as null");

            if(name.isBlank()){
                throw new InvalidNameException();
            }
            this.name = name;
            return this;
        }

        public MapBuilder dimensions(Dimensions dim){

            Preconditions.checkNotNull(dim, "Dimensions can never be null");

            this.dimensions = dim;
            return this;

        }

        private void makeGrid() throws InvalidCoordinatesException {

            myGrid = new IMapDataType[dimensions.nRows()][dimensions.nCols()];

            for (int i = 0; i < dimensions.nRows(); i++) {
                for (int j = 0; j < dimensions.nCols(); j++) {
                    Coordinates cod = new Coordinates.CoordinateBuilder()
                            .xCoordinates(i)
                            .yCoordinates(j)
                            .build();

                    myGrid[i][j] = new Empty(cod);
                }
            }
        }

        public Map build() throws InvalidCoordinatesException {

            makeGrid();
            return new Map(name, dimensions, myGrid);
        }


    }

    /**
     * Adds a new {@link Obstacle} to the Map and updates List {@link #obstacles}
     * @param obs to be added to the {@link Map}
     */
    public void addObstacle(Obstacle obs){

        Preconditions.checkNotNull(obs, "Obstacle to be added can never be null");
        checkMap();
        obstacles.add(obs);
        checkMap();
    }
    /**
     * Removes this {@link Obstacle} from the map and list of {@link #obstacles}
     * @param index To be removed from the list.
     */
    public void removeObstacle(int index){

        Preconditions.checkState(index>=0, "Index should be always greater than 0");
        checkMap();
        obstacles.remove(index);
        checkMap();

    }

    /*
    Getters
     */
    public List<Obstacle> getObsInMap(){

        return obstacles;
    }

    public Dimensions getDimensions(){

        return dimensions;
    }
    public IMapDataType[][] getGrid(){
        return this.myGrid;
    }
    public String getName(){
        return this.name;
    }

}