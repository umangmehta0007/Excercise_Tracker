package comp2450.Model.Map;

import java.util.List;

/**
 * Represents objects that occupy multiple coordinates on the Map,
 * such as Obstacles and Activities.
 */
public interface IObjectsWithCoordinates {

    void addCoordinates(Coordinates coordinates);
    List<Coordinates> getMappingObject();
}
