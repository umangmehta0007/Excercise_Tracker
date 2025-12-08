package comp2450.Logic;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.Exceptions.InvalidActivitySelectionException;
import comp2450.Exceptions.Exceptions.InvalidFollowerSelectionException;
import comp2450.Exceptions.Exceptions.InvalidGearSelectionException;
import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;
import comp2450.Persistence.PersonPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonLogic {

    private Person person;
    private List<Person> earthPeople;

    private final PersonPersistence persistence;


    private void checkPersonLogic() {
        Preconditions.checkNotNull(person, "Person cannot be null");
        Preconditions.checkNotNull(earthPeople, "All people cannot be null");
        for (var x : earthPeople) {
            Preconditions.checkNotNull(x, "people in the list cannot be null");
        }
        Preconditions.checkNotNull(persistence, "Persistence cannot be here null");

    }


    public PersonLogic(PersonPersistence persistence) {
        this.persistence = persistence;
    }


    public void addActivity(Activity act) {
        Preconditions.checkNotNull(act, "Activity cannot be null");
        checkPersonLogic();

        person.addActivity(act);

        persistence.savePerson(this.person);

        checkPersonLogic();
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(person.getMyActivityList());
    }


    public void addGear(Gears gear) {
        Preconditions.checkNotNull(gear, "Gear cannot be null");
        checkPersonLogic();

        person.addGear(gear);

        persistence.savePerson(this.person);

        checkPersonLogic();
    }

    public void removeGear(int index) throws InvalidGearSelectionException {
        Preconditions.checkState(index >= 0, "Index should alwayas be >=0");

        checkPersonLogic();

        List<Gears> gearList = new ArrayList<>(person.getGearsEquipped());

        if (index < 0 || index >= gearList.size()) {
            throw new InvalidGearSelectionException();
        }

        Gears toRemove = gearList.get(index);
        person.removeGear(toRemove);

        checkPersonLogic();
    }

    public List<Gears> gears() {
        return new ArrayList<>(person.getGearsEquipped());
    }

    public Gears getGear(int index) throws InvalidSelectionException {
        Preconditions.checkState(index >= 0, "Index should alwayas be >=0");

        List<Gears> list = this.gears();
        if (index < 0 || index >= list.size()) {
            throw new InvalidSelectionException();
        }

        return list.get(index);
    }

    public void addFollower(Person follower) {

        Preconditions.checkNotNull(follower, "Follower cannot be null");
        checkPersonLogic();

        person.addFollower(follower);

        //TODO after adding a follower we have to persist my person
        persistence.savePerson(this.person);

        checkPersonLogic();
    }

    public void removeFollower(int index) throws InvalidFollowerSelectionException {

        Preconditions.checkState(index >= 0, "Index should alwayas be >=0");

        checkPersonLogic();

        List<Person> list = new ArrayList<>(person.getFollowing());

        if (index < 0 || index >= list.size()) {
            throw new InvalidFollowerSelectionException();
        }

        Person target = list.get(index);
        person.removeFollower(target);

        //TODO after removing an follower we have to persist my person
        persistence.savePerson(this.person);

        checkPersonLogic();
    }

    public List<Person> followers() {
        return new ArrayList<>(person.getFollowing());
    }

    public Person getPerson() {
        return this.person;
    }

    public Activity getMyActivity(Person person, int index) throws InvalidActivitySelectionException {


        Preconditions.checkNotNull(person, "Person cannot be null");
        Preconditions.checkState(index >= 0, "Index should alwayas be >=0");

        checkPersonLogic();


        if (index < 0 || index >= person.getMyActivityList().size()) {
            throw new InvalidActivitySelectionException();
        }

        Activity act = person.getMyActivityList().get(index);

        checkPersonLogic();


        return act;
    }

    public Person getFollower(int index) throws InvalidSelectionException {

        Preconditions.checkState(index >= 0, "Index should alwayas be >=0");

        checkPersonLogic();

        List<Person> fol = new ArrayList<>(person.getFollowing());

        if (index < 0 || index >= fol.size()) {
            throw new InvalidSelectionException();
        }
        checkPersonLogic();

        return fol.get(index);
    }

    public List<Activity> getFollowersActivity(Person person) {

        Preconditions.checkNotNull(person, "Person cannot be null");
        checkPersonLogic();

        return person.getMyActivityList();
    }

    public List<Person> getPeopleOnTracker() {

        return this.earthPeople;
    }

    public List<Person> notInMyFollowingList() {

        checkPersonLogic();

        List<Person> result = new ArrayList<>();

        for (Person person : earthPeople) {
            if (!person.equals(this.person)) {
                result.add(person);
            }
        }
        checkPersonLogic();

        return result;
    }

    public Person getUnfollowedPerson(int index) throws InvalidSelectionException {

        Preconditions.checkState(index >= 0, "Index should alwayas be >=0");

        checkPersonLogic();


        List<Person> followers = this.notInMyFollowingList();

        if (index < 0 || index >= followers.size()) {
            throw new InvalidSelectionException();
        }
        checkPersonLogic();
        return followers.get(index);
    }

    /*
    Setter For dependency Injection
     */

    public void setPerson(Person p) {
        Preconditions.checkNotNull(p, "Person can never be null");
        this.person = p;
    }

    public void setPeople(List<Person> p) {
        Preconditions.checkNotNull(p, "Person's List to be assigned should never be null");
        for (var x : p) {
            Preconditions.checkNotNull(x, "Person in a List to be assigned should never be null");
        }

        this.earthPeople = p;
    }


}