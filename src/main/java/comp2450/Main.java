package comp2450;


/*
 * COMP 2450 SECTION: [A01]
 * INSTRUCTOR: [Franklin Bristow]
 * STUDENT NUMBER: [7885176]
 * ASSIGNMENT: [Assignment 1]
 * [Main Class - Activity Tracker]
 *
 * Purpose:
 * This program defines the "Main" class, which serves as the entry point for the Activity Tracker application.
 *
 * It allows the user to:
 * - Create a person profile with name and weight
 * - Create a map and define its dimensions
 * - Add gears, obstacles, and activities
 * - View the map, activities, and gears
 * - Remove gears, obstacles, or activities
 * - Set or update the person's weight
 *
 * The program continuously prompts the user with a menu until the "Exit" option is chosen.
 * Used Grammarly to refine comment phrasing, though all implementation is original.
 *
 */

import comp2450.Model.Activity.Activity;
import comp2450.Model.Activity.Route;
import comp2450.Model.Person.Gears;
import comp2450.Model.Map.*;
import comp2450.Model.Map.Map;
import comp2450.Model.Person.Person;
import comp2450.Output.*;
import comp2450.Model.Map.Coordinates;

import java.util.*;
import java.util.List;

/**
 * This is the main class where everything happens.
 *
 * It shows the menu to the user and asks what they want to do.
 * Initially as you begin you create a Person
 * Secondly you have to make a Map, add Gears, Obstacles, and Activities.
 * You can also see the Map, see all Activities, and see what Gears you have.
 * The main loop keeps running until the user chooses to exit. (# Thanks REPL)
 * {@link Scanner} is use to take input from user and uses Output class to print anything.
 * Output classes like ShowMap, ShowGears, ShowActivity handle the output.
 */

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        runTracker();

    }

    /**
     * Starts and runs the Activity Tracker program do not do it in main #Thanks_Lauren
     *
     * This method handles the main user interaction:
     * - Add a new gear
     * - Create a new map
     * - Add obstacles to the map
     * - Create and record activities
     * - View the map, gears, and activities
     * - Remove gears, activities, or obstacles
     * - Set their current weight
     * The user is repeatedly prompted with a menu until they choose to exit. (REPL)
     */
    private static void runTracker(){

        Scanner sc = new Scanner(System.in);
        Map map = null;
        Person person = createPerson(sc);

        boolean done = false;

        while(!done){

            String output =
                    """
                   
                   *To begin with you would just need to create a MAP first(option 2): 
                   *Then gears are added(option 1), followed by adding obstacles(option 3) if needed then activities(option 4). 
                   
                   Please choose from the following List: 
                   0. Exit
                   1. ADD Gear
                   2. ADD MAP
                   3. ADD OBSTACLE
                   4. ADD ACTIVITY
                   5. SHOW MAP
                   6. SHOW GEARS
                   7. SHOW ACTIVITY
                   8. SHOW ACTIVITIES
                   9. REMOVE GEAR
                   10. REMOVE ACTIVITY
                   11. REMOVE OBSTACLE
                   12. REMOVE MAP
                   13. SET WEIGHT
                    """;

            System.out.println(output);

            int moveChose = sc.nextInt();
            sc.nextLine();

            switch (moveChose){
                case (1):{

                    createGear(sc, person);
                    break;
                }
                case(2): {
                    map = createMyMap(sc);
                    break;
                }
                case(3): {

                    createObstacle(sc,map);
                    break;
                }
                case(4): {
                    createActivity(sc,person, map);
                    break;
                }
                case(5): {
                    showMap(sc, map, person);
                    break;
                }
                case(6): {
                    showGears(sc, person);
                    break;
                }
                case(7): {
                    showActivity(sc, map, person);
                    break;
                }
                case (8) : {
                    showActivites(person);
                    break;
                }
                case(9):{
                    removeGear(sc,person);
                    break;
                }
                case(10):{
                    removeActivity(sc, person, map);
                    break;
                }
                case(11): {
                    removeObstacle(sc, map);
                    break;
                }
                case(12): {
                    removeMap();break;
                }
                case(13): {

                    setWeight(sc,person);
                    break;
                }
                case(0):{

                    System.out.println("Thank you for your time!");
                    done = true;
                    break;
                }


            }

        }


    }
    private static void setWeight(Scanner sc,Person person) {

        System.out.println("Please select the new weight!");

        double weight = sc.nextDouble();
        person.setWeight(weight);

    }

    private static void removeObstacle(Scanner sc, Map map) {

        System.out.println("Please choose the Obstacle you want to remove: ");

        showObstacles(map);

        int index = sc.nextInt();

        map.removeObstacle(index-1);

    }
    private static void removeActivity(Scanner sc, Person person, Map map) {

        System.out.println("Please choose the Activity you want to remove: ");

        showActivites(person);

        int index = sc.nextInt();


        Activity removedActivity = person.removeActivity(index-1);
        map.removeRoute(removedActivity.getRoute());

    }
    private static void removeGear(Scanner sc, Person person) {

        System.out.println("Please choose the gear you want to remove: ");

        showGears(sc,person);

        int index = sc.nextInt();

        person.removeGear(index-1);
    }
    private static void removeMap() {

        Map map = null;
    }

    /**
     * Lets the user pick one activity from their list and shows it on the map along with {@link Obstacle}.
     * Steps:
     * 1. Prints all activities and asks the user which one to see.
     * 2. Gets the chosen activity from the person's list.
     * 3. Makes a new temporary map based on the original map.
     * 4. Adds all obstacles and the chosen activity's route to the new map.
     */
    private static void showActivity(Scanner sc, Map map, Person person) {

        System.out.println("Select from the List of the Activity you want to See: ");
        showActivites(person);
        int index = sc.nextInt();

        Activity currChosen = person.getMyActivityList().get(index-1);
        List<Route> route = List.of(currChosen.getRoute());

        Map currMap = new Map(map.getName(),map.getDimensions()); // everytime I call this a new instance is created.

        //currMap.addObstacleToGrid(map.getObsInMap());
        //currMap.addRouteToGrid(route);
        currMap.createGrid(map.getObsInMap(), route);

        ShowActivity showAct = new ShowActivity(currMap.getGrid());
        showAct.printActivity();

    }
    private static void showActivites(Person person) {

        PrintAllActivities showAct = new PrintAllActivities(person.getMyActivityList());

        showAct.printActivities();

    }
    private static void showObstacles(Map map) {

        ShowObstacles showObs = new ShowObstacles(map.getObsInMap());

        showObs.printObstacle();

    }

    /**
     * Displays the map with all activities and obstacles between two dates. NOTE: Date you enter are Exclusive
     * Steps:
     * 1. Welcomes the user and asks for start and end dates.
     * 2. Creates a new temporary map based on the original map.
     * 3. Adds all obstacles and all activities to the temporary map.
     * 4. Uses ShowMap to print the map along with total distance and calories
     *    for activities within the selected date range.
     */
    private static void showMap(Scanner sc, Map map, Person person) {

        System.out.println("Welcome to the MAP Display! \n" +
                "Enter the date you want to begin with");
        Calendar startDate = createCalendar(sc);
        Calendar endDate = createCalendar(sc);

        Map currMap = new Map(map.getName(),map.getDimensions()); // everytime I call this a new instance is created.
        //currMap.addObstacleToGrid(map.getObsInMap());
        //currMap.addRouteToGrid(map.getRouteInMap());

        currMap.createGrid(map.getObsInMap(), map.getRouteInMap());


        ShowMap showMap = new ShowMap(currMap.getGrid(),person,startDate,endDate);
        showMap.printMap();

    }
    private static void showGears(Scanner sc,Person person){

        ShowGears sg= new ShowGears(person.getGearsEquipped());
        sg.printGears();
    }

    private static Map createMyMap(Scanner sc) {

        System.out.println("Please enter the Name you want to give your Map: ");
        String name = sc.nextLine();

        System.out.println("Please enter the Number of Rows you want: ");
        int nRows = sc.nextInt();

        System.out.println("Please enter the Number of Columns you want: ");
        int nCols = sc.nextInt();

        Dimensions dim = new Dimensions(nRows, nCols);

        return new Map(name, dim );
    }
    private static void createObstacle(Scanner sc, Map map) {

        System.out.print("Please enter the Name of Obstacle you want to add: ");
        String name = sc.nextLine();


        Obstacle myObstacle = new Obstacle(name);

        boolean done = false;

        Coordinates currentCoordinate;

        Coordinates intialCoordinates = addInitialCoordinates(sc);
        myObstacle.addCoordinates(intialCoordinates);
        updateCoordiantes(sc, myObstacle);

        map.addObstacle(myObstacle);
    }
    private static Gears createGear(Scanner sc, Person person) {

        System.out.println("Please enter the Name of the Gear: ");
        String name = sc.nextLine();

        System.out.println("Please enter the use of this gear ");
        String uses = sc.nextLine();

        Gears gear = new Gears(name, uses);

        person.addGear(gear);

        return gear;
    }
    private static Person createPerson(Scanner sc) {

        System.out.println("Please enter your Name: ");
        String name = sc.nextLine();

        System.out.println("Please enter your weight");
        double myWeight = sc.nextDouble();

        System.out.println();

        return new Person(name, myWeight);
    }
    public static void  createActivity(Scanner sc, Person person, Map map) {

        System.out.println("Please enter the name of the Activity you want to create:  ");
        String name = sc.nextLine();

        Calendar date = createCalendar(sc);
        double distance = getDistance(sc);
        double currentWeight = person.getWeight();

        Gears gear = addGearsToActivity(sc,person);

        System.out.println("Let's select the route visited: ");
        Route route = createRoute(sc);

        Activity currActivity = new Activity(name, route, date, distance, currentWeight, gear);
        map.addRoutes(route);
        person.addActivity(currActivity);
    }

    /**
     * Lets the user pick a gear from the person's equipped gears to use in an activity.
     * Steps:
     * 1. Gets all gears currently equipped by the person.
     * 2. Shows the list of gears with their uses.
     * 3. Asks the user to choose a gear by entering its number.
     * 4. Returns the selected gear to be used in the activity.
     *
     * @param sc Scanner to take input
     * @param person this is the instance that holds {@link Gears} and {@link Activity}
     */
    private static Gears addGearsToActivity(Scanner sc, Person person) {

        TreeSet<Gears> mySet = person.getGearsEquipped();
        ShowGears showGear = new ShowGears(mySet);
        ArrayList<Gears> myGears = new ArrayList<>(mySet);

        System.out.println("Chose Gear you want to add to your person");
        showGear.printGears();

        int chosenGear = sc.nextInt();
        Gears myGear = (myGears.get(chosenGear-1));

        return myGear;

    }
    private static double getDistance(Scanner sc) {

        System.out.println("Please enter the Total distance covered by activity in KM:  ");

        double distance = sc.nextDouble();

        return distance;

    }

    /**
     * Lets the user create a Calendar object by entering the date and time.
     *
     * Steps:
     * 1. Asks the user to enter year, month, day, hour, minute, and second (space-separated).
     * 2. Adjusts month by subtracting 1, since Calendar months are 0-based.
     * 3. Sets the values into a Calendar instance.
     * 4. Returns the Calendar object with the specified date and time.
     */
    private static Calendar createCalendar(Scanner sc) {

        Calendar cal = Calendar.getInstance();

        System.out.println("Enter year, month, day, hour (0-23), minute (0-59), and second (0-59) separated by spaces:");
        System.out.println("Note: Use 24-hour format for hours. Example: 2025 10 12 14 30 00");
        int year = sc.nextInt();
        int month = sc.nextInt()-1;
        int day = sc.nextInt();

        int hour = sc.nextInt();
        int minute = sc.nextInt();
        int second = sc.nextInt();

        System.out.println();

        cal.set(year, month, day, hour, minute, second);


        return cal;

    }
    private static Route createRoute(Scanner sc) {

        Route myActivityRoute = new Route();

        boolean done = false;

        Coordinates currentCoordinate;

        Coordinates intialCoordinates = addInitialCoordinates(sc);

        myActivityRoute.addCoordinates(intialCoordinates);

        updateCoordiantes(sc, myActivityRoute);

        return myActivityRoute;
    }
    private static Coordinates addInitialCoordinates(Scanner sc){

        Coordinates startingPoint;

        System.out.println("Please enter a starting coordiate");

        System.out.println(" Enter x coordinates");

        int x = sc.nextInt();

        System.out.println(" Enter y coordinates");

        int y = sc.nextInt();

        startingPoint = new Coordinates(x, y);

        return startingPoint;

    }

    /**
     * This asks the user to enter directions and steps to update the coordinates
     * of a given {@link IMapping} object until the user chooses to quit.
     * Valid directions are:
     * 'N' - move North (up)
     * 'S' - move South (down)
     * 'E' - move East (right)
     * 'W' - move West (left)
     * 'Q' - quit entering coordinates
     *
     * This method calls {@link #addCoordinates)} internally to add each step.
     *
     * @param sc {@link Scanner} read user input
     * @param mappableObject the {@link IMapping} object whose coordinates are being updated
     */
    private static void updateCoordiantes(Scanner sc, IMapping mappableObject) {

        boolean done = false;

        while(!done) {

            System.out.println("Enter direction to move your point (N=North, S=South, E=East, W=West) or Q to finish:");
            System.out.println("Example: N moves up, S moves down, E moves right, W moves left.");

            char direction = sc.next().charAt(0);

            char exit = 'Q';
            if(direction!= exit) {

                System.out.println("Please enter how many steps you want to move");
                int steps = sc.nextInt();

                switch (direction) {

                    case 'N':
                    case 'S':
                    case 'W':
                    case 'E':
                        addCoordinates(direction, steps, mappableObject);

                        break;

                    default:
                        System.out.println("Invalid input! Please enter N, S, E, W for directions or Q to quit.");
                        break;
                }
            }
            else{
                System.out.println("Thank you ! ADDED SUCCESSFULLY");
                done = true;
            }
        }


    }

    /**
     * Adds new coordinates to the given {@link IMapping}.
     * It starts from the last coordinate already present in the object and moves step by step.
     *
     * @param direction the direction to move ('N' for North, 'S' for South, 'E' for East, 'W' for West)
     * @param steps the number of steps to move in the given direction
     * @param mappableObject the object implementing {@link IMapping} which will be {@link Obstacle} or {@link Route}
     * where the new coordinates will be added
     */
    private static void addCoordinates(char direction, int steps, IMapping mappableObject) {

        for (int i = 0; i < steps; i++) {

            List<Coordinates> currList = mappableObject.getCoordinates();

            Coordinates lastCod = currList.get(currList.size() - 1);

            int xCod = lastCod.xCoordinates();
            int yCod = lastCod.yCoordinates();

            switch (direction) {

                case 'N':
                    xCod = lastCod.xCoordinates() - 1;
                    break;
                case 'S':
                    xCod = lastCod.xCoordinates() + 1;
                    break;
                case 'E':
                    yCod = lastCod.yCoordinates() + 1;
                    break;
                case 'W':
                    yCod = lastCod.yCoordinates() - 1;
                    break;
            }

            Coordinates newCod = new Coordinates(xCod, yCod);
            mappableObject.addCoordinates(newCod);

            }
        }
    }
