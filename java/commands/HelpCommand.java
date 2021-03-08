package commands;

/**
 * Prints the information about available commands that command manager contains
 * **/
public class HelpCommand extends AbstractCommand{

    public HelpCommand(){
        super("help","prints the information about available commands");
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    public boolean execute(String arg){
        return true;
    }
}
