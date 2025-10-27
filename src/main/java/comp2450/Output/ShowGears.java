package comp2450.Output;

import comp2450.Model.Person.Gears;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Displays all {@link Gears} in a {@link TreeSet}.
 * Prints each gear's name and its  uses.
 */
public class ShowGears {

    final private TreeSet<Gears> setOfGears;

    public ShowGears(final TreeSet<Gears> setOfGears){

        this.setOfGears = setOfGears;
    }

    public void printGears(){

        final ArrayList<Gears> myGears = new ArrayList<>(setOfGears); //set of Gears created in Person class

        for(int i = 0; i<setOfGears.size(); i++){ //loops through all list of gears and print them

            String gearName = myGears.get(i).getName();
            String uses = myGears.get(i).getUses();
            int currCount = i+1;

            System.out.println(currCount+ ".) " + gearName+ "\n is used for "+ uses);

        }
    }


}
