package comp2450.UI;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.Exceptions.InvalidActivitySelectionException;
import comp2450.Exceptions.Exceptions.InvalidFollowerSelectionException;
import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Logic.MapLogic;
import comp2450.Logic.PersonLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Model.Activity.Activity;
import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Model.Person.Person;
import comp2450.UI.Output.MapLegend;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FeedDisplay {

    private final Scanner sc;
    private PersonLogic pl;
    private final MapLogic ml;
    private PersonManagementDisplay pmd;

    public FeedDisplay(Scanner sc,PersonLogic pl, MapLogic ml, PersonManagementDisplay pmd) {
        this.sc = sc;
        this.ml = ml;
        this.pl = pl;
        this.pmd = pmd;
    }

    public void displayFeed() {

        boolean done = false;

        while (!done) {

            printMenu();

            int choice = getInputForSelection();
            sc.nextLine();

            switch (choice) {

                case 1 -> showMyActivities();

                case 2 -> showFollowersActivities();

                case 3 -> addFollower();

                case 4 -> removeFollower();

                case 5 -> managerPerson();

                case 6 -> {
                    done = true;
                    System.out.println("Logging you out!");
                }

                default -> System.out.println("Please select a valid entry 1-6.");
            }
        }
    }

    private void managerPerson() {

        pmd.startTracking();

    }

    private void printMenu() {
        System.out.println("----------- Welcome Aboard Tiger! ----------");
        System.out.println(
                "1. View your activities\n" +
                        "2. View followers activities\n" +
                        "3. Add follower\n" +
                        "4. Remove follower\n" +
                        "5. Profile Management\n" +
                        "6. Logout"
        );
    }


    private int getInputForSelection() {
        int value = -1;
        boolean done = false;

        while (!done) {
            try {
                value = sc.nextInt();
                done = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please enter a number.");
            }
        }
        return value;
    }

    private void showMyActivities() {
        List<Activity> act = pl.getActivities();

        if (act.isEmpty()) {
            System.out.println("You have no activities yet.");
        } else {
            showActivityList(pl.getPerson(), act);
        }
    }

    private void showFollowersActivities() {

        List<Person> followed = pl.followers();

        if (followed.isEmpty()) {
            System.out.println("You do not follow anyone yet.");
        }
    else {
            Person selected = null;

            while (selected == null) {

                for (int i = 0; i < followed.size(); i++) {
                    System.out.println((i + 1) + ". " + followed.get(i).getName());
                }

                System.out.print("Choose person");
                int index = getInputForSelection() - 1;
                sc.nextLine();

                try {
                    selected = pl.getFollower(index);
                } catch (InvalidSelectionException ise) {
                    System.out.println("Invalid selection. Try again.");
                    selected = null;
                }
            }

            List<Activity> acts = pl.getFollowersActivity(selected);

            if (acts.isEmpty()) {
                System.out.println(selected.getName() + " has no activities.");
            } else {
                showActivityList(selected, acts);
            }
        }
    }

    private void showActivityList(Person person, List<Activity> list) {

        Preconditions.checkNotNull(person, "Person can never be null");
        Preconditions.checkNotNull(list, "List of Activity can never be null");

        boolean valid = false;
        Activity selected = null;

        while (!valid) {

            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).getName());
            }

            System.out.print("Choose activity to display on map: ");
            int index = getInputForSelection() - 1;
            sc.nextLine();

            try {
                selected = pl.getMyActivity(person, index);
                valid = true;

            } catch (InvalidActivitySelectionException iase) {
                System.out.println("Invalid selection, please try withing the range.");
            }
        }

        displayMap(selected);
    }

    private void displayMap(Activity act) {

        Preconditions.checkNotNull(act,"Acitvity can never be null" );

        try {
            ml.createGrid(List.of(act));

            System.out.println("Showing route on map:");

            MapLegend.printLegend('.','>','X');
            MapLegend.printGrid(
                    ml.getMap().getGrid(),
                    '.',
                    '>',
                    'X'
            );

        } catch (InvalidCoordinatesException e) {
            System.out.println("This never happens");
        }
    }

    private void removeFollower() {

        List<Person> followers = pl.followers();

        if (followers.isEmpty()) {
            System.out.println("No followers to remove.");
        } else {

            boolean removed = false;

            while (!removed) {
                System.out.println("Select follower to remove (1–" + followers.size() + "):");

                for (int i = 0; i < followers.size(); i++) {
                    System.out.println((i + 1) + ". " + followers.get(i).getName());
                }

                int choice = getInputForSelection();
                sc.nextLine();

                try {
                    int index = choice - 1;
                    pl.removeFollower(index);
                    System.out.println("Follower removed.");
                    removed = true;
                } catch (InvalidFollowerSelectionException e) {
                    System.out.println("Invalid selection: Please choose between 1 and " + followers.size());
                }
            }
        }
    }

    private void addFollower() {

        List<Person> peopleToADD = pl.notInMyFollowingList();

        if (peopleToADD.isEmpty()) {
            System.out.println("No people available to follow.");
        }

        else {
            boolean added = false;

            while (!added) {

                System.out.println("Select someone to follow:");

                for (int i = 0; i < peopleToADD.size(); i++) {
                    System.out.println((i + 1) + ". " + peopleToADD.get(i).getName());
                }

                int index = getInputForSelection() - 1;
                sc.nextLine();

                try {
                    Person toFollow = pl.getUnfollowedPerson(index);
                    pl.addFollower(toFollow);
                    added = true;

                } catch (InvalidSelectionException e) {
                    System.out.println("Please make a selection within the range");
                }
            }
        }
    }

    /*
    Calls the logic to set person for dependency injection principle
     */
    public void setPersonLogic(Person person){

        Preconditions.checkNotNull(person, "Person to be assigned should never be null");

        pl.setPerson(person);
    }


    public void setPersonList(List<Person> people) {

        Preconditions.checkNotNull(people, "Person's List to be assigned should never be null");
        for(var x : people){
            Preconditions.checkNotNull(x, "Person in a List to be assigned should never be null");
        }

        pl.setPeople(people);
    }
}