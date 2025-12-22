package comp2450.Model;

import comp2450.Exceptions.InvalidNameException;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Map.Obstacle;
import comp2450.tests.TestResults;

import java.util.List;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestObstacle {

    private int successes;
    private int failures;

    public TestObstacle() {
    }

    public TestResults runTests() {

        testInvalidName();
        testAddCoordinates();
        testCreateObstacle();

        return new TestResults(successes, failures);
    }

    private void testCreateObstacle() {

        Obstacle.ObstacleBuilder builder = new Obstacle.ObstacleBuilder();

        try {
            Obstacle obs = builder.createName("Tree").build();

            if (!obs.getName().equals("Tree")) {
                fail("Obstacle name was not correctly assigned expected 'Tree'");
            }

            if (!obs.getMappingObject().isEmpty()) {
                fail("Newly created obstacle must start with zero coordinates");
            }

            pass("Obstacle was created successfully");

        } catch (Exception e) {
            fail("Happy path should never throw an exception while creating Obstacle");
            e.printStackTrace();
        }
    }


    private void testInvalidName() {

        Obstacle.ObstacleBuilder builder = new Obstacle.ObstacleBuilder();

        try {
            builder.createName("");
            fail("Should not have succeeded in creating empty name");
        } catch (InvalidNameException e) {
            pass("Successfully rejected empty name for Obstacle");
        } catch (Exception e) {
            fail("Some other exception was thrown");
            e.printStackTrace();
        }
    }

    private void testAddCoordinates() {

        Obstacle.ObstacleBuilder builder = new Obstacle.ObstacleBuilder();
        Coordinates.CoordinateBuilder builderC = new Coordinates.CoordinateBuilder();

        try {
            Obstacle obs = builder.createName("Building").build();

            Coordinates cood = builderC.xCoordinates(1).yCoordinates(2).build();

            obs.addCoordinates(cood);

            List<Coordinates> obstacle = obs.getMappingObject();

            if (obstacle.size() != 1) {
                fail("Coordinates were not added successfully expected size 1");
            } else if (!obstacle.contains(cood)) {
                fail("Added coordinates are not present in the obstacle");
            }

            pass("Coordinates were added successfully for the Obstacle");

        } catch (Exception e) {
            fail("Happy path should never throw an exception while adding Coordinates");
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
