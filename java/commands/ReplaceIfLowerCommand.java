package commands;

import exceptions.EmptyCollectionException;
import exceptions.EmptyStringException;
import exceptions.IncorrectInputException;
import task_classes.Flat;
import utility.FlatAsker;
import utility.HashMapManager;
/**
 * Replaces one flat with another if first flat is more than specified
 * **/
public class ReplaceIfLowerCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    private FlatAsker flatAsker;
    public ReplaceIfLowerCommand(HashMapManager hashMapManager,FlatAsker flatAsker){
        super("replace_if_lower","replace element by key if new element is less than old one");
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
            if(arg.isEmpty()) throw new EmptyStringException("You should specify the key");
        } catch (EmptyCollectionException | EmptyStringException e){
            System.out.println(e.getMessage());
            return false;
        }
        Integer key;
        try{
            key = Integer.parseInt(arg);
            Flat oldFlat = hashMapManager.getElementByKey(key);
            if(oldFlat == null) throw new IncorrectInputException("No flat with such key");
            Flat newFlat = flatAsker.askFlat();
            if (newFlat.compareTo(oldFlat)<0){
                return hashMapManager.replace(oldFlat,newFlat);
            } else {
                return false;
            }
        } catch (NumberFormatException e){
            System.out.println("Key must be a number");
            e.printStackTrace();
            return false;
        } catch (IncorrectInputException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
