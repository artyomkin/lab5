package commands;

import utility.Console;
/**
 * Interrupt the programm
 * **/
public class ExitCommand extends AbstractCommand{
    private Console console;
    public ExitCommand(Console console){
        super("exit","stop the programm");
        this.console = console;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public boolean execute(String arg) {
        console.turnOff();
        return true;
    }
}
