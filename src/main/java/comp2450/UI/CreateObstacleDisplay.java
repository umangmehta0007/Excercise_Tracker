package comp2450.UI;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.Exceptions.CoordinatesOutOfBoundsException;
import comp2450.Exceptions.Exceptions.ObstacleAlreadyExistsException;
import comp2450.Exceptions.Exceptions.RouteAlreadyExistsException;
import comp2450.Logic.MapLogic;
import comp2450.Exceptions.InvalidNameException;
import comp2450.Model.Map.Coordinates;
import comp2450.Model.Map.Obstacle;

import java.util.Scanner;

public class CreateObstacleDisplay {

    private final Scanner sc;
    private final MapLogic ml;

    public CreateObstacleDisplay(MapLogic ml) {
        this.sc = new Scanner(System.in);
        this.ml = ml;
    }

    public Obstacle createObstacle() {

        Obstacle.ObstacleBuilder builder = new Obstacle.ObstacleBuilder();
        Obstacle obstacle = null;

        getName(builder);
        obstacle = builder.build();
        addFirstCoordinate(obstacle);

        ml.addObstacle(obstacle);

        return obstacle;
    }


    private void getName(Obstacle.ObstacleBuilder builder) {
        Preconditions.checkNotNull(builder, "Builder cannot be null");

        String name = null;
        boolean done = false;

        while (!done) {
            System.out.println("Enter obstacle name:");
            name = sc.nextLine();

            try {
                builder.createName(name);
                done = true;
            } catch (InvalidNameException ine) {
                System.out.println("Invalid name try again.");
            }
        }
    }


    private void addFirstCoordinate(Obstacle obstacle) {
        Preconditions.checkNotNull(obstacle, "Obstacle added can never be null");
        boolean added = false;

        System.out.println("Enter FIRST coordinate:");

        while (!added) {
            CreateCoordinateDisplay builder = new CreateCoordinateDisplay();
            Coordinates cod = builder.createCoordinates();

            try {
                ml.checkValidObsCod(cod);
                obstacle.addCoordinates(cod);
                added = true;

            } catch (CoordinatesOutOfBoundsException ce) {
                System.out.println("Obstacle should be inside map.");

            } catch (RouteAlreadyExistsException re) {
                System.out.println("A route exists here, choose another coordinate: Cannot add Obstacle here.");
            }catch (ObstacleAlreadyExistsException oe){

                System.out.println("An Obstacle exists here, choose another coordinate: Cannot add Obstacle here.");
            }
        }
    }

}