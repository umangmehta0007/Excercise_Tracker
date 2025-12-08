package comp2450.logic;

import comp2450.Logic.Tracker;
import comp2450.Model.Person.ExerciseTracker;
import comp2450.Model.Person.Person;
import comp2450.Persistence.PersonPersistence;
import comp2450.tests.TestResults;

import java.util.Collection;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestTracker {
    private int successes;
    private int failures;

    public TestTracker() { }

    public TestResults runTests() {
        testAddPerson();
        return new TestResults(successes, failures);
    }

    private void testAddPerson() {

        try {
            PersonPersistence mock = new PersonPersistence() {
                @Override
                public Person savePerson(Person p) {
                    return null;
                }
                @Override
                public Collection<Person> loadList() {
                    return null;
                }
            };

            // ---- Setup tracker ----
            ExerciseTracker et = new ExerciseTracker();
            Tracker tracker = new Tracker(et, mock);

            Person.PersonBuilder pb = new Person.PersonBuilder();
            Person person = pb.name("Umang").weight(80).build();

            int before = tracker.getPeople().size();
            tracker.addPerson(person);
            int after = tracker.getPeople().size();

            if (after != before + 1) {
                fail("FAIL: Person was not added to ExerciseTracker list.");
            }
            else if (!tracker.getPeople().contains(person)) {
                fail("FAIL: Person list does not contain the added person.");
            }

            pass("PASS: addPerson() correctly adds a new Person.");

        } catch (Exception e) {
            fail("FAIL: Unexpected exception in testAddPerson()");
            e.printStackTrace();
        }
    }

    private void pass(String message) {
        successes++;
        green("PASS: " + message);
    }

    private void fail(String message) {
        failures++;
        red("FAIL: " + message);
    }
}
