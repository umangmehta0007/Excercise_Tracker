package comp2450.Logic;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Model.Person.ExerciseTracker;
import comp2450.Model.Person.Person;

import java.util.List;

public class Tracker {

    private final ExerciseTracker tracker;

    public Tracker(ExerciseTracker tracker) {
        this.tracker = tracker;

        checkTracker();
    }


    public void checkTracker(){
        Preconditions.checkNotNull(tracker, "Tracker cannot be here null");
    }

    public void addPerson(Person p) {
        checkTracker();
        tracker.add(p);
        checkTracker();

    }

        public List<Person> getPeople() {

        return tracker.getList();
    }

    public Person getPerson(int index) throws InvalidSelectionException {

        checkTracker();
        List<Person> list = this.getPeople();

        if (index < 0 || index >= list.size()) {
            throw new InvalidSelectionException();
        }

        checkTracker();


        return list.get(index);
    }

}

