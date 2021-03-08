package commands;

import exceptions.*;
import task_classes.Flat;
import utility.FlatAsker;
import utility.HashMapManager;
/**
 * Inserts new element with specified key into collection
 * **/
public class InsertCommand extends AbstractCommand{

    private HashMapManager hashMapManager;
    private FlatAsker flatAsker;
    public InsertCommand(HashMapManager hashMapManager, FlatAsker flatAsker){
        super("insert", "insert new element with specified key");
        this.hashMapManager = hashMapManager;
        this.flatAsker = flatAsker;
    }
    /**
     * Executes the command
     * @return exit status of command
     * @param key that will be used to perform the insert
     * **/
    @Override
    public boolean execute(String key){
        try{
            if(key.isEmpty()) throw new EmptyStringException("You should specify the key");
            Integer resultKey = Integer.parseInt(key);
            if(hashMapManager.getHashMap().keySet().contains(resultKey)) throw new KeyAlreadyExistsException("Element with specified key already exists");
            Flat flat = flatAsker.askFlat();
            if(flat == null) throw new NullPointerException();
            hashMapManager.insert(resultKey,flat);
            return true;
        }catch (EmptyStringException | KeyAlreadyExistsException e){
            System.out.println(e.getMessage());
            return false;
        }catch (NumberFormatException e){
            System.out.println("Key must be a number");
            return false;
        }catch (NullPointerException e){
            System.out.println("You should specify the flat");
            return false;
        }

    }
}
