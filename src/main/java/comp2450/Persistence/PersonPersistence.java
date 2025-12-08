package comp2450.Persistence;

import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;

import java.util.Collection;

public interface PersonPersistence {


    Person savePerson(Person t);

    Collection<Person> loadList() throws NotFoundException;
}

