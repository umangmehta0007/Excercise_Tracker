package comp2450.UI;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Logic.MapLogic;
import comp2450.Logic.PersonLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Model.Activity.Activity;
import comp2450.Exceptions.*;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Gears;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreateActivityDisplay {

    private final Scanner sc;
    final private MapLogic ml;
    final private RouteLogic rl;
    final private PersonLogic pl;

    public CreateActivityDisplay(MapLogic ml, PersonLogic pl) {
        this.ml = ml;
        this.pl = pl;
        this.sc = new Scanner(System.in);
        this.rl = new RouteLogic(pl,this.ml); // could do map
    }

    public Activity createActivity(){
        Activity.ActivityBuilder builder  = new Activity.ActivityBuilder();

        getNameInput(builder);
        getGearInput(builder);
        getCalendarInput(builder);
        getDistanceInput(builder);
        getRouteInput(builder);

        return builder.build();
    }
    private void getNameInput(Activity.ActivityBuilder builder) {

        Preconditions.checkNotNull(builder, "Builder cannot be null");
        String getName= null;
        do {
            System.out.println("Enter name: ");
            getName = sc.nextLine();
            try {
                builder.createName(getName);
            } catch (InvalidNameException e) {
                getName = null;
            }
        } while (getName == null);
    }

    private void getGearInput(Activity.ActivityBuilder builder) {

        Preconditions.checkNotNull(builder, "Builder cannot be null");

        Gears gearUsed =  getGearInput();
        builder.gears(gearUsed);

    }
    private Gears getGearInput() {

        List<Gears> gears = pl.gears();

        Gears gear = null;

        System.out.println("Select gear to use:");

        for (int i = 0; i < gears.size(); i++) {
            System.out.println((i + 1) + ". " + gears.get(i).getName());
        }


        while (gear == null) {
            System.out.println("Enter the gear you want to select ");
            int index = inputSelection() - 1;

            try {
                gear = pl.getGear(index);
            } catch (InvalidSelectionException e) {
                System.out.println("Invalid choice. Try again.");
                gear = null;
            }
        }

        return gear;
    }

    private int inputSelection() {
        int value = -1;
        boolean valid = false;

        while (!valid) {
            try {
                value = sc.nextInt();
                sc.nextLine();
                valid = true;
            } catch (InputMismatchException ime) {
                sc.nextLine();
                System.out.println("Input must be a digit number like 1,2,3..");
            }
        }

        return value;
    }
    private void getDistanceInput(Activity.ActivityBuilder builder) {

        Preconditions.checkNotNull(builder, "Builder cannot be null");
        System.out.println("Please enter the distance covered (in KM) :");
        double coverage = -1.0;

        do {
            System.out.println("Enter distance :");

            try {
                coverage = sc.nextDouble();
                builder.distance(coverage);
                sc.nextLine();

            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid NUMBER (e.g 3.2)");
                coverage = -1.0;
            }
            catch (InvalidDistanceException ide) {
                System.out.println("Please enter a valid NUMBER greater than zero: ");
                coverage = -1.0;
            }

        } while (coverage <= 0);
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
