package commands;

import exceptions.EmptyCollectionException;
import task_classes.Flat;
import utility.FlatAsker;
import utility.HashMapManager;

import java.util.HashMap;
import java.util.Iterator;
/**
 * Removes all flats that are more than specified one
 * **/
public class RemoveGreaterCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    private FlatAsker flatAsker;
    public RemoveGreaterCommand(HashMapManager hashMapManager, FlatAsker flatAsker){
        super("remove_greater","removes all elements that more than specified");
        this.hashMapManager = hashMapManager;
        this.flatAsker = flatAsker;
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
        Flat flat = flatAsker.askFlat();
        Iterator it = hashMapManager.getHashMap().entrySet().iterator();
        int len = hashMapManager.getLength();
        int cnt = 0;
        while(it.hasNext()){
            if(cnt>len) break;
            it = hashMapManager.getHashMap().entrySet().iterator();
            HashMap.Entry<Integer,Flat> entry = (HashMap.Entry<Integer,Flat>) it.next();
            Flat currentFlat = entry.getValue();
            if (flat.compareTo(currentFlat)<0){
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
