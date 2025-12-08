package comp2450.Logic;


import com.google.common.base.Preconditions;
import comp2450.Exceptions.Exceptions.CoordinatesOutOfBoundsException;
import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Exceptions.Exceptions.ObstacleAlreadyExistsException;
import comp2450.Exceptions.Exceptions.RouteAlreadyExistsException;
import comp2450.Model.Activity.Activity;
import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Map.Dimensions;
import comp2450.Model.Map.Empty;
import comp2450.Model.Map.IMapDataType;
import comp2450.Model.Map.Map;
import comp2450.Model.Map.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class MapLogic {

    final private Map map;
    final private Dimensions dim;

    public MapLogic(Map map) {
        this.map = map;
        this.dim = map.getDimensions();
    }

    public void checkMapLogic() {
        Preconditions.checkNotNull(map, "Map can never be null");
        Preconditions.checkNotNull(dim, "Dimensions can never be null");
    }

    public Map getMap() {
        return this.map;
    }

    public void checkValidRouteCood(Coordinates cod) throws CoordinatesOutOfBoundsException, ObstacleAlreadyExistsException {

        Preconditions.checkNotNull(cod, "Coordinates to be check should never be null");
        checkMapLogic();
        checkWithinBound(cod);
        notObstacle(cod);
        checkMapLogic();
    }

    public void checkValidObsCod(Coordinates cod) throws CoordinatesOutOfBoundsException, RouteAlreadyExistsException, ObstacleAlreadyExistsException {

        checkMapLogic();
        checkWithinBound(cod);
        notObstacle(cod);
        checkMapLogic();

    }

    public Dimensions getDimensions() {

        return this.dim;
    }

    public void addObstacle(Obstacle obs) {
        map.addObstacle(obs);
    }

    public void removeObstacle(int index) throws InvalidSelectionException {

        Preconditions.checkState(index >= 0, "index should alwyas eb greater than = 0");

        checkMapLogic();

        if (index < 0 || index > map.getObsInMap().size()) {
            throw new InvalidSelectionException();
        }

        map.removeObstacle(index);

        checkMapLogic();


    }

    public List<Obstacle> getAllObstacle() {

        return map.getObsInMap();
    }

    public void createGrid(List<Activity> routes) throws InvalidCoordinatesException {

        Preconditions.checkNotNull(routes, "Activity to be check should never be null");
        for (var r : routes) {
            Preconditions.checkNotNull(r, "Activity in list to be check should never be null");
        }

        checkMapLogic();

        resetGrid();

        addObstacleToGrid(map.getObsInMap());
        addRouteToGrid(routes);

        checkMapLogic();


    }

    private void addObstacleToGrid(List<Obstacle> obstacles) {

        Preconditions.checkNotNull(obstacles, "Obstacles to be check should never be null");
        for (var r : obstacles) {
            Preconditions.checkNotNull(r, "Obstacles in list to be check should never be null");
        }
        checkMapLogic();

        IMapDataType[][] grid = map.getGrid();

        for (Obstacle obs : obstacles) {
            addObs(grid, obs);
        }

        checkMapLogic();

    }

    private void addObs(IMapDataType[][] grid, Obstacle obs) {

        Preconditions.checkNotNull(grid, "Grid can never be null");
        Preconditions.checkNotNull(obs, "Obstacle can never be null");


        checkMapLogic();
        List<Coordinates> myObsC = new ArrayList<>(obs.getMappingObject());

        for (Coordinates cod : myObsC) {
            int x = cod.xCoordinates();
            int y = cod.yCoordinates();
            grid[x][y] = obs;
        }

        checkMapLogic();

    }

    private void addRouteToGrid(List<Activity> activities) {

        Preconditions.checkNotNull(activities, "Activity to be check should never be null");
        for (var r : activities) {
            Preconditions.checkNotNull(r, "Activity in list to be check should never be null");
        }

        checkMapLogic();

        IMapDataType[][] grid = map.getGrid();

        for (Activity activity : activities) {
            addRoute(grid, activity);
        }

        checkMapLogic();

    }

    private void addRoute(IMapDataType[][] grid, Activity act) {

        Preconditions.checkNotNull(grid, "Grid can never be null");
        Preconditions.checkNotNull(act, "Activity can never be null");
        checkMapLogic();

        List<Coordinates> route = act.getMappingObject();

        for (Coordinates cod : route) {
            int x = cod.xCoordinates();
            int y = cod.yCoordinates();
            grid[x][y] = act;
        }

        checkMapLogic();
    }

    public void checkWithinBound(Coordinates cod) throws CoordinatesOutOfBoundsException {

        Preconditions.checkNotNull(cod, "Coordinates can never be null");

        checkMapLogic();

        int x = cod.xCoordinates();

        int y = cod.yCoordinates();

        if (x >= (dim.nRows()) || y >= (dim.nCols())) {
            throw new CoordinatesOutOfBoundsException();
        }
        checkMapLogic();


    }

    private void notObstacle(Coordinates cod) throws ObstacleAlreadyExistsException {
        Preconditions.checkNotNull(cod, "Coordinates can never be null");

        checkMapLogic();

        List<Obstacle> list = map.getObsInMap();

        for (var obs : list) {
            List<Coordinates> coords = obs.getMappingObject();
            for (Coordinates c : coords) {
                if (c.equals(cod)) {
                    throw new ObstacleAlreadyExistsException();
                }
            }
        }
        checkMapLogic();

    }

    public void resetGrid() throws InvalidCoordinatesException {

        checkMapLogic();

        IMapDataType[][] myGrid = map.getGrid();

        for (int i = 0; i < myGrid.length; i++) {
            for (int j = 0; j < myGrid[i].length; j++) {

                Coordinates cod = new Coordinates.CoordinateBuilder()
                        .xCoordinates(i)
                        .yCoordinates(j)
                        .build();

                myGrid[i][j] = new Empty(cod);   // ALWAYS reset to empty
            }
        }
        checkMapLogic();

    }
}

