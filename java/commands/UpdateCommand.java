package commands;

import exceptions.EmptyStringException;
import exceptions.IncorrectInputException;
import task_classes.Flat;
import utility.FlatAsker;
import utility.HashMapManager;

import java.util.HashMap;
/**
 * Updates the element by its key
 * **/
public class UpdateCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    private FlatAsker flatAsker;
    public UpdateCommand(HashMapManager hashMapManager, FlatAsker flatAsker){
        super("update","updates the element with specified id");
        this.hashMapManager = hashMapManager;
        this.flatAsker = flatAsker;
    }

    /**
     * Executes the command
     * @return exit status of command
     * @param id that will be used to perform the update
     * **/
    @Override
    public boolean execute(String id){
        try{
            boolean res = true;
            if(id.isEmpty()) throw new EmptyStringException("You should specify the key");
            Flat flat = hashMapManager.getHashMap().get(Integer.parseInt(id));
            if(flat == null) throw new IncorrectInputException("No flat with such key");
            if(flatAsker.ask("Do you want to change the name?")) res = res && flat.setName(flatAsker.askName());
            if(flatAsker.ask("Do you want to change the coordinates?")){
                if(flatAsker.ask("Do you want to change the x coordinate?")) res = res && flat.getCoordinates().setX(flatAsker.askX());
                if(flatAsker.ask("Do you want to change the y coordinate?")) flat.getCoordinates().setY(flatAsker.askY());
            }
            if(flatAsker.ask("Do you want to change the area?")) flat.setArea(flatAsker.askArea());
            if(flatAsker.ask("Do you want to change the number of rooms?")) flat.setNumberOfRooms(flatAsker.askNumberOfRooms());
            if(flatAsker.ask("Do you want to change the price?")) flat.setPrice(flatAsker.askPrice());
            if(flatAsker.ask("Do you want to change the living space?")) flat.setLivingSpace(flatAsker.askLivingSpace());
            if(flatAsker.ask("Do you want to change the transport?")) flat.setTransport(flatAsker.askTransport());
            if(flatAsker.ask("Do you want to change the house?")){
                if(flatAsker.ask("Do you want to change the name of the house?")) flat.getHouse().setName(flatAsker.askName());
                if(flatAsker.ask("Do you want to change the year of the house?")) flat.getHouse().setYear(flatAsker.askHouseYear());
                if(flatAsker.ask("Do you want to change the number of floors of the house?")) flat.getHouse().setNumberOfFloors(flatAsker.askHouseNumberOfFloors());
                if(flatAsker.ask("Do you want to change the number of flats on a single floor?")) flat.getHouse().setNumberOfFlatsOnFloor(flatAsker.askHouseNumberOfFlatsOnFloor());
                if(flatAsker.ask("Do you want to change the number of lifts?")) flat.getHouse().setNumberOfLifts(flatAsker.askHouseNumberOfLifts());
            }
        } catch (EmptyStringException | IncorrectInputException e){
            System.out.println(e.getMessage());
            return false;
        } catch (NumberFormatException e){
            System.out.println("Key must be a number");
            return false;
        }
    }
}
