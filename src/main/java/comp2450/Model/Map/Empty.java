package comp2450.Model.Map;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Empty implements IMapDataType{

    private final List<Coordinates> myEmptyCoordinates;

    private void checkEmpty(){
        Preconditions.checkNotNull(myEmptyCoordinates, "List can never be null");
        Preconditions.checkState(myEmptyCoordinates.size() ==1, "If a place is empty it should have coordinates.");
    }

    public Empty(Coordinates cood) {
        this.myEmptyCoordinates = new ArrayList<>();
        myEmptyCoordinates.add(cood);
        checkEmpty();
    }

    @Override
    public List<Coordinates> getMappingObject() {
        return Collections.unmodifiableList(myEmptyCoordinates);
    }
}