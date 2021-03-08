package commands;

import utility.HashMapManager;

import java.util.HashMap;
import java.util.Iterator;
/**
 * Prints all elements in collection
 * **/
public class ShowCommand extends AbstractCommand{
    private HashMapManager hashMapManager;
    public ShowCommand(HashMapManager hashMapManager){
        super("show","prints all elements");
        this.hashMapManager = hashMapManager;
    }

    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public boolean execute(String arg) {
        try{
            if (hashMapManager.getLength() == 0){
                return true;
            }
            Iterator it = hashMapManager.getHashMap().entrySet().iterator();
            do {
                HashMap.Entry entry = (HashMap.Entry) it.next();
                System.out.println(entry.getValue().toString() + "\n\n");
            } while(it.hasNext());
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
