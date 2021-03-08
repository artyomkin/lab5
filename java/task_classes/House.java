package task_classes;

import exceptions.HouseException;

import java.util.Objects;
/**
 * Class of house of flat
 * **/
public class House {
    private String name;
    private Integer year;
    private Integer numberOfFloors;
    private long numberOfFlatsOnFloor;
    private Integer numberOfLifts;

    private static final Integer MIN_YEAR = 0;
    private static final Integer MAX_NUMBER_OF_FLOORS = 20;
    private static final Integer MIN_NUMBER_OF_FLOORS = 0;
    private static final long MIN_NUMBER_OF_FLATS_ON_FLOOR = 0l;
    private static final Integer MIN_NUMBER_OF_LIFTS = 0;

    public House() {
        this.name = "unknown";
        this.year = 1;
        this.numberOfFloors = 1;
        this.numberOfFlatsOnFloor = 1;
        this.numberOfLifts = 1;
    }
    public House(String name, Integer year, Integer numberOfFloors, long numberOfFlatsOnFloor, Integer numberOfLifts)
            throws HouseException {
        if (year > 0 && numberOfFloors < 21 && numberOfFloors > 0 && numberOfFlatsOnFloor > 0 && numberOfLifts > 0) {
            this.name = name;
            this.year = year;
            this.numberOfFloors = numberOfFloors;
            this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
            this.numberOfLifts = numberOfLifts;
        } else {
            throw new HouseException("Incorrect house data");
        }
    }
    /**
     * @return name
     * **/
    public String getName() {
        return name;
    }
    /**
     * @return number of floors
     * **/
    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }
    /**
     * @return number of lifts
     * **/
    public Integer getNumberOfLifts() {
        return numberOfLifts;
    }
    /**
     * @return years of house
     * **/
    public Integer getYear() {
        return year;
    }
    /**
     * @return number of flats on floor
     * **/
    public long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }
    /**
     * Sets the name
     * **/
    public boolean setName(String name) {
        try{
            if (name == null) throw new NullPointerException();
            this.name = name;
            return true;
        } catch (NullPointerException e){
            System.out.println("Cannot access name");
            return false;
        }
    }
    /**
     * Sets the number of flats on floor
     * **/
    public void setNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) {
        try{
            if(numberOfFlatsOnFloor <= MIN_NUMBER_OF_FLATS_ON_FLOOR) throw new HouseException("Number of flats on floor must be more than " + MIN_NUMBER_OF_FLATS_ON_FLOOR);
            this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
        } catch (HouseException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Sets the number of floors
     * **/
    public void setNumberOfFloors(Integer numberOfFloors) {
        try{
            if (numberOfFloors>MAX_NUMBER_OF_FLOORS) throw new HouseException("Number of floors cannot be more than " + MAX_NUMBER_OF_FLOORS);
            if (numberOfFloors<=MIN_NUMBER_OF_FLOORS) throw new HouseException("Number of floors must be more than " + MIN_NUMBER_OF_FLOORS);
            this.numberOfFloors = numberOfFloors;
        } catch (HouseException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Sets the number of lifts
     * **/
    public void setNumberOfLifts(Integer numberOfLifts) {
        try{
            if (numberOfLifts<=MIN_NUMBER_OF_LIFTS) throw new HouseException("Number of lifts must be more than " + MIN_NUMBER_OF_LIFTS);
        } catch (HouseException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Sets the year of house
     * **/
    public void setYear(Integer year) {
        try{
            if(year <= MIN_YEAR) throw new HouseException("Year must be more than " + MIN_YEAR);
            this.year = year;
        } catch(HouseException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String toString(){
        return  "Name of house: " + this.name + '\n' +
                "Year of house: " + this.year + '\n' +
                "Number of floors: " + this.numberOfFloors + '\n' +
                "Number of floors: " + this.numberOfFlatsOnFloor + '\n' +
                "Number of lifts: " + this.numberOfLifts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return numberOfFlatsOnFloor == house.numberOfFlatsOnFloor &&
                Objects.equals(name, house.name) &&
                Objects.equals(year, house.year) &&
                numberOfFloors.equals(house.numberOfFloors) &&
                numberOfLifts.equals(house.numberOfLifts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, numberOfFloors, numberOfFlatsOnFloor, numberOfLifts);
    }
}

