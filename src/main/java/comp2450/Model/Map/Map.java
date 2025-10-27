package comp2450.Model.Map;

import comp2450.Model.Activity.Activity;
import comp2450.Model.Activity.Route;
import comp2450.Model.Person.Person;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Map that contains {@link Obstacle}s and {@link Route} for activities done by {@link Person}.
 * The Map stores a grid of {@link IMapping} objects where each cell can be an {@link Obstacle} or {@link Route}.
 * Each {@link Activity} can add its {@link Route} to the grid.
 * {@link Obstacle} are added to the Grid along with {@link Route}
 */
public class Map {

    final private String name;
    final private List<Obstacle> obstacles;
    final private List<Route> routes;
    final private Dimensions dimensions;
    final private IMapping[][]myGrid;

    private void checkMap(){
        Preconditions.checkNotNull(name, "Name of the Map> can never be null");
        Preconditions.checkState(name.length()>=1, "A Map should be assigned an empty name");
        Preconditions.checkNotNull(obstacles, "Obstacles cannot be null");
        Preconditions.checkNotNull(routes, "Routes cannot be null");
        Preconditions.checkNotNull(dimensions, "Dimensions should be assigned for a map");

        Preconditions.checkNotNull(myGrid, "The grid can never be null");
        Preconditions.checkState(myGrid.length >= 1, "Map must have atleast one Row");
        Preconditions.checkState(myGrid[0].length >= 1, "Map must have atleast one Coloumn");

        for (Obstacle o : obstacles) {
            Preconditions.checkNotNull(o, "Obstacle in the list should never be null.");

            List<Coordinates> cod = o.getCoordinates();
            for(Coordinates coordiante: cod){
                int x = coordiante.xCoordinates();
                int y = coordiante.yCoordinates();
                Preconditions.checkState(x<myGrid.length && y<myGrid[0].length,
                        "Coordinates of Obstacles can never be outside the GRID");
            }

        }
        for (Route r : routes) {
            Preconditions.checkNotNull(r, "Routes in the list should never be null.");
            List<Coordinates> cod = r.getCoordinates();
            for(Coordinates coordiante: cod){
                int x = coordiante.xCoordinates();
                int y = coordiante.yCoordinates();
                Preconditions.checkState(x<myGrid.length && y<myGrid[0].length,
                        "Coordinates of Route can never be outside the GRID");
            }
        }


    }

    public Map(String name, Dimensions dimensions){
        this.name = name;
        this.dimensions =dimensions;
        this.obstacles = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.myGrid = new IMapping[dimensions.nRows()][dimensions.nCols()];
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
     * Adds a new {@link Route} to the Map and updates List {@link #routes}
     * @param route to be added to the {@link Map}
     */
    public void addRoutes(Route route){
        checkMap();
        routes.add(route);
        checkMap();
    }
    /**
     * Removes this {@link Route} from the map and list of {@link #routes}
     * @param route is the object To be removed from the list.
     */
    public void removeRoute(Route route){

        checkMap();
        routes.remove(route);
        checkMap();
    }


    public void createGrid(List<Obstacle> obs, List<Route> route){

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

        ArrayList<Coordinates> myObsC   = new ArrayList<>(obs.getCoordinates());

        for(Coordinates cood: myObsC){
            int x = cood.xCoordinates();
            int y = cood.yCoordinates();

            this.myGrid[x][y] = obs;
        }
        checkMap();

    }

    /**
     * Adds each {@link Route} from the {@link Activity} to {@link #myGrid} using {@link #addRoute}
     * @param routes is the list of all Routes to be added to the {@link #myGrid}.
     */
    private void addRouteToGrid(List<Route> routes){
        checkMap();

        for(Route route: routes){
            addRoute(route);
        }
        checkMap();


    }
    /**
     * This method is called internally by {@link #addRouteToGrid} which then take individual {@link Activity}
     * And look for it's {@link Route} and assign {@link Coordinates} of {@link Route} to {@link #myGrid}
     * @param route is the route chosen from {@link Activity} and added.
     */
    private void addRoute(Route route){
        checkMap();

        ArrayList<Coordinates> myRoute = new ArrayList<>(route.getCoordinates());
        for(Coordinates cood: myRoute){
            int x = cood.xCoordinates();
            int y = cood.yCoordinates();

            myGrid[x][y] = route;
        }
        checkMap();

    }

    /*
    Getters
     */
    public List<Obstacle> getObsInMap(){

        return obstacles;
    }
    public List<Route> getRouteInMap(){

        return routes;
    }
    public Dimensions getDimensions(){

        return dimensions;
    }
    public IMapping[][] getGrid(){
        return this.myGrid;
    }
    public String getName(){
        return this.name;
    }

}

