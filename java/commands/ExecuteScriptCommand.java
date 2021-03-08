package commands;

import utility.Console;
import utility.FlatAsker;

import java.io.*;

/**
 * Execute the script from file
 * **/
public class ExecuteScriptCommand extends AbstractCommand{
    private Console console;
    private FlatAsker flatAsker;

    public ExecuteScriptCommand(Console console, FlatAsker flatAsker){
        super("execute_script","read and execute script from specified file. " +
                "Script contains same commands as user uses in interactive mode");
        this.console = console;
        this.flatAsker = flatAsker;
    }
    /**
     * Executes the command
     * @param filePath
     * **/
    @Override
    public boolean execute(String filePath) {
        try{
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            flatAsker.setFileReader(bufferedReader);
            console.scriptMode(bufferedReader, console.getStatus());
            return true;
        } catch (FileNotFoundException e){
            System.out.println("No such file found");
            return false;
        }

    }
}
