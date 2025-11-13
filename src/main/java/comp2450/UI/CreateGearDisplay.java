package comp2450.UI;

import com.github.lalyos.jfiglet.FigletFont;
import comp2450.Model.Exceptions.InvalidNameException;
import comp2450.Model.Person.Gears;

import java.io.IOException;
import java.util.Scanner;

public class CreateGearDisplay {

    final private Scanner sc;


    public CreateGearDisplay(){

        sc = new Scanner (System.in);
    }

    public Gears createGear(){


        Gears.GearsBuilder builder = new Gears.GearsBuilder();

        try {
            System.out.println(FigletFont.convertOneLine("Gears Creator!"));
        } catch (IOException ignored) { }

        System.out.println("Lets go! Create a Gear");

        nameBuilder(builder);
        usesBuilder(builder);

        return builder.build();
    }


    private void nameBuilder(Gears.GearsBuilder builder) {

        String name = null;

        do{
            System.out.println("Please enter the name of the Gear: ");

            try{
                name = sc.nextLine();
                builder.nameBuilder(name);

            } catch (InvalidNameException e) {
                System.out.println("Name should never be empty");
                name = null;
            }
        }
        while(name == null);
    }
    private void usesBuilder(Gears.GearsBuilder builder) {

        String uses = null;

        do{
            System.out.println("Please enter the uses of the Gear: ");

            try{
                uses = sc.nextLine();
                builder.usageBuilder(uses);

            } catch (InvalidNameException e) {
                System.out.println("Usages should never be empty");
                uses = null;
            }
        }
        while(uses == null);
    }


}


