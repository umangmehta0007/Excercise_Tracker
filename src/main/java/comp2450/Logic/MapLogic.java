package comp2450.Logic;

import comp2450.Logic.Exceptions.CoordinatesOutOfBoundsException;
import comp2450.Logic.Exceptions.ObstacleAlreadyExistsException;
import comp2450.Logic.Exceptions.RouteAlreadyExistsException;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Exceptions.InvalidCoordinatesException;
import comp2450.Model.Exceptions.InvalidNameException;
import comp2450.Model.Map.*;
import comp2450.Model.Person.Person;

public class MapLogic {

  final private Map map;
  final private Dimensions dim;

    public MapLogic(Map map) {

        this.map = map;
        //this.person = person;
        this.dim = map.getDimensions();
    }

    public Map getMap(){
        return this.map;
    }

    public void checkValidRouteCood(Coordinates cod) throws CoordinatesOutOfBoundsException, ObstacleAlreadyExistsException {

        checkWithinBound(cod);
        notObstacle(cod);
    }
    public void checkValidObsCod(Coordinates cod) throws CoordinatesOutOfBoundsException,RouteAlreadyExistsException {

        checkWithinBound(cod);
        notRoute(cod);
    }

    public Dimensions getDimensions(){

        return this.dim;
    }

    public void addObstacle(Obstacle obs) {
        map.addObstacle(obs);
    }

    public void removeObstacle(int index) {
        map.getObsInMap().remove(index);
    }

    public void addActivity(Activity act) {
        map.addActivity(act);
    }

    public void removeActivity(int index) {
        map.getActivities().remove(index);
    }


    private void checkWithinBound(Coordinates cod) throws CoordinatesOutOfBoundsException {
        int x = cod.xCoordinates();
        int y = cod.yCoordinates();

        if(x>=(dim.nRows()) || y>=(dim.nCols())){
            throw new CoordinatesOutOfBoundsException();
        }

    }
    private void notObstacle(Coordinates cod) throws ObstacleAlreadyExistsException{

        int x = cod.xCoordinates();
        int y = cod.yCoordinates();
        IMapDataType[][] myGrid = map.getGrid();
        if(myGrid[x][y] instanceof Obstacle){
            throw new ObstacleAlreadyExistsException();
        }
    }

    public void resetGrid() throws InvalidCoordinatesException {


        IMapDataType[][] myGrid = map.getGrid();

        for(int i = 0; i<myGrid.length;i++){
            for(int j = 0; j<myGrid[i].length;j++){



                IMapDataType data = myGrid[i][j];

                if(data instanceof Activity){
                    Coordinates cod = new Coordinates.CoordinateBuilder()
                            .xCoordinates(i)
                            .yCoordinates(j)
                            .build();

                    myGrid[i][j] = new Empty(cod);
                }

                //replacing null with Empty Objects created.

            }
        }

    }
    private void notRoute(Coordinates cod) throws RouteAlreadyExistsException {

        int x = cod.xCoordinates();
        int y = cod.yCoordinates();
        IMapDataType[][] myGrid = map.getGrid();
        if(myGrid[x][y] instanceof Activity){
            throw new RouteAlreadyExistsException();
        }
    }



}
