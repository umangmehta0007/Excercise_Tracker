package comp2450.UI;

import comp2450.Logic.MapLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Exceptions.InvalidRouteSelectionException;
import comp2450.Model.Exceptions.NoActivityLeftToChooseException;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Person;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreateRouteExistingDisplay {
    private final RouteLogic rl;
    private final Scanner sc;


    public CreateRouteExistingDisplay(RouteLogic rl){

        sc = new Scanner(System.in);
        this.rl = rl;
    }

    public List<Coordinates> getRoute() throws NoActivityLeftToChooseException{

        List<Activity>activities = rl.activityList();
        if(activities.isEmpty()){
            throw new NoActivityLeftToChooseException();
       }
        List<Coordinates> route = null;
        do {

            try {
                int index = routeSelectionScreen();
                route= rl.getRoute(index);

            } catch (InvalidRouteSelectionException nel) {
                System.out.println("Please choose a valid Route between 1-" + activities.size());
                route = null;
            }
        }while(route == null);

        return route;

    }

    private int routeSelectionScreen(){

        int result = -1;

        List<Activity>activities = rl.activityList();
        do{
            System.out.println("Please choose the route you want to select from the activities below: ");
            int count = 1;

            for(var act: activities){
                String name = act.getName();
                System.out.println(count+". "+name);
                count++;
            }

            try {
                result = sc.nextInt();
                sc.nextLine();

            }catch(InputMismatchException e) {
                System.out.println("Selected move must be a positive whole number, e.g., 5");
                result = -1;
            }
        }
        while(result == -1);

        return result-1;
    }



    //print all the activities that are currently there in the system, if no activity this would throw an exception
    //no activity found and take it back to the create route display

    //print the list of existing activities inside the person which will be done by Maplogic class or root logic
}
