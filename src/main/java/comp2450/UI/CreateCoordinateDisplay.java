package comp2450.UI;

import com.github.lalyos.jfiglet.FigletFont;
import com.google.common.base.Preconditions;
import comp2450.Exceptions.InvalidCoordinatesException;
import comp2450.Model.Map.Coordinates;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateCoordinateDisplay {

    final private Scanner sc;

    public CreateCoordinateDisplay(){
        sc = new Scanner(System.in);
    }


    public Coordinates createCoordinates(){

        Coordinates.CoordinateBuilder builder = new Coordinates.CoordinateBuilder();

        try {
            System.out.println(FigletFont.convertOneLine("Coordinate Creator!"));
        } catch (IOException ignored) { }

        System.out.println("Let's create the Coordinates------> ");

        buildXCoordinate(builder);
        buildYCoordinate(builder);


        return builder.build();

    }

    private void buildXCoordinate(Coordinates.CoordinateBuilder builder){

        Preconditions.checkNotNull(builder, "Builder should not be null.");

        int x = -1;
        do{
            System.out.println("Please enter the X coordinate ");
            try {
                x = sc.nextInt();

                sc.nextLine();
                builder.xCoordinates(x);
            }
            catch(InputMismatchException ime){
                System.out.println("Please enter the digits for X coordinate");
                x = -1;
                sc.nextLine();
            }
            catch(InvalidCoordinatesException ice){
                System.out.println("Please enter the valid state for X coordinate(>=0)");
                x = -1;
            }

        }
        while(x == -1);

    }
    private void buildYCoordinate(Coordinates.CoordinateBuilder builder){

        Preconditions.checkNotNull(builder, "Builder should not be null.");

        int y = -1;
        do{
            System.out.println("Please enter the Y coordinate ");
            try {
                y = sc.nextInt();
                sc.nextLine();

                builder.yCoordinates(y);
            }
            catch(InputMismatchException ime){
                System.out.println("Please enter the digits for Y coordinate");
                y = -1;
                sc.nextLine();
            }
            catch(InvalidCoordinatesException ice){
                System.out.println("Please enter the valid state for Y coordinate(>=0)");
                y = -1;
            }

        }
        while(y == -1);

    }



}
