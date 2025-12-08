package comp2450.Model.Person;


import comp2450.Model.Activity.Activity;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.InvalidWeightException;
import comp2450.Exceptions.InvalidNameException;

import java.util.*;

/**
 * Person class represents an individual doing {@link Activity}
 * A person has a name and weight along with A list of {@link Activity} and a list of {@link Gears} equipped.
 * Uses a {@link TreeSet} for {@link #gearsEquipped} to ensure gears are always unique.
 * A {@link Gears}, {@link Activity} can be added/removed from the person's list.
 * {@link #weight} can be updated anytime during the Program.
 */
public class Person implements Comparable<Person> {

    final private String name;
    private double weight;
    final private List<Activity> myActivityList;
    final private Set<Gears> gearsEquipped;// Uses TreeSet to ensure unique and sorted gears
    final private Set<Person> following;


    private void checkPerson() {
        Preconditions.checkNotNull(name, "Name of the person can never be null");
        Preconditions.checkNotNull(following, "Following the people can never be null");

        Preconditions.checkState(name.length() >= 1, "Person should be assigned a non empty name");
        Preconditions.checkNotNull(myActivityList, "Activity List should never be null");
        Preconditions.checkNotNull(gearsEquipped, "List of gears should never be null");
        Preconditions.checkState(weight > 0, "Weight of person should always be greater than zero");

        for (Activity currAct : myActivityList) {
            Preconditions.checkNotNull(currAct, "Activity in a person should never be null");
        }

        for (var following : following) {

            Preconditions.checkNotNull(following, "Person in following  should never be null");

        }
        for (Gears currGear : gearsEquipped) {
            Preconditions.checkNotNull(currGear, "Gears for a person should never be null");
        }

    }

    /**
     * Constructor
     * Creates a new Person with the user-defined name and weight.
     *
     * @param name   the person's name
     * @param weight the person's weight, must be greater than 0
     */
    private Person(String name, double weight) {

        this.weight = weight;
        this.name = name;
        this.following = new TreeSet<>();
        this.myActivityList = new ArrayList<>();
        this.gearsEquipped = new TreeSet<>();

        checkPerson(); //Post condition to check if a valid person has been made;
    }


    public static class PersonBuilder {
        private String name;
        private double weight;

        public PersonBuilder() {
        }

        public PersonBuilder name(String name) throws InvalidNameException {
            Preconditions.checkNotNull(name, "Name cannot be null");
            if (name.isBlank()) {
                throw new InvalidNameException();
            }
            this.name = name;
            return this;
        }

        public PersonBuilder weight(double weight) throws InvalidWeightException {

            if (weight <= 0) {
                throw new InvalidWeightException();
            }
            this.weight = weight;
            return this;
        }


        public Person build() {
            Preconditions.checkState(name != null && !name.isBlank(), "Name can never be null/empty");
            Preconditions.checkState(weight > 0, "Weight must be greater than 0");
            return new Person(name, weight);
        }
    }

    /**
     * Updates {@link #myActivityList} and adds {@link Activity}
     *
     * @param activity is the {@link Activity} to be added to the list
     */
    public void addActivity(Activity activity) {
        Preconditions.checkNotNull(activity, "Added activity can never be null");
        checkPerson(); //Precondition check
        myActivityList.add(activity);
        checkPerson();//Postcondition check

    }

    /**
     * Updates {@link #gearsEquipped} and adds {@link Gears}
     *
     * @param gear is the {@link Gears} to be added to the list
     */
    public void addGear(Gears gear) {
        Preconditions.checkNotNull(gear, "Added gear can never be null");


        checkPerson();

        gearsEquipped.add(gear);
        checkPerson();

    }

    /**
     * Remove the {@link Gears} from the list {@link #gearsEquipped}
     *
     * @param gear, removes the {@link Gears} at that index
     */
    public void removeGear(Gears gear) {

        Preconditions.checkNotNull(gear, "Removing gear can never be null");
        checkPerson();

        gearsEquipped.remove(gear);
        checkPerson();

    }

    public void addFollower(Person person) {
        Preconditions.checkNotNull(person, "Added person can never be null");

        following.add(person);
    }

    public void removeFollower(Person person) {
        Preconditions.checkNotNull(person, "removable person can never be null");

        following.remove(person);
    }

    public int compareTo(Person other) {
        Preconditions.checkNotNull(other, "Comaparable person should never be null");
        checkPerson();
        return this.name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public List<Activity> getMyActivityList() {
        return myActivityList;
    }

    public Set<Gears> getGearsEquipped() {
        return gearsEquipped;
    }

    public Set<Person> getFollowing() {
        return this.following;
    }
    @Override
    public boolean equals(Object o) {
        Preconditions.checkNotNull(o, "Person to check can never be null");

        Person other = (Person) o;

        return this.name.equals(other.name);
    }

}
