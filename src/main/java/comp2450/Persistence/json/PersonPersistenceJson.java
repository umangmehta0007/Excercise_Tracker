package comp2450.Persistence.json;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.*;
import comp2450.Model.Activity.Activity;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Gears;
import comp2450.Model.Person.Person;
import comp2450.Persistence.NotFoundException;
import comp2450.Persistence.PersonPersistence;

import javax.json.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public class PersonPersistenceJson implements PersonPersistence {

    private final Path personStorage;


    private void checkPersonPersistenceJson() {
        Preconditions.checkNotNull(personStorage);
    }

    public PersonPersistenceJson(Path personStorage) {
        Preconditions.checkNotNull(personStorage, "File can never be null");

        this.personStorage = personStorage;
        checkPersonPersistenceJson();
    }

    @Override
    public Person savePerson(Person person) {

        Preconditions.checkNotNull(person, "");
        checkPersonPersistenceJson();

        Map<String, Person> allPerson = loadPerson();

        allPerson.put(person.getName(), person);
        savePersons(allPerson.values());

        return person;
    }

    public Collection<Person> loadList() throws NotFoundException {

        Collection<Person> p;

        p = loadPerson().values();

        if (p == null) {
            throw new NotFoundException();
        }

        return p;
    }

    public void savePersons(Collection<Person> earthPeople) {


        Preconditions.checkNotNull(earthPeople, "Collections of people can never be null");


        try {
            JsonWriter writer = Json.createWriter(Files.newOutputStream(personStorage));
            JsonObjectBuilder personJson = Json.createObjectBuilder();

            for (Person person : earthPeople) {
                JsonObjectBuilder trainerJsonBuilder = Json.createObjectBuilder()
                        // pass the initial basic properties to the JsonObjectBuilder:
                        .add("name", person.getName())
                        .add("weight", person.getWeight());

                trainerJsonBuilder.add("gears", personGearsToJson(person.getGearsEquipped()));
                trainerJsonBuilder.add("activities", activityToJson(person.getMyActivityList()));
                trainerJsonBuilder.add("followers", followersToJson(person.getFollowing()));

                // finally: add the trainer to the overall json object we're
                // accumulating
                personJson.add(person.getName(), trainerJsonBuilder.build());
            }
            writer.writeObject(personJson.build());
        } catch (Exception e) {
            // can't write to file, probably.
            throw new RuntimeException(e);
        }

    }

    private JsonObject personGearsToJson(Set<Gears> gears) {

        Preconditions.checkNotNull(gears, "Set of gears can never be null");
        for (var x : gears) {
            Preconditions.checkNotNull(x, "Gear can never be null");
        }

        JsonObjectBuilder gearObjectBuilder = Json.createObjectBuilder();

        for (Gears g : gears) {
            JsonObject gearJson = Json.createObjectBuilder()
                    .add("name", g.getName())
                    .add("usage", g.getUses())
                    .build();

            gearObjectBuilder.add(g.getName(), gearJson);
        }

        return gearObjectBuilder.build();
    }

    private JsonArray activityToJson(List<Activity> activities) {

        Preconditions.checkNotNull(activities, "Set of Activity can never be null");
        for (var x : activities) {
            Preconditions.checkNotNull(x, "ACtivity can never be null");
        }
        JsonArrayBuilder activityBuilder = Json.createArrayBuilder();

        for (Activity a : activities) {


            JsonArrayBuilder routeBuilder = Json.createArrayBuilder();
            for (Coordinates c : a.getMappingObject()) {

                JsonObject coordJson = Json.createObjectBuilder()
                        .add("x", c.xCoordinates())
                        .add("y", c.yCoordinates())
                        .build();

                routeBuilder.add(coordJson);
            }
            JsonObject activityJson = Json.createObjectBuilder()
                    .add("name", a.getName())
                    .add("distance", a.getDistance())
                    .add("date", a.getCalendar().toString())
                    .add("route", routeBuilder)
                    .add("gear", a.getGears().getName())
                    .build();

            activityBuilder.add(activityJson);
        }

        return activityBuilder.build();
    }

    private JsonArray followersToJson(Set<Person> earthPeople) {

        Preconditions.checkNotNull(earthPeople, "Set of Followers can never be null");
        for (var x : earthPeople) {
            Preconditions.checkNotNull(x, "Followers can never be null");
        }
        JsonArrayBuilder followerBuilder = Json.createArrayBuilder();

        for (Person p : earthPeople) {
            followerBuilder.add(p.getName());
        }

        return followerBuilder.build();
    }

//    private Gears gearsFromJson(JsonObject gearsJson) throws InvalidNameException, InvalidUsageException {
//
//        Gears.GearsBuilder gearBuilder = new Gears.GearsBuilder();
//
//        Gears gear = gearBuilder.nameBuilder(gearsJson.getString("name"))
//                .usageBuilder(gearsJson.getString("usage")).build();
//
//        return gear;
//    }

    private List<Coordinates> routeFromJson(JsonArray routeJson) throws InvalidCoordinatesException {

        Preconditions.checkNotNull(routeJson, "Array of routes should never be null");
        List<Coordinates> route = new ArrayList<>();
        Coordinates.CoordinateBuilder builder = new Coordinates.CoordinateBuilder();

        for (JsonValue routes : routeJson) {
            JsonObject c = routes.asJsonObject();

            int x = c.getInt("x");
            int y = c.getInt("y");

            Coordinates coordinate = builder
                    .xCoordinates(x)
                    .yCoordinates(y)
                    .build();

            route.add(coordinate);
        }

        return route;
    }


    public Map<String, Person> loadPerson() {

        checkPersonPersistenceJson();
        Map<String, Person> peopleMap = new HashMap<>();

        if (Files.exists(personStorage)) {
            // only try to do this if the file even exists
            try {
                JsonReader reader = Json.createReader(Files.newInputStream(personStorage));
                JsonObject personsJson = reader.readObject();

                for (String personName : personsJson.keySet()) {
                    // load each Person from a Json object:
                    JsonObject personJson = personsJson.getJsonObject(personName);

                    Person.PersonBuilder personBuilder = new Person.PersonBuilder()
                            // load the basic properties for a trainer:
                            .name(personJson.getString("name"))
                            .weight(personJson.getInt("weight"));

                    Person p = personBuilder.build();

                    peopleMap.put(p.getName(), p);
                }

                for (String personName : personsJson.keySet()) {

                    JsonObject personJson = personsJson.getJsonObject(personName);
                    Person p = peopleMap.get(personJson.getString("name"));

                    Map<String, Gears> gearMap = loadGears(personJson.getJsonObject("gears"));

                    for (Gears g : gearMap.values()) {
                        p.addGear(g);
                    }

                    List<Activity> acts = activityFromJson(gearMap, personJson.getJsonArray("activities"));
                    for (Activity a : acts) {
                        p.addActivity(a);
                    }

                    JsonArray followers = personJson.getJsonArray("followers");

                    /*
                    Literally copied and pasted this whole from what was given in class
                     */
                    for (JsonValue f : followers) {
                        String followerName = ((JsonString) f).getString();
                        Person follower = peopleMap.get(followerName);
                        p.addFollower(follower);
                    }


                }
            } catch (IOException | InvalidNameException | InvalidWeightException | InvalidUsageException |
                     InvalidDistanceException | InvalidCoordinatesException | InvalidRouteException |
                     RoutesNotAdjacentException e) {
                throw new RuntimeException(e);
            }
        }

        return peopleMap;
    }

    private List<Activity> activityFromJson(Map<String, Gears> map, JsonArray activities) throws InvalidCoordinatesException, InvalidNameException, RoutesNotAdjacentException, InvalidRouteException, InvalidDistanceException {
        Preconditions.checkNotNull(map, "Map never be null");
        Preconditions.checkNotNull(activities, "Array of activites should never be null");


        List<Activity> list = new ArrayList<>();

        for (JsonValue val : activities) {

            JsonObject activityJson = val.asJsonObject();

            String name = activityJson.getString("name");
            double distance = activityJson.getJsonNumber("distance").doubleValue();
            LocalDateTime date = LocalDateTime.parse(activityJson.getString("date"));

            String gearName = activityJson.getString("gear");
            Gears gear = map.get(gearName);

            List<Coordinates> routes = routeFromJson(activityJson.getJsonArray("route"));

            Activity activity = new Activity.ActivityBuilder()
                    .createName(name)
                    .gears(gear)
                    .calendar(date)
                    .route(routes)
                    .distance(distance)
                    .build();

            list.add(activity);
        }

        return list;
    }

    private Map<String, Gears> loadGears(JsonObject gearsJson) throws InvalidNameException, InvalidUsageException {
        Preconditions.checkNotNull(gearsJson, "Gears should never be null");

        Map<String, Gears> map = new HashMap<>();

        for (String gearName : gearsJson.keySet()) {

            JsonObject gears = gearsJson.getJsonObject(gearName);

            Gears gear = new Gears.GearsBuilder()
                    .nameBuilder(gears.getString("name"))
                    .usageBuilder(gears.getString("usage"))
                    .build();

            map.put(gearName, gear);
        }

        return map;
    }

}
