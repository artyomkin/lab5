package commands;

import exceptions.EmptyCollectionException;
import task_classes.Flat;
import task_classes.Transport;
import utility.FlatAsker;
import utility.HashMapManager;

import java.util.HashMap;
import java.util.Iterator;
/**
 * Prints all elements that have the same transport as specified one has
 * **/
public class FilterByTransportCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    private FlatAsker flatAsker;
    public FilterByTransportCommand(HashMapManager hashMapManager, FlatAsker flatAsker){
        super("filter_by_transport", "print all elements value transport of wich equals to specified one");
        this.hashMapManager = hashMapManager;
        this.flatAsker = flatAsker;
    }

    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public boolean execute(String transportArg) {
        Transport transport;
        try{
            if(transportArg == null) throw new NullPointerException();
            if(hashMapManager.getLength() == 0) throw new EmptyCollectionException("Collection is empty");
            transport = Transport.valueOf(transportArg);
        } catch (EmptyCollectionException e){
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException | IllegalArgumentException e){
            System.out.println("You should specify the correct transport");
            return false;
        }


        Iterator it = hashMapManager.getHashMap().entrySet().iterator();
        while (it.hasNext()){
            HashMap.Entry<Integer, Flat> entry = (HashMap.Entry<Integer, Flat>) it.next();
            Flat currentFlat = entry.getValue();
            if (transport.equals(currentFlat.getTransport())){
                System.out.println(currentFlat.toString());
            }
        }
        return true;
    }
}
