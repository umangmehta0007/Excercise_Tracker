package comp2450.Model.Map;

import com.google.common.base.Preconditions;
import comp2450.Exceptions.InvalidCoordinatesException;

public class Coordinates {

    private final int xCoordinates;
    private final int yCoordinates;


    public void checkCoordinates() {
        Preconditions.checkState(xCoordinates >= 0, "Coordinates of X need to be greater than equal to 0");
        Preconditions.checkState(yCoordinates >= 0, "Coordinates of Y need to be greater than equal to 0");
    }


    private Coordinates(int xCoordinates, int yCoordinates) {
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;

        checkCoordinates();
    }


    public int xCoordinates(){
        return xCoordinates;
    }
    public int yCoordinates(){
        return yCoordinates;
    }

    public static class CoordinateBuilder {
        private int x;
        private int y;

        public CoordinateBuilder() {}

        public CoordinateBuilder xCoordinates(int x) throws InvalidCoordinatesException {
            if (x < 0) {
                throw new InvalidCoordinatesException();
            }
            this.x = x;
            return this;
        }

        public CoordinateBuilder yCoordinates(int y) throws InvalidCoordinatesException {
            if (y < 0) {
                throw new InvalidCoordinatesException();
            }
            this.y = y;
            return this;
        }

        public Coordinates build() {
            return new Coordinates(x, y);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Preconditions.checkNotNull(obj, "Coordinates entered can never be null");
        Coordinates other = (Coordinates) obj;
        return (this.xCoordinates() == other.xCoordinates() &&
                this.yCoordinates() == other.yCoordinates());
    }
}