package comp2450.Output;

import comp2450.Model.Map.IMapping;
import comp2450.Model.Activity.Activity;

public class MapLegend {

    /**
     * Prints a legend for the map symbols.
     *
     * @param empty    symbol for empty location
     * @param route    symbol for activity route
     * @param obstacle symbol for obstacles
     */
    public static void printLegend(char empty, char route, char obstacle) {
        System.out.println("Legend:");
        System.out.println(empty + " : Empty location");
        System.out.println(route + " : Part of activity's route");
        System.out.println(obstacle + " : Obstacle");
    }


    /**
     * Prints the 2D map grid and assign the character instead of Object from {@link IMapping}
     *
     * @param grid  the grid containing {@link IMapping} objects
     * @param EMPTY symbol for empty location
     * @param ROUTE symbol for route
     * @param OBS   symbol for obstacle
     */
    public static void printGrid(IMapping[][] grid, char EMPTY, char ROUTE, char OBS){

        for(int i = 0;i<grid.length; i++){

            for(int j = 0;j<grid[i].length;j++){

                IMapping myObj = grid[i][j];

                if(myObj == null){ //it firstly check for null to avoid NullPointerException and adds symbol for that
                    System.out.print(EMPTY+" ");
                }
                else if(myObj instanceof Activity){
                    System.out.print(ROUTE+" ");
                }
                else{
                    System.out.print(OBS+" ");
                }
            }
            System.out.println();

        }
    }
}