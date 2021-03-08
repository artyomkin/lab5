package commands;

import utility.HashMapManager;
/**
 * Clear whole collection by removing all elements
 * **/
public class ClearCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    public ClearCommand(HashMapManager hashMapManager){
        super("clear", "clear the collection");
        this.hashMapManager = hashMapManager;
    }
    /**
     *  Executes the command
     * @return exit status of command
     * **/
    @Override
    public boolean execute(String arg) {
        try{
            hashMapManager.clear();
            hashMapManager.updateAfterClear();
            return true;
        } catch (Exception e){
            System.out.println("Error");
            return false;
        }
    }
}
