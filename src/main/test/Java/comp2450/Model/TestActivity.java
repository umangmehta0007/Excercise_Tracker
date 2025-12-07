package comp2450.Model;

import comp2450.Exceptions.InvalidDistanceException;
import comp2450.Exceptions.InvalidNameException;
import comp2450.Exceptions.InvalidRouteException;
import comp2450.Exceptions.RoutesNotAdjacentException;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Gears;
import comp2450.tests.TestResults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestActivity {
    private int successes;
    private int failures;

    public TestActivity() {
    }

    public TestResults runTests() {

        testInvalidNameActivity();
        testInvalidNonAdjacentRouteTaken();
        testInvalidEmptyRouteTaken();
        testInvalidNegativeDistance();
        testInvalidZeroDistance();

        testCreateActivity();

        return new TestResults(successes, failures);
    }

    //Happy path
    private void testCreateActivity() {
        Activity.ActivityBuilder builderA = new Activity.ActivityBuilder();
        Coordinates.CoordinateBuilder builderC = new Coordinates.CoordinateBuilder();
        Gears.GearsBuilder builderG = new Gears.GearsBuilder();

        try {
            Coordinates a = builderC.xCoordinates(0).yCoordinates(0).build();
            Coordinates b = builderC.xCoordinates(0).yCoordinates(1).build();
            Gears used = builderG.nameBuilder("Shoes").usageBuilder("Running").build();
            List<Coordinates> routes = List.of(a, b);
            LocalDateTime ldt = LocalDateTime.now();

            Activity act = builderA.createName("Running").gears(used).calendar(ldt).route(routes).distance(10).build();

            if (!act.getName().equals("Running")) {

                fail("Name was not correctly assigned. Expected Running");

            } else if (act.getGears() != used) {

                fail("Gear assigned is not the same gear passed");

            } else if (!act.getCalendar().equals(ldt)) {

                fail("Calendar was not correctly assigned");

            } else if (act.getDistance() != 10) {
                fail("Distance should be 10");

            } else if (!act.getMappingObject().equals(routes)) {
                fail("Route was not correctly assigned");

            }

            pass("Activity was created successfully");

        } catch (Exception e) {

            fail("No exception should be thrown in happy paths");
        }

    }


    private void testInvalidNameActivity() {

        Activity.ActivityBuilder builder = new Activity.ActivityBuilder();

        try {
            builder.createName("");
            fail("Should not have succeeded in creating empty activity name");
        } catch (InvalidNameException e) {
            pass("Successfully rejected empty activity name");
        } catch (Exception e) {
            fail("Some other exception was thrown");
            e.printStackTrace();
        }
    }


    private void testInvalidNonAdjacentRouteTaken() {

        Activity.ActivityBuilder builderActivity = new Activity.ActivityBuilder();
        Coordinates.CoordinateBuilder builderCoordinates = new Coordinates.CoordinateBuilder();

        try {

            Coordinates c1 = builderCoordinates.xCoordinates(0).yCoordinates(0).build();
            Coordinates c2 = builderCoordinates.xCoordinates(5).yCoordinates(5).build();
            List<Coordinates> nonAdjacentRoute = List.of(c1, c2);

            builderActivity.route(nonAdjacentRoute);
            fail("Non-adjacent route should never be allowed");

        } catch (RoutesNotAdjacentException e) {
            pass("Successfully rejected a non-adjacent route");

        } catch (Exception e) {
            fail("Unexpected exception type was thrown");
            e.printStackTrace();
        }
    }

    private void testInvalidEmptyRouteTaken() {
        Activity.ActivityBuilder builderActivity = new Activity.ActivityBuilder();

        List<Coordinates> emptyRoute = new ArrayList<>();

        try {
            builderActivity.route(emptyRoute);
            fail("Empty route should have never been created");

        } catch (InvalidRouteException e) {
            pass("Successfully caught the empty route which should never be built");
        } catch (Exception e) {
            fail("Some other exception was thrown");
            e.printStackTrace();
        }

    }


    private void testInvalidNegativeDistance() {

        Activity.ActivityBuilder builder = new Activity.ActivityBuilder();

        try {
            builder.distance(-5);
            fail("Should not have succeeded in creating activity with negative distance");
        } catch (InvalidDistanceException e) {
            pass("Successfully rejected negative distance");
        } catch (Exception e) {
            fail("Some other exception was thrown");
            e.printStackTrace();
        }
    }

    private void testInvalidZeroDistance() {

        Activity.ActivityBuilder builder = new Activity.ActivityBuilder();

        try {
            builder.distance(0);
            fail("Should not have succeeded in creating activity with zero distance");
        } catch (InvalidDistanceException e) {
            pass("Successfully rejected zero distance");
        } catch (Exception e) {
            fail("Some other exception was thrown");
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

