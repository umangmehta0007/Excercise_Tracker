package comp2450.tests;

import com.github.lalyos.jfiglet.FigletFont;
import comp2450.Model.*;
import comp2450.Model.StackTestHarness;

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


//        successes += coordinatesResults.successes();
//        failures += coordinatesResults.failures();


        successes += stackResults.successes() + gearResults.successes() + personResults.successes() + activityResults.successes() + coordinatesResults.successes() + obstacleResults.successes();
        failures += stackResults.failures() + gearResults.failures() + personResults.failures() + activityResults.failures() + coordinatesResults.failures() + obstacleResults.failures();

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
