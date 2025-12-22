package comp2450.Model;

import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Model.Map.Coordinates;
import comp2450.tests.TestResults;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestCoordinates {
    private int successes;
    private int failures;

    public TestCoordinates() {
    }

    public TestResults runTests() {

        testInvalidYCoordinate();
        testInvalidXCoordinate();
        testCreateCoordinate();
        return new TestResults(successes, failures);
    }

    //Happy path
    private void testCreateCoordinate() {

        Coordinates.CoordinateBuilder builder = new Coordinates.CoordinateBuilder();

        try {
            Coordinates c = builder.xCoordinates(3).yCoordinates(4).build();

            if (c.xCoordinates() != 3) {
                fail("X coordinate was not correctly assigned");
            } else if (c.yCoordinates() != 4) {
                fail("Y coordinate was not correctly assigned");
            } else {
                pass("Coordinate was created successfully");
            }

        } catch (Exception e) {
            fail("Happy paths should never throw any exception");
            e.printStackTrace();
        }
    }

    private void testInvalidXCoordinate() {

        Coordinates.CoordinateBuilder builder = new Coordinates.CoordinateBuilder();

        try {
            builder.xCoordinates(-1);
            fail("Should not have succeeded in creating negative X coordinate");
        } catch (InvalidCoordinatesException e) {
            pass("Successfully rejected negative X coordinate");
        } catch (Exception e) {
            fail("Some other exception was thrown");
            e.printStackTrace();
        }
    }

    private void testInvalidYCoordinate() {

        Coordinates.CoordinateBuilder builder = new Coordinates.CoordinateBuilder();

        try {
            builder.yCoordinates(-1);
            fail("Should not have succeeded in creating negative Y coordinate");
        } catch (InvalidCoordinatesException e) {
            pass("Successfully rejected negative Y coordinate");
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
