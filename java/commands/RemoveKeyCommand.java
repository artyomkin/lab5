package commands;

import exceptions.EmptyCollectionException;
import exceptions.EmptyStringException;
import utility.HashMapManager;
/**
 * Removes the flat from collections by its key
 * **/
public class RemoveKeyCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    public RemoveKeyCommand(HashMapManager hashMapManager){
        super("remove_key","remove the element from collection by its key");
        this.hashMapManager = hashMapManager;
    }
    /**
     * Executes the command
     * @return exit status of command
     * @param key that will be used to perform the remove
     * **/
    @Override
    public boolean execute(String key) {
        try{
            if(key.isEmpty()) throw new EmptyStringException("You should specify the key");
            Integer resultKey = Integer.parseInt(key);
            if (!hashMapManager.updateAfterRemove()) throw new EmptyCollectionException("Collection is already empty");
            return hashMapManager.remove(resultKey);
        } catch (NumberFormatException e){
            System.out.println("Key must be a number");
            return false;
        } catch(EmptyCollectionException | EmptyStringException e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
