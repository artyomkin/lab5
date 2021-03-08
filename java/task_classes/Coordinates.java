package task_classes;

import exceptions.CoordinatesException;

import java.util.Objects;

/**
 * Coordinates of flat
 * **/
public class Coordinates {
    private Double x;
    private Long y;
    private final Double MAX_X = 69D;
    public Coordinates() {
        x = 0d; y = 0l;
    }
    public Coordinates(Double x, Long y) throws CoordinatesException {
        if (x < 70 && x != null && y != null) {
            this.x = x; this.y = y;
        } else {
            throw new CoordinatesException("Incorrect coordinates data");
        }
    }
    /**
     * @return x coordinate
     * **/
    public Double getX() {
        return x;
    }
    /**
     * @return y coordinate
     * **/
    public Long getY() {
        return y;
    }
    /**
     * Sets x coordinate
     * @param x coordinate
     * **/
    public boolean setX(Double x) {
        try{
            if (x==null) throw new NullPointerException();
            if (x > MAX_X) throw new CoordinatesException("X coordinate cannot be more than " + MAX_X);
            this.x = x;
            return true;
        } catch (CoordinatesException e){
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e){
            System.out.println("X coordinate cannot be null");
            return false;
        }
    }

    /**
     * Sets y coordinate
     * @param y coordinate
     * **/
    public boolean setY(Long y) {
        try{
            if(y == null) throw new NullPointerException();
            this.y = y;
            return true;
        } catch (NullPointerException e){
            System.out.println("Y coordinate cannot be null");
            return false;
        }
    }

    @Override
    public String toString(){
        return "X: " + x.toString() + " Y: " + y.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x.equals(that.x) &&
                y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
