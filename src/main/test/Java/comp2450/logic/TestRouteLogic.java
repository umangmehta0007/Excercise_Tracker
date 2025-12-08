package comp2450.logic;

import comp2450.Logic.MapLogic;
import comp2450.Logic.PersonLogic;
import comp2450.Logic.RouteLogic;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Map.Dimensions;
import comp2450.Model.Map.Map;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;
import comp2450.Persistence.NotFoundException;
import comp2450.Persistence.PersonPersistence;
import comp2450.tests.TestResults;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestRouteLogic {

    private int successes;
    private int failures;

    public TestRouteLogic() { }

    public TestResults runTests() {

        testPathFinding();


        return new TestResults(successes, failures);
    }


    private void testPathFinding() {
        try {
            PersonPersistence mock = new PersonPersistence() {
                @Override
                public Person savePerson(Person t) {
                    return null;
                }

                @Override
                public Collection<Person> loadList() throws NotFoundException {
                    return null;
                }
            };

            PersonLogic pl = new PersonLogic(mock);

            Map.MapBuilder mb = new Map.MapBuilder();
            Person.PersonBuilder pb = new Person.PersonBuilder();
            Gears.GearsBuilder gb = new Gears.GearsBuilder();

            Dimensions dim = new Dimensions(5,5);
            Map map = mb.createName("Mars").dimensions(dim).build();

            MapLogic ml = new MapLogic(map);
            Person p = pb.name("Umang").weight(80).build();
            pl.setPerson(p);
            pl.setPeople(List.of(p));

            Coordinates first = new Coordinates.CoordinateBuilder().xCoordinates(1).yCoordinates(1).build();
            Coordinates second = new Coordinates.CoordinateBuilder().xCoordinates(1).yCoordinates(2).build();
            Coordinates third = new Coordinates.CoordinateBuilder().xCoordinates(1).yCoordinates(3).build();

            Activity a = new Activity.ActivityBuilder()
                    .createName("run")
                    .gears(gb.nameBuilder("shoes").usageBuilder("toRun").build())
                    .calendar(LocalDateTime.now())
                    .route(List.of(first, second, third))
                    .distance(5)
                    .build();

            pl.addActivity(a);

            RouteLogic rl = new RouteLogic(pl, ml);

            Coordinates start = first;
            Coordinates end = third;

            List<Coordinates> path = rl.pathFinding(1, List.of(start, end));


            if (!path.contains(end)) {
                fail("It should contain all coordinates");
            } else {
                pass("PASS: Path found successfully");
            }

        } catch (Exception e) {
            fail("FAIL: Unexpected exception thrown: ");
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
