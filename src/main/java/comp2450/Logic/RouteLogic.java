package comp2450.Logic;

import comp2450.Model.Activity.Activity;
import comp2450.Model.Exceptions.InvalidRouteSelectionException;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Person.Person;

import java.util.ArrayList;
import java.util.List;

public class RouteLogic {
    private final Person person;
    private final List<Activity> act;

    public RouteLogic(Person person) {
        this.person = person;
        this.act =  person.getMyActivityList();
    }

    public List<Activity> activityList(){
        return this.act;
    }

    public List<Coordinates> getRoute(int index) throws InvalidRouteSelectionException {

        if(index<0 || index>=act.size()){
            throw new InvalidRouteSelectionException();
        }

        return act.get(index).getMappingObject();
    }






}
