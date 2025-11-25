package comp2450.UI;

import comp2450.Exceptions.Exceptions.CoordinatesOutOfBoundsException;
import comp2450.Exceptions.Exceptions.ObstacleAlreadyExistsException;
import comp2450.Logic.MapLogic;
import comp2450.Model.Map.Coordinates;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreateRouteManuallyDisplay{
    private Scanner sc;
    private MapLogic ml;

    public CreateRouteManuallyDisplay(MapLogic ml) {
        sc = new Scanner(System.in);
        this.ml = ml;
    }

    public List<Coordinates> createRouteManually() {

        List<Coordinates> route = new ArrayList<>();
        boolean keepAdding = true;

        while (keepAdding) {

            keepAdding = askToContinue();

            if(keepAdding){
                CreateCoordinateDisplay builder = new CreateCoordinateDisplay();
                Coordinates cod = builder.createCoordinates();

                try{
                    ml.checkValidRouteCood(cod);
                    route.add(cod);
                }catch (CoordinatesOutOfBoundsException coob) {

                    System.out.println("The Coordinates should be withing the bounds "+ml.getDimensions());

                }
                catch (ObstacleAlreadyExistsException e) {
                    System.out.println("The Place is non empty, please choose other directions to move or exit");
                }
            }

        }

        return route;
    }


    private boolean askToContinue() {

        boolean valid = false;
        boolean result = false;

        while (!valid) {
            System.out.println("Do you want to coordinate?");
            System.out.println("Enter 1 for Yes");
            System.out.println("Enter 2 for No");

            int choice = -1;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
            }

            if (choice == 1) {
                valid = true;
                result = true;
            }

            if (choice == 2) {
                valid = true;
                result = false;
            }

            if (!valid) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        return result;
    }
}