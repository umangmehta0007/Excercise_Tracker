package comp2450.Model;

import comp2450.Exceptions.*;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;
import comp2450.tests.TestResults;

import java.time.LocalDateTime;
import java.util.List;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestPerson {

    private int successes;
    private int failures;

    public TestPerson(){}


    public TestResults runTests(){

               testInvalidName();
               testInvalidZeroWeight();
               testInvalidNegativeWeight();

               testAddActivity();
               testAddGear();
               testRemoveGear();
               testAddFollower();
               testRemoveFollower();

               testCreatePerson();

            return new TestResults(successes, failures);
        }

    // THis is my happy path

    private void testCreatePerson(){
        Person.PersonBuilder builder = new Person.PersonBuilder();

        try{
            Person p = builder.name("Umang").weight(85).build();

            if(!p.getName().equals("Umang")){
                fail("Name was not correctly assigned it should be Umang");
            }else if(p.getWeight() != 85){
                fail("weight assigned should be 85");
            }

            pass("Person was created successfully");

        } catch (Exception e) {
            fail("Happy paths are not suppose to create a Exception");
            e.printStackTrace();
        }


    }

    private void testInvalidName() {

        Person.PersonBuilder builder = new Person.PersonBuilder();

        try {
            builder.name("");
            fail("Should not have succeeded in creating empty name");
        } catch (InvalidNameException e) {
            pass("Successfully Rejected Empty String for Name");
        } catch (Exception e) {
            fail("Some other exception throws");
            e.printStackTrace();
        }
    }

    private void testInvalidZeroWeight(){

            Person.PersonBuilder builder = new Person.PersonBuilder();

            try{
                builder.weight(0);
                fail("Should not have succeeded in creation of Zero weight");
            }catch(InvalidWeightException iwe){
                pass("Successfully rejected Zero invalid weight.");
            }catch(Exception e){
                fail("Some other exception throws");
            }

    }

    private void testInvalidNegativeWeight(){

        Person.PersonBuilder builder = new Person.PersonBuilder();

        try{
            builder.weight(-1);
            fail("Should not have succeeded in creation of negative weight");
        }catch(InvalidWeightException iwe){
            pass("Successfully rejected Negative weight.");
        }catch(Exception e){
            fail("Some other exception throws");
        }

    }

    //Happy path
    private void testAddActivity() {

            Person.PersonBuilder builderPerson  = new Person.PersonBuilder();
            Gears.GearsBuilder builderGear = new Gears.GearsBuilder();
            Activity.ActivityBuilder builderActivity = new Activity.ActivityBuilder();
            Coordinates.CoordinateBuilder builderCoordinates = new Coordinates.CoordinateBuilder();

            LocalDateTime ldt = LocalDateTime.now();

            try{
                Person person = builderPerson.name("Umang").weight(85).build();
                Gears gear = builderGear.nameBuilder("Shoes").usageBuilder("Run").build();
                List<Coordinates> routes = List.of(builderCoordinates.xCoordinates(0).yCoordinates(0).build());
                Activity activity = builderActivity.createName("Running").gears(gear).calendar(ldt).route(routes).distance(10).build();

                int before = person.getMyActivityList().size();

                person.addActivity(activity);

                int after = person.getMyActivityList().size();

                if(after!= (before+1)){
                    fail("Activity was not add successfully");
                }else if(!person.getMyActivityList().contains(activity)){
                    fail("Activity was not added");
                }

                pass("Activity was added successfully");

            }catch(Exception e){
                e.printStackTrace();

                fail("An exception should never be thrown while adding activity");
            }


    }
    private void testAddFollower() {

        try {

            Person.PersonBuilder builder = new Person.PersonBuilder();

            Person me = builder.name("Umang").weight(85).build();
            Person follower = builder.name("Ramesh").weight(70).build();

            int before = me.getFollowing().size();

            me.addFollower(follower);

            int after = me.getFollowing().size();

            if (after != before + 1) {
                fail("Follower was not added successfully (size did not increase)");
            }

            else if (!me.getFollowing().contains(follower)) {

                fail("Follower list does not contain the added follower");

            }

            pass("Follower was added successfully");

        } catch (Exception e) {
            fail("An exception should never be thrown while adding a follower");
            e.printStackTrace();
        }
    }
    private void testAddGear() {

        try {

            Person.PersonBuilder builderPerson = new Person.PersonBuilder();
            Gears.GearsBuilder builderGear = new Gears.GearsBuilder();

            Person person = builderPerson.name("Umang").weight(85).build();

            Gears shoes = builderGear.nameBuilder("Shoes").usageBuilder("Running").build();

            int before = person.getGearsEquipped().size();

            person.addGear(shoes);

            int after = person.getGearsEquipped().size();

            if (after != before + 1) {
                fail("Gear was not added successfully (size did not increase)");
            }

            else if (!person.getGearsEquipped().contains(shoes)) {
                fail("Gear list does not contain the added gear");
            }

            pass("Gear was added successfully");

        } catch (Exception e) {
            fail("An exception should never be thrown while adding gear");
            e.printStackTrace();
        }
    }
    private void testRemoveGear() {

        try {
            Person.PersonBuilder builderPerson = new Person.PersonBuilder();
            Gears.GearsBuilder builderGear = new Gears.GearsBuilder();

            Person person = builderPerson.name("Umang").weight(85).build();

            Gears shoes = builderGear.nameBuilder("Shoes").usageBuilder("Running").build();

            person.addGear(shoes);


            int before = person.getGearsEquipped().size();
            person.removeGear(shoes);
            int after = person.getGearsEquipped().size();

            if (after != before - 1) {
                fail("Gear was not removed");
            }

            else if (person.getGearsEquipped().contains(shoes)) {
                fail("Removed gear still exists in the list");
            }

            pass("Gear was removed successfully");

        } catch (Exception e) {
            fail("An exception should never be thrown while removing gear");
            e.printStackTrace();
        }
    }
    private void testRemoveFollower() {

        try {
            Person.PersonBuilder builder = new Person.PersonBuilder();

            Person person = builder.name("Umang").weight(85).build();
            Person follower = builder.name("Ramesh").weight(70).build();

            person.addFollower(follower);

            int before = person.getFollowing().size();

            person.removeFollower(follower);

            int after = person.getFollowing().size();

            if (after != before - 1) {
                fail("Follower was not removed successfully (size did not decrease)");
            }

            else if (person.getFollowing().contains(follower)) {
                fail("Removed follower still exists in the follower list");
            }

            pass("Follower was removed successfully");

        } catch (Exception e) {
            fail("An exception should never be thrown while removing a follower");
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
