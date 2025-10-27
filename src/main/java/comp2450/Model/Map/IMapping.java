package comp2450.Model.Map;

import comp2450.Model.Activity.Route;
import comp2450.Model.Map.Coordinates;

import java.util.List;

/**
 * An interface for all the items on {@link Map}
 * This contains methods necessary for {@link Route} and {@link Obstacle}
 */
public interface IMapping {

    ObjectType type();
    void addCoordinates(Coordinates coordinates);
    List<Coordinates> getCoordinates();
}
