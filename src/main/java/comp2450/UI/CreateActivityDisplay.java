package comp2450.UI;

import com.github.lalyos.jfiglet.FigletFont;
import com.google.common.base.Preconditions;
import comp2450.Logic.MapLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Exceptions.InvalidDistanceException;
import comp2450.Model.Exceptions.InvalidNameException;
import comp2450.Model.Exceptions.InvalidRouteException;
import comp2450.Model.Exceptions.RoutesNotAdjacentException;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Map.Map;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreateActivityDisplay {

    private final Scanner sc;
    final private MapLogic ml;
    final private RouteLogic rl;


    public CreateActivityDisplay(Map map, Person person) {
        this.ml = new MapLogic(map);
        this.rl = new RouteLogic(person);
        this.sc = new Scanner(System.in);
    }

    public Activity createActivity(){
        Activity.ActivityBuilder builder  = new Activity.ActivityBuilder();

        getNameInput(builder);
        getGearInput(builder);
        getCalendarInput(builder);
        getRouteInput(builder);
        getDistanceInput(builder);

        return builder.build();
    }
    private void getNameInput(Activity.ActivityBuilder builder) {

        Preconditions.checkNotNull(builder, "Builder cannot be null");
        String name;
        do {
            System.out.print("Enter name: ");
            name = sc.nextLine();
            try {
                builder.name(name);
            } catch (InvalidNameException e) {
                name = null;
            }
        } while (name == null);
    }

    private void getGearInput(Activity.ActivityBuilder builder){

        Preconditions.checkNotNull(builder, "Builder cannot be null");

        CreateGearDisplay gear = new CreateGearDisplay();
        Gears gearUsed =  gear.createGear();
        builder.gears(gearUsed);

    }

    private void getDistanceInput(Activity.ActivityBuilder builder) {

        Preconditions.checkNotNull(builder, "Builder cannot be null");
        System.out.println("Please enter the distance covered (in KM) :");
        double distance = -1.0;

        do {
            System.out.println("Enter distance :");

            try {
                distance = sc.nextDouble();
                sc.nextLine();
                builder.distance(distance);

            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid NUMBER (e.g 3.2)");
                sc.nextLine();
                distance = -1.0;
            }
            catch (InvalidDistanceException ide) {
                System.out.println("Please enter a valid NUMBER greater than zero: ");
                sc.nextLine(); // clear invalid input
                distance = -1.0;
            }

        } while (distance <= 0);
    }

    private void getCalendarInput(Activity.ActivityBuilder builder){
        Preconditions.checkNotNull(builder, "Builder cannot be null");

        CreateCalendarDisplay dateCreator = new CreateCalendarDisplay();
        LocalDateTime date =  dateCreator.createCalendar();
        builder.calendar(date);
    }

    private void getRouteInput(Activity.ActivityBuilder builder) {
        Preconditions.checkNotNull(builder, "Builder cannot be null");

        List<Coordinates> routes = null;
        do{

            try{
                CreateRouteDisplay route = new CreateRouteDisplay(ml, rl);
                routes= route.createRoute();
                builder.route(routes);
            }catch(InvalidRouteException ire){
                System.out.println("There should be at least one Coordinate for the Route to Exist");
                routes = null;
            }catch(RoutesNotAdjacentException rne){

                System.out.println("Each coordinate should be adjacent to each other, it cannot have jumps or move diagonally");
                routes = null;
            }

        }
        while(routes == null);
    }

}
