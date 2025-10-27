package comp2450.Model.Activity;

import comp2450.Model.Map.*;
import com.google.common.base.Preconditions;

import java.util.ArrayList; //would love to see it as a LL it's a kind of route.
import java.util.List;

/**
 * This is the {@link Route} that is followed when creating an {@link Activity}
 * It stores a list of {@link Coordinates} which is {@link #routeCoordinates} the path followed during the {@link Activity}.
 * This also implements {@link IMapping} which, represent this is to be a part of my {@link Map}
 * Distinguishes itself from an {@link Obstacle} via method {@link #type()} being true
 */
public class Route implements IMapping {

    private final List<Coordinates> routeCoordinates;

    private void checkRoute(){

        Preconditions.checkNotNull(routeCoordinates);
        for(Coordinates cood: routeCoordinates){
            Preconditions.checkNotNull(cood, "A coordinates of a Route can never be null");
        }
    }

    public Route(){

        this.routeCoordinates = new ArrayList<>();
        checkRoute();
    }

    /**
     * Adds, coordinates of path followed to the route.
     * @param coordinates the current coordinate x-y of route to be added.
     */
    public void addCoordinates(Coordinates coordinates){

        checkRoute();
        routeCoordinates.add(coordinates);
        checkRoute();

    }

    public List<Coordinates> getCoordinates(){

        return this.routeCoordinates;
    }
    @Override

    public ObjectType type() {
        return ObjectType.ROUTE;
    }

    // my route is just a list of coordinates
}
