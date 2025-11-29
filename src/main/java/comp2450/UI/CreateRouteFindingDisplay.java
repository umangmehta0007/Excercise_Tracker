package comp2450.UI;

import comp2450.Logic.MapLogic;
import comp2450.Exceptions.Exceptions.CoordinatesOutOfBoundsException;
import comp2450.Exceptions.Exceptions.ObstacleAlreadyExistsException;
import comp2450.Logic.RouteLogic;
import comp2450.Exceptions.InvalidRouteSelectionException;
import comp2450.Exceptions.NoPathAvailableException;
import comp2450.Exceptions.NoRouteAvailableException;
import comp2450.Model.Map.Coordinates;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreateRouteFindingDisplay {

    private final Scanner sc;
    private final MapLogic ml;
    private final RouteLogic rl;

    public CreateRouteFindingDisplay(Scanner sc, MapLogic ml, RouteLogic rl) {
        this.rl = rl;
        this.sc = sc;
        this.ml = ml;
    }

    public List<Coordinates> getPathFindingRoute() throws NoPathAvailableException, NoRouteAvailableException {
        List<Coordinates> route = null;
        boolean done = false;
        List<Coordinates> sePoints = startingEndingPoints();

        do {
            int whichWay = pathSelectionType();
            try {
                route = rl.pathFinding(whichWay, sePoints);
            } catch (InvalidRouteSelectionException irs) {

            }
        }while(route == null);

        return route;
    }

    private List<Coordinates> startingEndingPoints() {
        List<Coordinates> list = new ArrayList<>();

        System.out.println("Enter starting coordinate:");
        Coordinates start = getCoordinate();

        System.out.println("Enter ending coordinate:");
        Coordinates end = getCoordinate();

        list.add(start);
        list.add(end);

        return list;
    }


    private Coordinates getCoordinate() {
        Coordinates cod = null;

        do {
            CreateCoordinateDisplay builder = new CreateCoordinateDisplay(sc);
            cod = builder.createCoordinates();

            try {
                ml.checkValidRouteCood(cod);
            } catch (CoordinatesOutOfBoundsException e) {
                System.out.println("Coordinate is outside the map");
                cod = null;
            } catch (ObstacleAlreadyExistsException e) {
                System.out.println("Invalid Coordinates:There is an obstacle on this coordinates.");
                cod = null;
            }

        } while (cod == null);

        return cod;
    }

    private int pathSelectionType() {
        int choice = -1;

        do {
            System.out.println("How so you want to find the Route?");
            System.out.println("1. Route taken by self in the past");
            System.out.println("2. Routes from the people you follow");

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException ime) {
                sc.nextLine();
                choice = -1;
            }

        } while (choice == -1);

        return choice;
    }
}


//creating a path finding route system
// in this either the route is found using
// starting and ending point from my own existing route
//starting and ending point from my feed's existing route

// 1) ask user where do you want your route to choose from your own existing or your followers
//      1.1 for own existing ask user to choose starting and ending point
//      1.2 give that points to logic layer that runs the algorithm in itself and returns the best Route
//      1.3 if no route available it throws NoRouteFoundException and returns back to the wall of asking how you want to select.


// 2) If user chooses to select from feed that has let's say 10 people.
//2.1 You have select list of activities of one person
//2.11 Then you have to select list of coordinates that each activity holds of one person
//     and check if there is a route available in that person's activity then move to next activity
//     once activities have ended check for now second person.
//2.2 now check our list of all route available using those starting and ending points and return one who's size is smallest.



// Let's say I've given starting and ending point:

//1) I've to make sure they are on the map using map logic