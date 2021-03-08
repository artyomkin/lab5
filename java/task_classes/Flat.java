package task_classes;
import exceptions.EmptyStringException;
import exceptions.FlatException;
import utility.RandomStr;

import java.time.LocalDate;
import java.lang.Comparable;
import java.util.Objects;
import java.util.Random;

/**
 * Objects of this class are stored in collection
 * **/
public class Flat implements Comparable<Flat>{

    private static int idCount;
    static {
        idCount = 0;
    }
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private long area;
    private Integer numberOfRooms;
    private double price;
    private Integer livingSpace;
    private Transport transport;
    private House house;

    private static final long MAX_AREA = 897L;
    private static final long MIN_AREA = 0L;
    private static final Integer MAX_NUMBER_OF_ROOMS = 18;
    private static final Integer MIN_NUMBER_OF_ROOMS = 0;
    private static final double MIN_PRICE = 0d;
    private static final Integer MIN_LIVING_SPACE = 0;
    public Flat() {
        this.id = idCount;
        updateIdCount();

        this.name = RandomStr.randomStr();
        this.coordinates = new Coordinates();
        this.creationDate = LocalDate.now();

        Random rand = new Random();
        this.area = (long)1 + rand.nextInt((int)MAX_AREA);
        this.numberOfRooms = 1 + rand.nextInt(MAX_NUMBER_OF_ROOMS);
        this.price = 1 + rand.nextInt(1000);
        this.livingSpace = 1 + rand.nextInt(1000);
        this.transport = null;
        this.house = null;
    }

    public Flat(String name, Coordinates coordinates, long area, Integer numberOfRooms, double price, Integer livingSpace, Transport transport, House house) throws FlatException {
        this.id = idCount;
        updateIdCount();
        if (
                        name != null &&
                        !name.isEmpty() &&
                        coordinates != null &&
                                numberOfRooms!=null &&
                                livingSpace!=null&&
                        area > MIN_AREA &&
                        area <= MAX_AREA &&
                        numberOfRooms > MIN_NUMBER_OF_ROOMS &&
                        numberOfRooms <= MAX_NUMBER_OF_ROOMS &&
                        price > MIN_PRICE &&
                        livingSpace > MIN_LIVING_SPACE
        ) {
            this.name = name;
            this.coordinates = coordinates;
            this.creationDate = LocalDate.now();
            this.area = area;
            this.numberOfRooms = numberOfRooms;
            this.price = price;
            this.livingSpace = livingSpace;
            this.transport = transport;
            this.house = house;
        } else {
            throw new FlatException("Incorrect flat data");
        }
    }

    @Override
    public String toString(){
        return  "ID: " + this.id + '\n' +
                "Name : " + this.name + '\n' +
                "Coordinates: \n" + this.coordinates.toString() + '\n' +
                "Created: " + this.creationDate.toString() + '\n' +
                "Area: " + this.area + '\n' +
                "Number of rooms: " + this.numberOfRooms + '\n' +
                "Price: " + this.price + '\n' +
                "Living space: " + this.livingSpace + '\n' +
                "Transport: " + this.transport.toString() + '\n' +
                "House: \n" + this.house.toString();
    }
    /**
     * Sets name
     * @param name
     * **/
    public boolean setName(String name) {
        try{
            if (name == null) throw new NullPointerException();
            if (name.isEmpty()) throw new EmptyStringException("Empty string");
            this.name = name;
            return true;
        } catch(EmptyStringException e){
            System.out.println(e.getMessage());
            return false;
        } catch(NullPointerException e){
            System.out.println("Name cannot be null");
            return false;
        }
    }

