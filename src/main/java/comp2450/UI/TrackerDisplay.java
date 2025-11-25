package comp2450.UI;

import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Logic.MapLogic;
import comp2450.Logic.Tracker;
import comp2450.Model.Person.ExerciseTracker;
import comp2450.Model.Map.Map;
import comp2450.Model.Person.Person;

import java.util.List;
import java.util.Scanner;

public class TrackerDisplay {

    private Tracker tracker;
    private final Scanner sc;
    private MapLogic ml;

    public TrackerDisplay(ExerciseTracker tracker, Map map) {
        sc = new Scanner(System.in);
        this.tracker = new Tracker(tracker);
        this.ml = new MapLogic(map);
    }

    public void startRecording(){

        System.out.println("------------------------------------------------------");
        System.out.println("****** Welcome to COMP 2450 Exercise Tracker ******");
        System.out.println("------------------------------------------------------");

        boolean done = false;
        while(!done) {

            headsUp();
            int selection = getInputForSelection();

            switch (selection) {
                case 1: {

                    createPerson();

                    break;
                }
                case 2: {
                    signInTiger();
                    break;
                }
                case 3: {

                    System.out.println("Exiting.....Thank you For your time.......... :) :) :) :)");
                    done = true;
                }
                default: {
                    System.out.println("Please try again!!");
                }
            }
        }

    }

    public void headsUp(){

        System.out.println("Please choose from the following: ");
        System.out.println("1.Create New Account\n2. Sign in using existing\n3.Exit the Program");
    }

    private int getInputForSelection() {


        int value = -1;
        boolean done = false;

        while (!done) {
            try {
                value = sc.nextInt();
                sc.nextLine();
                done = true;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter a number not alphabets.");
            }
        }

        return value;
    }


    private void createPerson() {
        CreatePersonDisplay cpd = new CreatePersonDisplay();
        Person newPerson = cpd.createPerson();

        tracker.addPerson(newPerson);

        System.out.println("Profile created successfully!");
    }


    private void signInTiger() {

        List<Person> allPeople = tracker.getPeople();

        if (allPeople.isEmpty()) {
            System.out.println("No profiles found. Please create one first.");
        }

        else {
            System.out.println("Select a profile to sign in:");

            for (int i = 0; i < allPeople.size(); i++) {
                System.out.println((i + 1) + ". " + allPeople.get(i).getName());
            }

            Person user = null;

            while (user == null) {
                System.out.println("Enter User you want to Login: ");
                int choice = getInputForSelection() - 1;

                try {
                    user = tracker.getPerson(choice);
                } catch (InvalidSelectionException e) {
                    System.out.println("Invalid selection. Try again.");
                }
            }

            System.out.println("Welcome " + user.getName() + "!");

            FeedDisplay feed = new FeedDisplay(user, tracker.getPeople(), ml);
            feed.displayFeed();
        }
    }

    
}
