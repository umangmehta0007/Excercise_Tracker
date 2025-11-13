package comp2450;

import comp2450.Model.ExerciseTracker;
import comp2450.Model.Person.Person;

import java.util.Scanner;

public class TestingMain {


    public static void main(String[]args){
        ExerciseTracker Tracker = new ExerciseTracker();

        runTracker(Tracker);
    }

    public static void runTracker(ExerciseTracker Tracker){

        Scanner sc = new Scanner(System.in);
        System.out.println("Chose from the following");

        System.out.println("1) Create new profile");
        System.out.println("2) Open existing profile");
        System.out.println("0) if you want to exit");


        int chosen = sc.nextInt();
        sc.nextLine();


        if(chosen == 1) {
            createPerson(sc);
        }else{
            //openFile(Person person);
        }


    }

    private static Person createPerson(Scanner sc) {

        System.out.println("Please enter your Name: ");
        String name = sc.nextLine();

        System.out.println("Please enter your weight");
        double myWeight = sc.nextDouble();

        System.out.println();

        return new Person(name, myWeight);
    }

}
