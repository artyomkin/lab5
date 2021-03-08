package commands;

import utility.HashMapManager;
/**
 * Prints the information about the collection
 * **/
public class InfoCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    public InfoCommand(HashMapManager hashMapManager){
        super("info", "prints the information about the collection");
        this.hashMapManager = hashMapManager;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public boolean execute(String arg) {
        System.out.println("Information about the collection: ");
        try{
            System.out.println("Type: " + hashMapManager.getHashMap().getClass().toString());
            System.out.println("Created: " + hashMapManager.getInitializationDate());
            System.out.println("Number of elements: " + hashMapManager.getLength());
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }
}
