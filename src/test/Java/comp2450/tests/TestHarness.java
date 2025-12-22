package comp2450.tests;

import com.github.lalyos.jfiglet.FigletFont;
import comp2450.Model.*;
import comp2450.logic.TestMapLogic;
import comp2450.logic.TestPersonLogic;
import comp2450.logic.TestRouteLogic;
import comp2450.logic.TestTracker;

import java.io.IOException;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestHarness {
    private static int successes;
    private static int failures;

    public static void main(String[] args) {
        bubblePrint("Test Harness");

        TestResults stackResults = new StackTestHarness().runTests();
        TestResults gearResults = new TestGear().runTests();
        TestResults personResults = new TestPerson().runTests();
        TestResults activityResults = new TestActivity().runTests();
        TestResults obstacleResults = new TestObstacle().runTests();
        TestResults coordinatesResults = new TestCoordinates().runTests();
        TestResults mapLogicResults = new TestMapLogic().runTests();
        TestResults personLogicResults = new TestPersonLogic().runTests();
        TestResults routeLogicResults = new TestRouteLogic().runTests();
        TestResults trackerLogicResults = new TestTracker().runTests();



//        successes += personLogicResults.successes()+mapLogicResults.successes()+routeLogicResults.successes();
//        failures += personLogicResults.failures()+mapLogicResults.failures()+routeLogicResults.failures();


        successes += stackResults.successes() +trackerLogicResults.failures()+
                gearResults.successes() + personResults.successes() + activityResults.successes() +
                coordinatesResults.successes() + obstacleResults.successes()+personLogicResults.successes()+
                mapLogicResults.successes()+routeLogicResults.successes();
        failures += stackResults.failures() + gearResults.failures() +
                personResults.failures() + activityResults.failures() +
                coordinatesResults.failures() + obstacleResults.failures()+
                personLogicResults.failures()+mapLogicResults.failures()+
                routeLogicResults.failures()+trackerLogicResults.failures();

        System.out.printf("Total tests: %d\n", successes + failures);
        System.out.printf("\tSuccesses: %d\n", successes);
        System.out.printf("\tFailures: %d\n", failures);

        if (failures > 0) {
            red("There were test failures.");
        } else {
            green("All tests passed!");
        }
    }

    private static void bubblePrint(String message) {
        try {
            System.out.println(FigletFont.convertOneLine(message));
        } catch (IOException ignored) {
        }
    }
}
