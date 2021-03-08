package utility;

import exceptions.*;
import task_classes.Coordinates;
import task_classes.Flat;
import task_classes.House;
import task_classes.Transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Gets all information about flat or its components from user
 * **/
public class FlatAsker {
    private Scanner scanner;
    private BufferedReader fileReader;
    private Console console;
    private final Double MAX_X = 69D;
    private final long MAX_AREA = 897L;
    private final long MIN_AREA = 0L;
    private final Integer MAX_NUMBER_OF_ROOMS = 18;
    private final Integer MIN_NUMBER_OF_ROOMS = 0;
    private final double MIN_PRICE = 0d;
    private final Integer MIN_LIVING_SPACE = 0;
    private final Integer MIN_YEAR = 0;
    private final Integer MAX_NUMBER_OF_FLOORS = 20;
    private final Integer MIN_NUMBER_OF_FLOORS = 0;
    private final long MIN_NUMBER_OF_FLATS_ON_FLOOR = 0l;
    private final Integer MIN_NUMBER_OF_LIFTS = 0;
    private final String YES = "y";
    private final String NO = "n";
    public FlatAsker(Scanner scanner, Console console){
        this.scanner = scanner;
        this.console = console;
    }
    /**
     * Sets new console
     * **/
    public void setFileReader(BufferedReader bufferedReader){
        this.fileReader = bufferedReader;
    }
    /**
     * Throws EmptyStringException if the input is empty to prevent the executing a command with empty argumments
     * **/
    private void exceptionIfEmpty(String s) throws EmptyStringException{
        if (s.isEmpty()) throw new EmptyStringException("Empty string");
    }
    /**
     * Asks the user specified question
     * @returns true if user's answer is yes, else false;
     * @param question
     * **/
    public boolean ask(String question){
        if(!console.isInteractive()) return true;
        String resQuestion = question + " (y/n): ";
        String answer;
        while(true){
            try {
                System.out.println(resQuestion);
                answer = scanner.nextLine().trim();
                if (!answer.equals(YES) && !answer.equals(NO)) {
                    throw new IncorrectInputException("Problems with access to file");
                }
                else {
                    return answer.equals(YES);
                }
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Asks all information about flat
     * @return flat
     * **/
    public Flat askFlat(){
        try{
            return new Flat(askName(),askCoordinates(),askArea(),askNumberOfRooms(),askPrice(),askLivingSpace(),askTransport(),askHouse());
        } catch (FlatException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    /**
     * Asks the name
     * @return name
     * **/
    public String askName(){
        console.println("Enter the name: ");
        String name;
        while(true){
            try{
                if(console.isInteractive()){
                    name = scanner.nextLine().trim();
                } else {
                    name = fileReader.readLine().trim();
                }
                if (name == null) throw new NullPointerException();
                exceptionIfEmpty(name);
                break;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            } catch (NullPointerException e){
                System.out.println("Empty string");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }

        }
        return name;
    }
    /**
     * Asks the X coordinate
     * @return X coordinate
     * **/
    public Double askX(){
        console.println("Enter the x coordinate: ");
        Double x;
        while(true){
            String rawX;
            try{
                if(console.isInteractive()){
                    rawX = scanner.nextLine().trim();
                } else {
                    rawX = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawX);
                x = Double.parseDouble(rawX);
                if (x > MAX_X) throw new IncorrectInputException("X coordinate cannot be more than " + MAX_X);
                break;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                return null;
            }
            catch (NullPointerException | NumberFormatException e){
                System.out.println("Enter the value");
                if (!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return x;
    }
    /**
     * Asks the Y coordinate
     * @return Y coordinate
     * **/
    public Long askY(){
        console.println("Enter the y coordinate: ");
        Long y;
        while(true){
            String rawY;
            try{
                if (console.isInteractive()){
                    rawY = scanner.nextLine().trim();
                } else {
                    rawY = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawY);
                if (rawY == null) throw new NullPointerException();
                y = Long.parseLong(rawY);
                break;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Enter the number");
                if (!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return y;
    }
    /**
     * Asks the area
     * @return area
     * **/
    public long askArea(){
        console.println("Enter the area: ");
        long area;
        while(true){
            String rawArea;
            try{
                if (console.isInteractive()){
                    rawArea = scanner.nextLine().trim();
                } else {
                    rawArea = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawArea);
                area = Long.parseLong(rawArea);
                if (area > MAX_AREA) throw new IncorrectInputException("Area cannot be more than " + MAX_AREA);
                if (area <= MIN_AREA) throw new IncorrectInputException("Area must be more than " + MIN_AREA);
                break;
            }catch(EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return MIN_AREA-1;
            }catch(NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return MIN_AREA-1;
            }catch(IOException e){
                System.out.println("Problems with access to file");
                return MIN_AREA-1;
            }
        }

        return area;
    }
    /**
     * Asks the coordinates
     * @return coordinate
     * **/
    public Coordinates askCoordinates(){
        console.println("Enter the coordinates: ");
        Double x = askX();
        Long y = askY();
        try {
            Coordinates coordinates = new Coordinates(x,y);
            return coordinates;
        } catch(CoordinatesException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
     * Asks the coordinates
     * @reutrn coordinate
     * **/
    public Integer askNumberOfRooms(){
        console.println("Enter the number of rooms: ");
        Integer numberOfRooms;
        while(true){
            String rawNumberOfRooms;
            try{
                if(console.isInteractive()){
                    rawNumberOfRooms = scanner.nextLine().trim();
                } else{
                    rawNumberOfRooms = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawNumberOfRooms);
                numberOfRooms = Integer.parseInt(rawNumberOfRooms);
                if(numberOfRooms > MAX_NUMBER_OF_ROOMS) throw new IncorrectInputException("Number of rooms cannot be more than " + MAX_NUMBER_OF_ROOMS);
                if(numberOfRooms <= MIN_NUMBER_OF_ROOMS) throw new IncorrectInputException("Number of rooms must be more than " + MIN_NUMBER_OF_ROOMS);
                break;
            }catch(EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return numberOfRooms;
    }
    /**
     * Asks the price
     * @reutrn price
     * **/
    public double askPrice(){
        console.println("Enter the price: ");
        double price;
        while(true){
            try{
                String rawPrice;
                if(console.isInteractive()){
                    rawPrice = scanner.nextLine().trim();
                } else {
                    rawPrice = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawPrice);
                price = Double.parseDouble(rawPrice);
                if (price <= MIN_PRICE) throw new IncorrectInputException("Price must be more than " + MIN_PRICE);
                break;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return MIN_PRICE-1;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return MIN_PRICE-1;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return MIN_PRICE-1;
            }
        }

        return price;
    }
    /**
     * Asks the living space
     * @reutrn living space
     * **/
    public Integer askLivingSpace(){
        console.println("Enter the living space: ");
        Integer livingSpace;
        while (true){
            try{
                String rawLivingSpace;
                if(console.isInteractive()){
                    rawLivingSpace = scanner.nextLine().trim();
                } else {
                    rawLivingSpace = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawLivingSpace);
                livingSpace = Integer.parseInt(rawLivingSpace);
                if (livingSpace <= MIN_LIVING_SPACE) throw new IncorrectInputException("Living space must be more than " + MIN_LIVING_SPACE);
                break;
            }catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return livingSpace;
    }
    /**
     * Asks the transport
     * @reutrn transport
     * **/
    public Transport askTransport(){
        Class transportClass = Transport.class;
        console.println("Available values of transport: ");
        if(console.isInteractive())
        for (Object enumConstant : transportClass.getEnumConstants()){
            System.out.println(enumConstant);
        }
        console.println("Enter the transport: ");
        Transport transport;
        while(true){
            String rawTransport;
            try{
                if(console.isInteractive()){
                    rawTransport = scanner.nextLine().trim();
                } else {
                    rawTransport = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawTransport);
                transport = Transport.valueOf(rawTransport);
                break;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            } catch (IllegalArgumentException e){
                System.out.println("Incorrect transport name");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return transport;
    }
    /**
     * Asks the year of house
     * @reutrn year of house
     * **/
    public Integer askHouseYear(){
        console.println("Enter the year of house: ");
        Integer year;
        while(true){
            String rawYear;
            try{
                if(console.isInteractive()){
                    rawYear = scanner.nextLine().trim();
                } else {
                    rawYear = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawYear);
                year = Integer.parseInt(rawYear);
                if (year <= MIN_YEAR) throw new IncorrectInputException("Year must be more than " + MIN_YEAR);
                break;
            }catch(EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return year;
    }
    /**
     * Asks the number of floors in house
     * @reutrn number of floors in house
     * **/
    public Integer askHouseNumberOfFloors(){
        console.println("Enter the number of floors: ");
        Integer numberOfFloors;
        while(true){
            String rawNumberOfFloors;
            try{
                if (console.isInteractive()){
                    rawNumberOfFloors = scanner.nextLine().trim();
                } else {
                    rawNumberOfFloors = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawNumberOfFloors);
                numberOfFloors = Integer.parseInt(rawNumberOfFloors);
                if (numberOfFloors > MAX_NUMBER_OF_FLOORS) throw new IncorrectInputException("Number of floors cannot be more than " + MAX_NUMBER_OF_FLOORS);
                if (numberOfFloors <= MIN_NUMBER_OF_FLOORS) throw new IncorrectInputException("Number of floors must be more than " + MIN_NUMBER_OF_FLOORS);
                break;
            }catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if (!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return numberOfFloors;
    }
    /**
     * Asks the number of flats on each floor in house
     * @reutrn number of flats on each floor in house
     * **/
    public long askHouseNumberOfFlatsOnFloor(){
        console.println("Enter the number of flats on the floor: ");
        long numberOfFlatsOnFloor;
        while(true){
            String rawNumberOfFlatsOnFloor;
            try{
                if(console.isInteractive()){
                    rawNumberOfFlatsOnFloor = scanner.nextLine().trim();
                } else {
                    rawNumberOfFlatsOnFloor = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawNumberOfFlatsOnFloor);
                numberOfFlatsOnFloor = Long.parseLong(rawNumberOfFlatsOnFloor);
                if (numberOfFlatsOnFloor <= MIN_NUMBER_OF_FLATS_ON_FLOOR) throw new IncorrectInputException("Number of flats on floor must be more than" + MIN_NUMBER_OF_FLATS_ON_FLOOR);
                break;
            }catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return MIN_NUMBER_OF_FLATS_ON_FLOOR-1;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return MIN_NUMBER_OF_FLATS_ON_FLOOR-1;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return MIN_NUMBER_OF_FLATS_ON_FLOOR-1;
            }
        }

        return numberOfFlatsOnFloor;
    }
    /**
     * Asks the number of lifts in house
     * @reutrn number of lifts in house
     * **/
    public Integer askHouseNumberOfLifts(){
        console.println("Enter the number of lifts: ");
        Integer numberOfLifts;
        while(true){
            String rawNumberOfLifts;
            try{
                if(console.isInteractive()){
                    rawNumberOfLifts = scanner.nextLine().trim();
                } else {
                    rawNumberOfLifts = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawNumberOfLifts);
                numberOfLifts = Integer.parseInt(rawNumberOfLifts);
                if (numberOfLifts <= MIN_NUMBER_OF_LIFTS) throw new IncorrectInputException("Number of lifts must be more than " + MIN_NUMBER_OF_LIFTS);
                break;
            }catch(EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                return null;
            }
        }

        return numberOfLifts;
    }
    /**
     * Asks all information about the house
     * @reutrn house
     * **/
    public House askHouse(){
        console.println("Enter the information about house: ");
        try {
            House house = new House(askName(),askHouseYear(),askHouseNumberOfFloors(),askHouseNumberOfFlatsOnFloor(),askHouseNumberOfLifts());
            return house;
        } catch (HouseException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
