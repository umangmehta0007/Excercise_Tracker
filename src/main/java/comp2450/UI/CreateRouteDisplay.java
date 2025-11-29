package comp2450.UI;

import comp2450.Logic.MapLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Exceptions.InvalidRouteSelectionException;
import comp2450.Exceptions.NoActivityLeftToChooseException;
import comp2450.Exceptions.NoPathAvailableException;
import comp2450.Exceptions.NoRouteAvailableException;
import comp2450.Model.Map.Coordinates;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreateRouteDisplay {

    final private Scanner sc;
    final private MapLogic ml;
    final private RouteLogic rl;

    public CreateRouteDisplay(Scanner sc, MapLogic ml, RouteLogic rl) {

        this.sc = sc;
        this.ml = ml;
        this.rl = rl;
    }

    public List<Coordinates> createRoute(){

        List<Coordinates> route = null;
        System.out.println("Let's create Route For our Activity");

        int selection;
        boolean done = false;

        while(!done) {

            try {

                do {
                    selection = waySelection();
                    sc.nextLine();

                    switch (selection) {
                        case 1: {
                            route = getRouteManually();
                            done = true;
                            break;
                        }
                        case 2: {
                            route = getRouteExisiting();
                            done = true;

                            break;

                        }
                        case 3: {

                            route = getRoutePathFinding();
                            done = true;
                            break;
                        }
//                        case 4: {
//                            System.out.println("Exiting...");
//                            done = true;
//                        }
                        default:
                            throw new InvalidRouteSelectionException();
                    }
                }

                while (route == null);

            } catch (NoActivityLeftToChooseException nl) {
                System.out.println("There are no activities, please enter manually or select from your followers");
                route = null;
            } catch (InvalidRouteSelectionException ise) {
                System.out.println(" Please make a valid selection 1,2 or 3");
                route = null;
            } catch (NoPathAvailableException npa) {
                System.out.println("There are no Path available from chosen Starting and ending coordiantes");
                route = null;
            }catch(NoRouteAvailableException nre){
                System.out.println("There are no Routes available to choose from");
                route = null;

            }
        }
        return route;
    }


    private int waySelection(){

        int result = -1;

        do {
            try {
                System.out.println("How do you want to select a Route?");
                System.out.println("1.Choose Route Manually \n 2.Choose Route from Existing Routes \n 3. Find Path Using starting and ending point.");

             result =sc.nextInt();
            }catch(InputMismatchException ime){
                sc.nextLine();
                System.out.println("Please select a valid whole number:1,2,3");
            }
        }
        while(result ==-1);
        return result;
    }

    private List<Coordinates> getRouteManually(){

        CreateRouteManuallyDisplay manualRoute = new CreateRouteManuallyDisplay(sc, ml);
        List<Coordinates> myRoute = manualRoute.createRouteManually();
        return myRoute;
    }
    private List<Coordinates> getRouteExisiting() throws NoActivityLeftToChooseException {

        CreateRouteExistingDisplay existingRoute = new CreateRouteExistingDisplay(sc,rl);
        List<Coordinates> myRoute = existingRoute.getRoute();
        return myRoute;
    }
    private List<Coordinates> getRoutePathFinding() throws NoPathAvailableException, NoRouteAvailableException{

        CreateRouteFindingDisplay findingRoute = new CreateRouteFindingDisplay(sc, ml, rl);
        List<Coordinates> myRoute = findingRoute.getPathFindingRoute();
        return myRoute;
    }


}
