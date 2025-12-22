package comp2450.logic;

import comp2450.Logic.PersonLogic;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;
import comp2450.Persistence.NotFoundException;
import comp2450.Persistence.PersonPersistence;
import comp2450.Persistence.json.PersonPersistenceJson;
import comp2450.tests.TestResults;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestPersonLogic {

    private int successes;
    private int failures;

    public TestPersonLogic() { }

    public TestResults runTests() {

        testAddActivity();
        testAddGear();
        testRemoveGear();
        testAddFollower();
        testRemoveFollower();

        return new TestResults(successes, failures);
    }



    private void testAddActivity() {

        try {
            Path temp = Files.createTempFile("Activity", "Json");
            PersonPersistenceJson persistence = new PersonPersistenceJson(temp);

            PersonLogic pl = new PersonLogic(persistence);

            Person.PersonBuilder pb = new Person.PersonBuilder();
            Gears.GearsBuilder gb = new Gears.GearsBuilder();
            Coordinates.CoordinateBuilder cb = new Coordinates.CoordinateBuilder();

            Person person = pb.name("Umang").weight(85).build();

            pl.setPerson(person);
            pl.setPeople(List.of(person));

            Gears gear = gb.nameBuilder("Shoes").usageBuilder("Run").build();
            Coordinates c = cb.xCoordinates(0).yCoordinates(0).build();

            List<Coordinates> route = List.of(c);

            Activity act = new Activity.ActivityBuilder()
                    .createName("Running")
                    .gears(gear)
                    .calendar(LocalDateTime.now())
                    .route(route)
                    .distance(10)
                    .build();

            int before = person.getMyActivityList().size();

            pl.addActivity(act);

            int after = person.getMyActivityList().size();

            if (after != before + 1) {
                fail("Activity was not added");
            } else if (!person.getMyActivityList().contains(act)) {
                fail("No newly added activity was added");
            }

            pass("Success in adding activity.");

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
            e.printStackTrace();
        }
    }
  /*
  I did use the other one, that was demonstrated in class, but it was throwing some blunder Exceptions so just copy pasted what professor did
  Using the mock persistence.
   */
//    private void testAddActivity() {
//
//        try {
//
//            PersonPersistence mockPersistence = new PersonPersistence(){
//                @Override
//                public Person savePerson(Person p) {
//                    return null;
//                }
//
//                @Override
//                public Collection<Person> loadList() throws NotFoundException {
//                    return null;
//                }
//
//            };
//
//            PersonLogic pl = new PersonLogic(mockPersistence);
//
//            Person.PersonBuilder pb = new Person.PersonBuilder();
//            Gears.GearsBuilder gb  = new Gears.GearsBuilder();
//            Coordinates.CoordinateBuilder cb = new Coordinates.CoordinateBuilder();
//
//            Person person = pb.name("Umang").weight(85).build();
//
//            pl.setPerson(person);
//            pl.setPeople(List.of(person));
//
//            Gears gear = gb.nameBuilder("Shoes").usageBuilder("Run").build();
//
//            Coordinates c = cb.xCoordinates(0).yCoordinates(0).build();
//
//            List<Coordinates> route = List.of(c);
//
//            Activity act = new Activity.ActivityBuilder()
//                    .createName("Running")
//                    .gears(gear)
//                    .calendar(LocalDateTime.now())
//                    .route(route)
//                    .distance(10)
//                    .build();
//
//            int before = person.getMyActivityList().size();
//
//            pl.addActivity(act);
//
//            int after = person.getMyActivityList().size();
//
//            if (after != before + 1) {
//                fail("FAIL: Activity was not added");
//            } else if (!person.getMyActivityList().contains(act)) {
//                fail("FAIL: Activity missing after add");
//            } else {
//                pass("PASS: Activity added successfully");
//            }
//
//        } catch (Exception e) {
//            fail("FAIL: Unexpected exception thrown:");
//            e.printStackTrace();
//        }
//    }
    private void testAddGear() {

        try {
            PersonPersistence mockPersistence = new PersonPersistence() {
                @Override
                public Person savePerson(Person p) { return null; }

                @Override
                public Collection<Person> loadList() { return null; }
            };

            PersonLogic pl = new PersonLogic(mockPersistence);

            Person.PersonBuilder pb = new Person.PersonBuilder();
            Gears.GearsBuilder gb  = new Gears.GearsBuilder();

            Person person = pb.name("Umang").weight(85).build();
            pl.setPerson(person);
            pl.setPeople(List.of(person));

            Gears gear = gb.nameBuilder("Shoes").usageBuilder("Run").build();

            int before = person.getGearsEquipped().size();

            pl.addGear(gear);

            int after = person.getGearsEquipped().size();

            if (after != before + 1) {
                fail("FAIL: Gear was not added");
            } else if (!person.getGearsEquipped().contains(gear)) {
                fail("FAIL: Gear missing after add");
            } else {
                pass("PASS: Gear added successfully");
            }

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown:");
            e.printStackTrace();
        }
    }

    private void testRemoveGear() {

        try {
            Path temp = Files.createTempFile("GearRemove", "Json");
            PersonPersistenceJson persistence = new PersonPersistenceJson(temp);

            PersonLogic pl = new PersonLogic(persistence);

            Person.PersonBuilder pb = new Person.PersonBuilder();
            Gears.GearsBuilder gb = new Gears.GearsBuilder();

            Person person = pb.name("Umang").weight(85).build();

            pl.setPerson(person);
            pl.setPeople(List.of(person));

            Gears g1 = gb.nameBuilder("Shoes").usageBuilder("Run").build();
            person.addGear(g1);

            int before = person.getGearsEquipped().size();

            pl.removeGear(0);

            int after = person.getGearsEquipped().size();

            if (after != before - 1) {
                fail("Gear was not removed");
            } else if (person.getGearsEquipped().contains(g1)) {
                fail("Removed gear is still in the list");
            }

            pass("Success in removing gear.");

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
            e.printStackTrace();
        }
    }

    private void testAddFollower() {

        try {
            PersonPersistence mockPersistence = new PersonPersistence() {
                @Override
                public Person savePerson(Person p) { return null; }

                @Override
                public Collection<Person> loadList() { return null; }
            };

            PersonLogic pl = new PersonLogic(mockPersistence);

            Person.PersonBuilder pb = new Person.PersonBuilder();

            Person person = pb.name("Umang").weight(85).build();
            Person follower = pb.name("Franklin").weight(70).build();

            pl.setPerson(person);

            pl.setPeople(List.of(person, follower));

            int before = person.getFollowing().size();

            pl.addFollower(follower);

            int after = person.getFollowing().size();

            if (after != before + 1) {
                fail("FAIL: Follower not added");
            } else if (!person.getFollowing().contains(follower)) {
                fail("FAIL: Added follower not found");
            } else {
                pass("PASS: Follower added successfully");
            }

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown:");
            e.printStackTrace();
        }
    }
    private void testRemoveFollower() {

        try {
            PersonPersistence mockPersistence = new PersonPersistence() {
                @Override
                public Person savePerson(Person p) { return null; }

                @Override
                public Collection<Person> loadList() { return null; }
            };

            PersonLogic pl = new PersonLogic(mockPersistence);

            Person.PersonBuilder pb = new Person.PersonBuilder();

            Person main = pb.name("Umang").weight(85).build();
            Person follower = pb.name("Raj").weight(70).build();

            // setup
            main.addFollower(follower);

            pl.setPerson(main);
            pl.setPeople(List.of(main, follower));

            int before = main.getFollowing().size();

            pl.removeFollower(0);

            int after = main.getFollowing().size();

            if (after != before - 1) {
                fail("FAIL: Follower was not removed");
            } else if (main.getFollowing().contains(follower)) {
                fail("FAIL: Follower still present after removal");
            } else {
                pass("PASS: Follower removed successfully");
            }

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown:");
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
