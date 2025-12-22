package comp2450.logic;

import comp2450.Exceptions.Exceptions.CoordinatesOutOfBoundsException;
import comp2450.Exceptions.Exceptions.InvalidSelectionException;
import comp2450.Exceptions.Exceptions.ObstacleAlreadyExistsException;
import comp2450.Logic.MapLogic;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.*;
import comp2450.Model.Person.Gears;
import comp2450.tests.TestResults;

import java.time.LocalDateTime;
import java.util.List;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestMapLogic {
    private int successes;
    private int failures;

    public TestMapLogic() { }

    public TestResults runTests() {

        testCheckWithinBound();
        testNotObstacle();
        testAddObstacle();
        testRemoveObstacle();
        testResetGrid();
        testCreateGrid();

        return new TestResults(successes, failures);
    }


    private void testCheckWithinBound() {

        try {

            Dimensions dim = new Dimensions(10, 10);

            Map.MapBuilder mb = new Map.MapBuilder();

            Map map = mb.createName("Mars")
                    .dimensions(dim)
                    .build();

            MapLogic ml = new MapLogic(map);

            //Happy path
            try {
                Coordinates cood = new Coordinates.CoordinateBuilder()
                        .xCoordinates(1)
                        .yCoordinates(1)
                        .build();

                ml.checkWithinBound(cood);

                pass("Coordinates within the bounds.");
            } catch (Exception e) {
                fail("FAIL: Unexpected exception thrown: ");
                e.printStackTrace();
            }


            //TesInvalid X-Coordinates
            try {
                Coordinates cood = new Coordinates.CoordinateBuilder()
                        .xCoordinates(11)
                        .yCoordinates(1)
                        .build();

                ml.checkWithinBound(cood);

                fail("Should not be able to create these coordinates");
            } catch (CoordinatesOutOfBoundsException e) {
                pass("PASS: Successfully rejected incorrect coordinates");
            }catch (Exception e) {
                fail("FAIL: Unexpected exception thrown: ");
                e.printStackTrace();
            }

            //Invalid Y-coordinates
            try {
                Coordinates cood = new Coordinates.CoordinateBuilder()
                        .xCoordinates(1)
                        .yCoordinates(11)
                        .build();

                ml.checkWithinBound(cood);

                fail("Should not be able to create these coordinates");
            } catch (CoordinatesOutOfBoundsException e) {
                pass("PASS: Successfully rejected incorrect coordinates");
            }
            catch (Exception e) {
                fail("FAIL: Unexpected exception thrown: ");
                e.printStackTrace();
            }

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
            e.printStackTrace();
        }
    }
    private void testNotObstacle() {

        try {
            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder mb = new Map.MapBuilder();

            Map map = mb.createName("Mars")
                    .dimensions(dim)
                    .build();

            MapLogic ml = new MapLogic(map);


            Coordinates c = new Coordinates.CoordinateBuilder()
                    .xCoordinates(1)
                    .yCoordinates(1)
                    .build();

            Obstacle obstacle = new Obstacle.ObstacleBuilder()
                    .createName("Tree")
                    .build();
            obstacle.addCoordinates(c);

            map.addObstacle(obstacle);

            //Happy path
            try {
                Coordinates route = new Coordinates.CoordinateBuilder()
                        .xCoordinates(0)
                        .yCoordinates(0)
                        .build();

                ml.checkValidObsCod(route);

                pass("PASS: Successfully placed Route");
            }  catch (Exception e) {
                fail("FAIL: Unexpected exception thrown: ");
                e.printStackTrace();
            }

            //Route cannot be placed over obstacle
            try {
                Coordinates route = new Coordinates.CoordinateBuilder()
                        .xCoordinates(1)
                        .yCoordinates(1)
                        .build();

                ml.checkValidObsCod(route);

                fail("Obstacle already exists, route should not be added");

            } catch (ObstacleAlreadyExistsException e) {
                pass("PASS: Cannot add route when there is already obstacle.");
            }
            catch (Exception e) {
                fail("FAIL: Unexpected exception thrown: ");
                e.printStackTrace();
            }

        } catch (Exception e) {
            fail("FAIL: Unexpected exception during test setup: " + e);
            e.printStackTrace();
        }
    }
    private void testAddObstacle() {

        try {
            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder mb = new Map.MapBuilder();

            Map map = mb.createName("Mars")
                    .dimensions(dim)
                    .build();
            MapLogic ml = new MapLogic(map);

            Obstacle o = new Obstacle.ObstacleBuilder()
                    .createName("police")
                    .build();
            o.addCoordinates(new Coordinates.CoordinateBuilder()
                    .xCoordinates(1).yCoordinates(1).build());

            int before = map.getObsInMap().size();

            ml.addObstacle(o);

            int after = map.getObsInMap().size();

            if (after != before + 1) {
                fail("Obstacle was not added to map");
            } else if (!map.getObsInMap().contains(o)) {
                fail("Obstacle added is not stored inside map");
            }

            pass("Obstacle added successfully");


        }  catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
            e.printStackTrace();
        }
    }
    private void testRemoveObstacle() {
        try {
            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder mb = new Map.MapBuilder();

            Map map = mb.createName("Mars")
                    .dimensions(dim)
                    .build();
            MapLogic ml = new MapLogic(map);

            Obstacle obstacle = new Obstacle.ObstacleBuilder()
                    .createName("Tree")
                    .build();

            obstacle.addCoordinates(new Coordinates.CoordinateBuilder()
                    .xCoordinates(0).yCoordinates(0).build());

            ml.addObstacle(obstacle);

            int before = map.getObsInMap().size();

            ml.removeObstacle(0);

            int after = map.getObsInMap().size();

            if (after != before - 1) {
                fail("Obstacle was not removed correctly");
            }

            pass("Obstacle removed successfully");

        }  catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
            e.printStackTrace();
        }

        // invalidSelectionExceptionTest
        try {
            Dimensions dim = new Dimensions(10, 10);
            Map map = new Map.MapBuilder()
                    .createName("Earth")
                    .dimensions(dim)
                    .build();
            MapLogic ml = new MapLogic(map);

            ml.removeObstacle(15);
            fail("IndexOUT Of bounds");

        } catch (InvalidSelectionException e) {
            pass("Correctly caught invalid index in removeObstacle");
        }  catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
            e.printStackTrace();
        }
    }
    private void testResetGrid() {
        try {
            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder mb = new Map.MapBuilder();

            Map map = mb.createName("Mars")
                    .dimensions(dim)
                    .build();
            MapLogic ml = new MapLogic(map);

            IMapDataType[][] grid = map.getGrid();

            Obstacle test = new Obstacle.ObstacleBuilder()
                    .createName("Rock")
                    .build();

            grid[1][1] = test;

            ml.resetGrid();

            IMapDataType[][] after = map.getGrid();
            for (int i = 0; i < dim.nRows(); i++) {
                for (int j = 0; j < dim.nCols(); j++) {

                    if (!(after[i][j] instanceof Empty)) {
                        fail("Grid was not reset successfully");
                    }
                }
            }

            pass("resetGrid correctly resets entire map");

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
            e.printStackTrace();
        }
    }
    private void testCreateGrid() {
        try {

            Dimensions dim = new Dimensions(10, 10);
            Map.MapBuilder mb = new Map.MapBuilder();

            Map map = mb.createName("Map").dimensions(dim).build();

            MapLogic ml = new MapLogic(map);

            Coordinates.CoordinateBuilder cb = new Coordinates.CoordinateBuilder();
            Obstacle.ObstacleBuilder ob = new Obstacle.ObstacleBuilder();
            Gears.GearsBuilder gb = new Gears.GearsBuilder();

            Obstacle obstacle = ob.createName("Rock").build();
            Gears gear = gb.nameBuilder("shoes").usageBuilder("run").build();

            obstacle.addCoordinates(cb.xCoordinates(0).yCoordinates(0).build());
            map.addObstacle(obstacle);

            Coordinates r1 = cb.xCoordinates(1).yCoordinates(1).build();
            List<Coordinates> route = List.of(r1);

            Activity act = new Activity.ActivityBuilder()
                    .createName("Run")
                    .gears(gear)
                    .calendar(LocalDateTime.now())
                    .route(route)
                    .distance(5)
                    .build();

            ml.createGrid(List.of(act));

            IMapDataType[][] grid = map.getGrid();


            if (!(grid[0][0] instanceof Obstacle)) {
                fail("Obstacle not placed correctly in Gird");
            } else {
                pass("Obstacle placed correctly");
            }

            if (!(grid[1][1] instanceof Activity)) {
                fail("Obstacle not placed correctly in Gird");
            }

            pass("Route placed correctly");


        } catch (Exception e) {
            fail("Unexpected exception in testCreateGrid()");
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
