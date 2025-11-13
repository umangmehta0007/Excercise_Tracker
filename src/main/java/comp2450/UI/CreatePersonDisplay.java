package comp2450.UI;

import comp2450.Model.Exceptions.InvalidWeightException;
import comp2450.Model.Exceptions.InvalidNameException;

import comp2450.Model.Person.Person;
import com.google.common.base.Preconditions;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import com.github.lalyos.jfiglet.FigletFont;

public class CreatePersonDisplay {

    private final Scanner sc;

    public CreatePersonDisplay() {
        this.sc = new Scanner(System.in);
    }

    public Person createPerson() {
        Person.PersonBuilder builder = new Person.PersonBuilder();
        try {
            System.out.println(FigletFont.convertOneLine("Create Profile!"));
        } catch (IOException ignored) { }

        System.out.println("Let's create a person");

        getNameInput(builder);
        getWeightInput(builder);

        return builder.build();
    }

    private void getNameInput(Person.PersonBuilder builder) {
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

    private void getWeightInput(Person.PersonBuilder builder) {
        Preconditions.checkNotNull(builder, "Builder cannot be null");

        double weight = -1;
        do {
            System.out.print("Enter weight: ");

            try {
                weight = sc.nextDouble();
                sc.nextLine();
                builder.weight(weight);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number for weight");
                sc.nextLine();
                weight = -1;
            } catch (InvalidWeightException e) {
                System.out.println("Weight must be greater than 0.");
                weight = -1;
            }
        } while (weight <= 0);
    }
}