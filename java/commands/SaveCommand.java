package commands;

import utility.HashMapManager;
/**
 * Saves the collection to file
 * **/
public class SaveCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    private String filePath;
    public SaveCommand(HashMapManager hashMapManager, String filePath){
        super("save","save collection to file");
        this.hashMapManager = hashMapManager;
        this.filePath = filePath;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    public boolean execute(String arg){
        return hashMapManager.save(filePath);
    }
}
