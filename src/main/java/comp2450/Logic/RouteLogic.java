package comp2450.Logic;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.Exceptions.CoordinatesOutOfBoundsException;
import comp2450.Model.Activity.Activity;
import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Exceptions.InvalidRouteSelectionException;
import comp2450.Exceptions.NoPathAvailableException;
import comp2450.Exceptions.NoRouteAvailableException;
import comp2450.Model.LinkedListStack;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Map.Dimensions;
import comp2450.Model.Person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class RouteLogic {
    private final PersonLogic pl;
    private final MapLogic ml;
    private final List<Activity> act;

    public RouteLogic(PersonLogic pl, MapLogic ml) {
        this.pl = pl;
        this.act =  pl.getPerson().getMyActivityList();
        this.ml = ml;
    }

    public void checkRouteLogic(){
        Preconditions.checkNotNull(pl, "Logic of person can never be null");
        Preconditions.checkNotNull(ml, "Logic of Map can never be null");
        Preconditions.checkNotNull(act, "Activities of person can never be null");
        for(var x: act){
            Preconditions.checkNotNull(x, "Activities of person can never be null");
        }
    }
    public List<Activity> activityList(){
        return this.act;
    }

    public List<Coordinates> getRoute(int index) throws InvalidRouteSelectionException {

        Preconditions.checkState(index>=0, "Index should alwayas be >=0");

        checkRouteLogic();
        if(index<0 || index>=act.size()){
            throw new InvalidRouteSelectionException();
        }

        checkRouteLogic();
        return act.get(index).getMappingObject();
    }

    public List<Coordinates> pathFinding(int selection, List<Coordinates> sePoints) throws InvalidRouteSelectionException, NoPathAvailableException, NoRouteAvailableException {

        Preconditions.checkState(selection>0 && selection <3, "Type of Path should alwayas be between 0 and 3");
        Preconditions.checkNotNull(sePoints, "Starting and ending coordinatate can never be null");
        for(var x: sePoints){
            Preconditions.checkNotNull(x, "Starting and ending coordinatate can never be null");
        }
        Preconditions.checkState(sePoints.size() == 2, "There hsould be exactly one starting and ending points");

        checkRouteLogic();

        List<Coordinates> result = null;

        if(selection<1 ||selection>2){
            throw new InvalidRouteSelectionException();
        }

        if(selection ==1){

            if (pl.getActivities().isEmpty()) {
                throw new NoRouteAvailableException();
            }

            result = findRouteSelf(sePoints);

        }else{
            if (pl.getPerson().getFollowing().isEmpty()) {
                throw new NoRouteAvailableException();
            }
            result = findRouteFeed(sePoints);

        }
        checkRouteLogic();

        return result;
    }

    private List<Coordinates> findRouteSelf(List<Coordinates> sePoints) throws NoPathAvailableException {

        Preconditions.checkNotNull(sePoints, "Starting and ending coordinatate can never be null");
        for(var x: sePoints){
            Preconditions.checkNotNull(x, "Starting and ending coordinatate can never be null");
        }
        Preconditions.checkState(sePoints.size() == 2, "There hsould be exactly one starting and ending points");

        checkRouteLogic();

        List<Coordinates> myRoute = null;
        boolean[][]routeGrid = makeGridPerson(ml.getDimensions());

        myRoute = pathFindingAlg(routeGrid, sePoints);
        checkRouteLogic();
        return myRoute;

    }

    private List<Coordinates> findRouteFeed(List<Coordinates> sePoints) throws NoPathAvailableException {
        Preconditions.checkNotNull(sePoints, "Starting and ending coordinatate can never be null");
        for(var x: sePoints){
            Preconditions.checkNotNull(x, "Starting and ending coordinatate can never be null");
        }
        Preconditions.checkState(sePoints.size() == 2, "There hsould be exactly one starting and ending points");
        checkRouteLogic();
        List<Coordinates> myRoute = null;

        boolean[][]routeGrid = makeGridFeed(ml.getDimensions());

        myRoute = pathFindingAlg(routeGrid, sePoints);

        checkRouteLogic();

        return myRoute;
    }

    private List<Coordinates> pathFindingAlg(boolean[][]myGrid, List<Coordinates>sePoints)throws NoPathAvailableException{

        Preconditions.checkNotNull(sePoints, "Starting and ending coordinatate can never be null");
        for(var x: sePoints){
            Preconditions.checkNotNull(x, "Starting and ending coordinate can never be null");
        }
        Preconditions.checkState(sePoints.size() == 2, "There hsould be exactly one starting and ending points");
        Preconditions.checkNotNull(myGrid, "Grid can not be assigned as null");

        checkRouteLogic();

        LinkedListStack<Coordinates> myStack = new LinkedListStack<>();

        Coordinates start = sePoints.get(0);
        Coordinates end = sePoints.get(1);

        Coordinates current = start;
        List<Coordinates> visited = new ArrayList<>();

        while(!end.equals(current)){

            visited.add(current);

            checkForNeighbours(myGrid, myStack, current, visited);
            if (myStack.isEmpty()) {
                throw new NoPathAvailableException();
            }

            current = myStack.pop();
            
        }
        checkRouteLogic();

        return visited;
    }

    private void checkForNeighbours(boolean[][]myGrid, LinkedListStack<Coordinates>stack, Coordinates current, List<Coordinates> visited){

        Preconditions.checkNotNull(myGrid, "Grid can not be assigned as null");
        Preconditions.checkNotNull(stack, "Stack can not be assigned as null");
        Preconditions.checkNotNull(current, "Coordinates can not be assigned as null");
        Preconditions.checkNotNull(visited, "Coordintes can not be assigned as null");

        checkRouteLogic();

        boolean done = false;
        int totalDirections = 4;

        Coordinates North = null;
        Coordinates South = null;
        Coordinates East  = null;
        Coordinates West  = null;

        int currX = current.xCoordinates();
        int currY = current.yCoordinates();

        try {
            North = new Coordinates.CoordinateBuilder().xCoordinates(currX-1).yCoordinates(currY).build();
        } catch (InvalidCoordinatesException ice) {

        }

        try {
            South = new Coordinates.CoordinateBuilder().xCoordinates(currX + 1).yCoordinates(currY).build();
        } catch (InvalidCoordinatesException ice) {

        }

        try {
            East  = new Coordinates.CoordinateBuilder().xCoordinates(currX).yCoordinates(currY+1).build();
        } catch (InvalidCoordinatesException ice) {
        }

        try {
            West  = new Coordinates.CoordinateBuilder().xCoordinates(currX).yCoordinates(currY-1).build();
        } catch (InvalidCoordinatesException ice) {

        }

        List<Coordinates> directions = Arrays.asList(North, South, East, West);


        for(var x: directions){

            if(x!= null) {
                if (visitable(x, myGrid) && neverVisited(x, visited )) {

                    stack.push(x);

                }
            }
        }

        checkRouteLogic();

    }

    private boolean visitable(Coordinates cod, boolean[][]myGrid){

        Preconditions.checkNotNull(cod, "Coordinates can not be assigned as null");
        Preconditions.checkNotNull(myGrid, "Grid can not be assigned as null");

        checkRouteLogic();

        boolean visitable = false;

        int x = cod.xCoordinates();
        int y = cod.yCoordinates();

        try{
            ml.checkWithinBound(cod);

            if(myGrid[x][y]){
                visitable = true;
            }
        }catch(CoordinatesOutOfBoundsException cob){
            visitable = false;
        }
        checkRouteLogic();

        return visitable;
    }

    private boolean neverVisited(Coordinates cod, List<Coordinates> visited){

        Preconditions.checkNotNull(cod, "Grid can not be assigned as null");
        Preconditions.checkNotNull(visited, "Coordinates visited can not be assigned as null");

        return (!visited.contains(cod));
    }


    private boolean[][] makeGridPerson(Dimensions dim) {

        Preconditions.checkNotNull(dim, "Dimensions can not be assigned as null");
        checkRouteLogic();


        boolean[][] grid = new boolean[dim.nRows()][dim.nCols()];

        for (Activity a : act) {
            for (Coordinates c : a.getMappingObject()) {

                int x = c.xCoordinates();
                int y = c.yCoordinates();
                grid[x][y] = true;
            }
        }
        checkRouteLogic();


        return grid;
    }

    private boolean[][] makeGridFeed(Dimensions dim) {


        Preconditions.checkNotNull(dim, "Dimensions can not be assigned as null");

        checkRouteLogic();


        boolean[][] grid = new boolean[dim.nRows()][dim.nCols()];

        TreeSet<Person> followersSet = pl.getPerson().getFollowing();
        ArrayList<Person> followers = new ArrayList<>(followersSet);

        for (Person follower : followers) {

            List<Activity> acts = follower.getMyActivityList();

            for (Activity act : acts) {

                List<Coordinates> path = act.getMappingObject();

                for (Coordinates cod : path) {
                    grid[cod.xCoordinates()][cod.yCoordinates()] = true;
                }
            }
        }
        checkRouteLogic();


        return grid;
    }


//    public boolean check() {
//        boolean exists = false;
//
//        List<Person> followers = pl.followers();
//
//        if (!followers.isEmpty()) {
//
//            for (Person p : followers) {
//                if (!p.getMyActivityList().isEmpty()) {
//                    exists = true;
//                }
//            }
//        }
//
//        return exists;
//    }



}
