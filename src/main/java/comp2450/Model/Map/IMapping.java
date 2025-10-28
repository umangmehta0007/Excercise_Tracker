package comp2450.Model.Map;

import java.util.List;
import comp2450.Model.Activity.Activity;

/**
 * An interface for all the items on {@link Map}
 * This contains methods necessary for {@link Activity} and {@link Obstacle}
 */
public interface IMapping {
    void addCoordinates(Coordinates coordinates);
    List<Coordinates> getMappingObject();
}
