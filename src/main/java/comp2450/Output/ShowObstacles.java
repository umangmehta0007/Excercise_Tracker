package comp2450.Output;

import comp2450.Model.Map.Obstacle;
import comp2450.Model.Map.Coordinates;

import java.util.List;

/**
 * Displays a list of {@link Obstacle} objects on the map.
 * Prints each obstacle's name and the {@link Coordinates} it occupies.
 */
public class ShowObstacles {

    final private List<Obstacle> myList;

    public ShowObstacles(List myList) {
        this.myList = myList;
    }

    /**
     * Prints all obstacles with their name and coordinates covered.
     */
    public void printObstacle(){

        int curr = 1;
        for (Obstacle obs : myList) {

            List<Coordinates> coordinatesCovered = obs.getMappingObject();

            String summary = curr+". Obstacle" + obs.getName() + "' covers " + coordinatesCovered.size() + " blocks at: ";

            for (int i = 0; i < coordinatesCovered.size(); i++) {
                Coordinates c = coordinatesCovered.get(i);

                summary += "(" + c.xCoordinates() + "," + c.yCoordinates() + ")";
                if (i != coordinatesCovered.size() - 1) {
                    summary += ", ";
                }
            }

            System.out.println(summary);

            curr++;
        }
    }
}
