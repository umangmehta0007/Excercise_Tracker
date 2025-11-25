package comp2450.UI;

import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Logic.PersonLogic;
import comp2450.Logic.MapLogic;
import comp2450.Exceptions.Exceptions.InvalidGearSelectionException;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.Map;
import comp2450.Model.Map.Obstacle;
import comp2450.Model.Person.Gears;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class PersonManagementDisplay {

    private final Scanner sc;
    private final PersonLogic pl;
    private final MapLogic ml;

    public PersonManagementDisplay(PersonLogic pl,  Map map) {
        this.sc = new Scanner(System.in);
        this.pl = pl;
        this.ml = new MapLogic(map);
    }

    public void startTracking() {
        boolean keepGoing = true;

        while (keepGoing) {
            printMenu();
            int choice = inputSelection();

            switch (choice) {
                case 1:
                    addActivity();
                    break;
                case 2:
                    addGear();
                     break;
                case 3:
                    removeGear();
                    break;
                case 4:
                    addObstacle();
                    break;
                case 5:
                    removeObstacle();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid Selection: please select 1-6.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("=================Person Management=================");
        System.out.println(
                "1. Add Activity\n" +
                        "2. Add Gear\n" +
                        "3. Remove Gear\n" +
                        "4. Add Obstacle\n" +
                        "5. Remove Obstacle\n" +
                        "6. Return to Feed"
        );
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
                value = -1;
            }
        }

        return value;
    }

    private void addActivity() {

        if(pl.gears().size() <= 0) {

            System.out.println("You cannot add activit without having gears: ");

        }
        else {
                Activity act = null;

                CreateActivityDisplay activityDisaply = new CreateActivityDisplay(ml, pl);

                act = activityDisaply.createActivity();

                pl.addActivity(act);
                System.out.println("Activity added.");
        }
    }

//    private void removeActivity() {
//
//        List<Activity> list = pl.getActivities();
//
//        if (list.isEmpty()) {
//            System.out.println("No activities to remove.");
//        } else {
//            boolean removed = false;
//
//            while (!removed) {
//                System.out.println("Select an activity to remove (1–" + list.size() + "):");
//
//                for (int i = 0; i < list.size(); i++) {
//                    System.out.println((i + 1) + ". " + list.get(i).getName());
//                }
//
//                int choice = inputSelection();
//
//                try {
//                    int index = choice - 1;
//                    pl.removeActivity(index);
//                    System.out.println("Activity removed.");
//                    removed = true;
//                } catch (InvalidActivitySelectionException e) {
//                    System.out.println("Invalid selection — please choose between 1 and " + list.size());
//                }
//            }
//        }
//    }

    private void addGear() {
        CreateGearDisplay gd = new CreateGearDisplay();
        Gears gear = gd.createGear();

        pl.addGear(gear);

        System.out.println("Gear added.");
    }

    private void removeGear() {

        List<Gears> gears = pl.gears();

        if (gears.isEmpty()) {
            System.out.println("No gear to remove.");
        } else {

            boolean removed = false;

            while (!removed) {
                System.out.println("Select gear to remove (1–" + gears.size() + "):");

                for (int i = 0; i < gears.size(); i++) {
                    System.out.println((i + 1) + ". " + gears.get(i).getName());
                }

                int choice = inputSelection();

                try {
                    int index = choice - 1;
                    pl.removeGear(index);
                    System.out.println("Gear removed.");
                    removed = true;
                } catch (InvalidGearSelectionException e) {
                    System.out.println("Invalid selection — please choose between 1 and " + gears.size());
                }
            }
        }
    }


    private void addObstacle() {

        CreateObstacleDisplay obsUI = new CreateObstacleDisplay(ml);

        Obstacle obs = obsUI.createObstacle();

        System.out.println("Obstacle added.");
    }

    private void removeObstacle() {

        List<Obstacle> obstacles = ml.getAllObstacle();

        if (obstacles.isEmpty()) {
            System.out.println("No obstacles to remove.");
        } else {

            boolean removed = false;

            while (!removed) {
                System.out.println("Select obstacle to remove (1–" + obstacles.size() + "):");

                for (int i = 0; i < obstacles.size(); i++) {
                    System.out.println((i + 1) + ". " + obstacles.get(i).getName());
                }

                int choice = inputSelection();

                try {
                    int index = choice - 1;
                    ml.removeObstacle(index);
                    System.out.println("Follower removed.");
                    removed = true;
                } catch (InvalidSelectionException ise) {
                    System.out.println("Invalid selection--->please choose between 1 and " + obstacles.size());
                }
            }
        }
    }
}