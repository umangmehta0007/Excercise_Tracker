package comp2450.Model.Person;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTracker {

    private List<Person> myList;

    public void checkExerciseTracker() {
        Preconditions.checkNotNull(myList, "People list cannot be null");

        for (Person p : myList) {
            Preconditions.checkNotNull(p, "Person inside tracker list cannot be null");
        }
    }

    public ExerciseTracker() {

        this.myList = new ArrayList<>();
        checkExerciseTracker();
    }

    public void add(Person person) {
        checkExerciseTracker();
        Preconditions.checkNotNull(person, "Entering a null person is invalid");
        myList.add(person);
        checkExerciseTracker();
    }

    public void setList(List<Person> earthPeople) {

        this.myList = earthPeople;
    }

    public List<Person> getList() {
        return this.myList;
    }

}
