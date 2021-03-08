package commands;

import utility.CommandManager;
/**
 * Prints 13 last executed commands
 * **/
public class HistoryCommand extends AbstractCommand{
    public HistoryCommand(){
        super("history","print 13 last commands without arguments");
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public boolean execute(String arg) {
        return true;
    }
}
