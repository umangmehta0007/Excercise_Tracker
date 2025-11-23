package comp2450.UI;

import comp2450.Logic.MapLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Model.Exceptions.NoActivityLeftToChooseException;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Person;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreateRouteDisplay {

    final private Scanner sc;
    final private MapLogic ml;
    final private RouteLogic rl;

    public CreateRouteDisplay(MapLogic ml, RouteLogic rl) {

        sc = new Scanner (System.in);
        this.ml = ml;
        this.rl = rl;


    }

    public List<Coordinates> createRoute(){

        List<Coordinates> route = null;
        System.out.println("Let's create Route For our Activity");

        try {
            do {
                int selection = waySelection();
                switch (selection) {
                    case 1: {
                        route = getRouteManually();
                        break;
                    }
                    case 2: {
                        route = getRouteExisiting();
                        break;
                    }
                    case 3: {
                        route = getRoutePathFinding();
                        break;
                    }default:{
                        System.out.println("Please make a valid selection 1,2,3");
                    }
                }
            } while (route == null);
        }catch(NoActivityLeftToChooseException nl){

            System.out.println("There are no activities, please enter manually or select from your followers");

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
             sc.nextLine();
            }catch(InputMismatchException ime){
                System.out.println("Please select a valid whole number:1,2,3");
            }
        }
        while(result ==-1);
        return result;
    }

    private List<Coordinates> getRouteManually(){

        CreateRouteManuallyDisplay manualRoute = new CreateRouteManuallyDisplay(ml);
        List<Coordinates> myRoute = manualRoute.createRouteManually();
        return myRoute;
    }
    private List<Coordinates> getRouteExisiting() throws NoActivityLeftToChooseException {

        CreateRouteExistingDisplay existingRoute = new CreateRouteExistingDisplay(rl);
        List<Coordinates> myRoute = existingRoute.getRoute();
        return myRoute;
    }
    private List<Coordinates> getRoutePathFinding(){

        CreateRouteFindingDisplay findingRoute = new CreateRouteFindingDisplay();
        List<Coordinates> myRoute = findingRoute.CreateRouteFindingDisplay();
        return myRoute;
    }


}
