package comp2450.Model;

import comp2450.Model.Person.Person;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTracker {

    final private List<Person> myList;

    public ExerciseTracker() {
        this.myList = new ArrayList<>();
    }

    public void add(Person person){

        myList.add(person);
    }
    public void remove(int index){
        myList.remove(index);
    }
}
