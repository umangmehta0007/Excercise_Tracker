package comp2450.Model.Person;

import comp2450.Model.Activity.Activity;
import com.google.common.base.Preconditions;
import comp2450.Model.Exceptions.InvalidNameException;

/**
 * Represent Gears equipped by the {@link Person} and also used in {@link Activity}.
 * A gear has a name and uses
 */
public class Gears implements Comparable<Gears> {

//    private static final TreeSet<Gears> allGears = new TreeSet<>();

    final private String name;
    final private String uses;

    private void checkGears() {
        Preconditions.checkNotNull(name, "Gear name cannot be null");
        Preconditions.checkState(name.length() >= 1, "Gear name cannot be empty");
        Preconditions.checkNotNull(uses, "Gear uses cannot be null");
        Preconditions.checkState(uses.length() >= 1, "Gear have atleast a non empty");
    }



    private Gears(String name, String uses){
        this.name = name;
        this.uses = uses;

       // allGears.add(this);

        checkGears();
    }


    public static class GearsBuilder{
        private String name;
        private String uses;

        public GearsBuilder() {}

        public GearsBuilder nameBuilder(String name) throws InvalidNameException {

            Preconditions.checkNotNull(name, "Name should never be null");

            if(name.isBlank()){
                throw new InvalidNameException();
            }
            this.name = name;

            return this;
        }
        public GearsBuilder usageBuilder(String uses) throws InvalidNameException {

            Preconditions.checkNotNull(uses, "Usage should never be null");

            if(uses.isBlank()){
                throw new InvalidNameException();
            }
            this.uses = uses;

            return this;
        }

        public Gears build(){

            return new Gears(name, uses);
        }
    }

    public String getName(){

        return this.name;
    }

    public String getUses(){

        return this.uses;
    }


    public int compareTo(Gears g) {
        Preconditions.checkNotNull(g, "Gear Can never be null");
        return this.name.toLowerCase().compareTo(g.getName().toLowerCase());
    }

}
