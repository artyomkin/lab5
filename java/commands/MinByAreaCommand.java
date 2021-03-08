package commands;

import exceptions.EmptyCollectionException;
import exceptions.EmptyStringException;
import task_classes.Flat;
import utility.HashMapManager;

import java.util.HashMap;
import java.util.Iterator;
/**
 * Prints the flat with the least value of area
 * **/
public class MinByAreaCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    public MinByAreaCommand(HashMapManager hashMapManager){
        super("min_by_area","print the element with minimal value of area");
        this.hashMapManager = hashMapManager;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/

    @Override
    public boolean execute(String arg) {
        try{
            if(hashMapManager.getLength() == 0) throw new EmptyCollectionException("Collection is empty");
        } catch (EmptyCollectionException e){
            System.out.println(e.getMessage());
            return false;
        }
        Iterator it = hashMapManager.getHashMap().entrySet().iterator();
        Flat minFlat = null;
        long minArea = Flat.getMAX_AREA();
        while (it.hasNext()){
            HashMap.Entry<Integer, Flat> entry = (HashMap.Entry<Integer,Flat>) it.next();
            Flat currentFlat = entry.getValue();
            if (currentFlat.getArea()<minArea){
                minArea = currentFlat.getArea();
                minFlat = currentFlat;
            }
        }
        try {
            if (minFlat != null) {
                System.out.println(minFlat.toString());
                return true;
            }
            else throw new NullPointerException();
        } catch (NullPointerException e){
            System.out.println("Exception while searching minimum area");
            return false;
        }

    }
}
