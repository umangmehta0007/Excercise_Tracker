package comp2450.Model.Map;

import com.google.common.base.Preconditions;
import comp2450.Model.Map.Map;


/**
 * Represents the size of my {@link Map}
 * @param nRows
 * @param nCols
 */
public record Dimensions(int nRows, int nCols) {


    private void checkDimensions(){
        Preconditions.checkState(nRows>0,"Rows in my Map should ALWAYS be greater than zero ");
        Preconditions.checkState(nCols>0,"Columns in my Map should ALWAYS be greater than zero ");

    }
    public Dimensions(int nRows, int nCols){

        this.nRows = nRows;
        this.nCols = nCols;
        checkDimensions();
    }
}
