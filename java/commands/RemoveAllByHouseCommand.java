package commands;

import exceptions.EmptyCollectionException;
import task_classes.Flat;
import task_classes.House;
import utility.FlatAsker;
import utility.HashMapManager;

import java.util.HashMap;
import java.util.Iterator;
/**
 * Removes all flats that have the same house as specified one
 * **/
public class RemoveAllByHouseCommand extends AbstractCommand{
    private FlatAsker flatAsker;
    private HashMapManager hashMapManager;
    public RemoveAllByHouseCommand(HashMapManager hashMapManager, FlatAsker flatAsker){
        super("remove_all_by_house","remove all elements house of wich equals to specified one");
        this.flatAsker = flatAsker;
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
        House house = flatAsker.askHouse();
        Iterator it = hashMapManager.getHashMap().entrySet().iterator();
        int len = hashMapManager.getLength();
        int cnt = 0;
        while(it.hasNext()){
            if(cnt > len) break;
            it = hashMapManager.getHashMap().entrySet().iterator();
            HashMap.Entry<Integer, Flat> entry = (HashMap.Entry<Integer,Flat>) it.next();
            Flat currentFlat = entry.getValue();
            if (house.equals(currentFlat.getHouse())){
                try{
                    if (!hashMapManager.updateAfterRemove()) throw new EmptyCollectionException("Collection is already empty");
                    hashMapManager.remove(currentFlat.getId());
                } catch(EmptyCollectionException e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            cnt+=1;
        }
        return true;
    }
}
