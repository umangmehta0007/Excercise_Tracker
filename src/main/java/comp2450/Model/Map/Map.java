package comp2450.Model.Map;

import comp2450.Model.Activity.Activity;
import comp2450.Model.Person.Person;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Map that contains {@link Obstacle}s and {@link Activity} done by {@link Person}.
 * The Map stores a grid of {@link IMapDataType} objects where each cell can be an {@link Obstacle} or {@link Activity}.
 * Each {@link Activity} can add its route to the grid.
 * {@link Obstacle} are added to the Grid along with {@link Activity}
 */
public class Map {

    final private String name;
    final private List<Obstacle> obstacles;
    final private List<Activity> activities;
    final private Dimensions dimensions;
    final private IMapDataType[][]myGrid;

    private void checkMap(){
        Preconditions.checkNotNull(name, "Name of the Map> can never be null");
        Preconditions.checkState(name.length()>=1, "A Map should be assigned an empty name");
        Preconditions.checkNotNull(obstacles, "Obstacles cannot be null");
        Preconditions.checkNotNull(activities, "Activities cannot be null");
        Preconditions.checkNotNull(dimensions, "Dimensions should be assigned for a map");

        Preconditions.checkNotNull(myGrid, "The grid can never be null");
        Preconditions.checkState(myGrid.length >= 1, "Map must have atleast one Row");
        Preconditions.checkState(myGrid[0].length >= 1, "Map must have atleast one Coloumn");

        for (Obstacle o : obstacles) {
            Preconditions.checkNotNull(o, "Obstacle in the list should never be null.");

            List<Coordinates> cod = o.getMappingObject();
            for(Coordinates coordiante: cod){
                int x = coordiante.xCoordinates();
                int y = coordiante.yCoordinates();
                Preconditions.checkState(x<myGrid.length && y<myGrid[0].length,
                        "Coordinates of Obstacles can never be outside the GRID");
            }

        }
        for (Activity act : activities) {
            Preconditions.checkNotNull(act, "Routes in the list should never be null.");
            List<Coordinates> cod = act.getMappingObject();
            for(Coordinates coordinate: cod){
                int x = coordinate.xCoordinates();
                int y = coordinate.yCoordinates();
                Preconditions.checkState(x<myGrid.length && y<myGrid[0].length,
                        "Coordinates of Route can never be outside the GRID");
            }
        }


    }

    public Map(String name, Dimensions dimensions){
        this.name = name;
        this.dimensions =dimensions;
        this.obstacles = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.myGrid = new IMapDataType[dimensions.nRows()][dimensions.nCols()];

        for(int i = 0; i<myGrid.length;i++){
            for(int j = 0; j<myGrid[i].length;j++){

                myGrid[i][j] = new Empty(); //replacing null with Empty Objects created.

            }
        }
        checkMap();
    }


    /**
     * Adds a new {@link Obstacle} to the Map and updates List {@link #obstacles}
     * @param obs to be added to the {@link Map}
     */
    public void addObstacle(Obstacle obs){
        checkMap();
        obstacles.add(obs);
        checkMap();
    }
    /**
     * Removes this {@link Obstacle} from the map and list of {@link #obstacles}
     * @param index To be removed from the list.
     */
    public void removeObstacle(int index){

        checkMap();
        obstacles.remove(index);
        checkMap();

    }

    /**
     * Adds a new {@link Activity} to the Map
     * @param activity to be added to the {@link Map}
     */
    public void addActivity(Activity activity){
        checkMap();
        activities.add(activity);
        checkMap();
    }
    /**
     * Removes this {@link Activity} from the map
     * @param index is the object To be removed from the list.
     */
    public void removeActivity(int index){

        checkMap();
        activities.remove(index);
        checkMap();
    }

    public void createGrid(List<Obstacle> obs, List<Activity> route){

        checkMap();
        addObstacleToGrid(obs);
        addRouteToGrid(route);
        checkMap();

    }
    /**
     * Adds each {@link Obstacle} from the list {@link #obstacles} to {@link #myGrid} using {@link #addObs}
     * @param obstacles the obstacles to be added to the {@link #myGrid}.
     */
    private void addObstacleToGrid(List<Obstacle> obstacles){

        checkMap();

        for(Obstacle obs: obstacles){
            addObs(obs);
        }
        checkMap();

    }
    /**
     * This method is called internally by {@link #addObstacleToGrid} which then take individual {@link Obstacle}
     * And look for it's {@link Coordinates} and assign {@link Obstacle} to that {@link Coordinates} to {@link #myGrid}
     * @param obs is the obstacle chosen from list and added.
     */
    private void addObs(Obstacle obs){

        checkMap();

        ArrayList<Coordinates> myObsC   = new ArrayList<>(obs.getMappingObject());

        for(Coordinates cood: myObsC){
            int x = cood.xCoordinates();
            int y = cood.yCoordinates();

            this.myGrid[x][y] = obs;
        }
        checkMap();

    }

    /**
     * Adds each Route of {@link Activity} from the list {@link #activities} to {@link #myGrid} using {@link #addRoute}
     * @param activities the activities whose routes are to be added to the {@link #myGrid}.
     */
    private void addRouteToGrid(List<Activity> activities){
        checkMap();

        for(Activity activity: activities){

            addRoute(activity);
        }
        checkMap();
    }

    private void addRoute(Activity act){
        checkMap();
        List<Coordinates> route = act.getMappingObject();
        for(Coordinates cood: route){
            int x = cood.xCoordinates();
            int y = cood.yCoordinates();
            myGrid[x][y] = act;
        }
        checkMap();

    }

    /*
    Getters
     */
    public List<Obstacle> getObsInMap(){

        return obstacles;
    }
    public List<Activity> getActivities(){

        return activities;
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