    /**
     * Sets area
     * @param area
     * */
    public boolean setArea(long area) {
        try {
            if (area > MAX_AREA) throw new FlatException("Area cannot be more than " + MAX_AREA);
            if (area <= MIN_AREA) throw new FlatException("Area must be more than " + MIN_AREA);
            this.area = area;
            return true;
        } catch(FlatException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setLivingSpace(Integer livingSpace) {
        try{
            if (livingSpace == null) throw new NullPointerException();
            if (livingSpace <= MIN_LIVING_SPACE) throw new FlatException("Living space must be more than " + MIN_LIVING_SPACE);
            this.livingSpace = livingSpace;
            return true;
        } catch (FlatException e){
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e){
            System.out.println("Incorrect living space");
            return false;
        }

    }
    /**
     * Sets number of rooms
     * @param numberOfRooms
     * **/
    public boolean setNumberOfRooms(Integer numberOfRooms) {
        try{
            if(numberOfRooms == null) throw new NullPointerException();
            if(numberOfRooms>MAX_NUMBER_OF_ROOMS) throw new FlatException("Number of rooms cannot be more than " + MAX_NUMBER_OF_ROOMS);
            if(numberOfRooms<=MIN_NUMBER_OF_ROOMS) throw new FlatException("Number of rooms must be more than " +MIN_NUMBER_OF_ROOMS);
            this.numberOfRooms = numberOfRooms;
            return true;
        } catch (FlatException e){
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e){
            System.out.println("Incorrect number of rooms");
            return false;
        }

    }

    /**
     * Sets price
     * @param price
     * **/
    public boolean setPrice(double price) {
        try{
            if (price<=MIN_PRICE) throw new FlatException("Price must be more than " + MIN_PRICE);
            this.price = price;
            return true;
        } catch (FlatException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setId(int id){
        this.id = id;
        if (this.id>idCount){
                idCount = id;
        }return true;
    }
    /**
     * Sets transport
     * @param transport
     * **/

    public boolean setTransport(Transport transport) {
        try{
            if (transport == null) throw new NullPointerException();
            this.transport = transport;
            return true;
        } catch (NullPointerException e){
            System.out.println("Incorrect transport");
            return false;
        }
    }

    /**
     * @return coordinates
     * **/
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return house
     * **/
    public House getHouse() {
        return house;
    }
    /**
     * @return name
     * **/
    public String getName() {
        return this.name;
    }
    /**
     * @return id
     * **/
    public int getId() {
        return this.id;
    }
    /**
     * @return area
     * **/
    public long getArea() {
        return area;
    }
    /**
     * @return number of rooms
     * **/
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }
    /**
     * @return price
     * **/
    public double getPrice() {
        return price;
    }
    /**
     * @return living space
     * **/
    public Integer getLivingSpace() {
        return livingSpace;
    }

    /**
     * @return transport
     * **/
    public Transport getTransport() {
        return transport;
    }
    /**
     * @return maximum value that area can be
     * **/
    public static long getMAX_AREA() {
        return MAX_AREA;
    }

    public static int getIdCount(){
        return idCount;
    }

    /**
     * Compares this flat to specified one
     * @return negative value if this flat is less than specified one, zero if they are equal and positive value in other cases
     * **/
    public int compareTo(Flat flat) {
        return this.getName().length()-flat.getName().length();
    }
    /**
     * Compares two flats
     * @return negative value if first flat is less than specified one, zero if they are equal and positive value in other cases
     * **/
    public int compare(Flat flat1, Flat flat2) {
        return flat1.compareTo(flat2);
    }
    /**
     * Increments ID count when another instance of flat was created
     * **/
    private static void updateIdCount() {
        idCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return id == flat.id &&
                area == flat.area &&
                Double.compare(flat.price, price) == 0 &&
                name.equals(flat.name) &&
                coordinates.equals(flat.coordinates) &&
                creationDate.equals(flat.creationDate) &&
                numberOfRooms.equals(flat.numberOfRooms) &&
                livingSpace.equals(flat.livingSpace) &&
                transport == flat.transport &&
                Objects.equals(house, flat.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, numberOfRooms, price, livingSpace, transport, house);
    }
}
