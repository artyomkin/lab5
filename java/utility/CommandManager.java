package utility;

import commands.Executable;
import exceptions.NoSuchCommandException;
import exceptions.RecursionException;

import java.util.*;

/**
 * Stores and manages the commands
 * **/
public class CommandManager {
    private HashMap<String, Executable> commands = new HashMap<String,Executable>();
    private ArrayDeque<Executable> history = new ArrayDeque<Executable>(13);
    private Stack<String> scriptStack;
    public CommandManager(Executable... commands){
        for (Executable c : commands){
            this.commands.put(c.getName(), c);
        }
        scriptStack = new Stack<>();
    }
    /**
     * Add new command to command manager
     * **/
    public void addCommand(Executable command){
        commands.put(command.getName(),command);
    }
    /**
     * Checks if command manager contains command with specified name
     * @return true if command manager contains command with specified name, else returns false
     * @param commandName
     * **/
    public boolean contains(String commandName){
        try{
            if (!commands.containsKey(commandName)) throw new NoSuchCommandException("No such command "+commandName+ " found");
            return true;
        } catch(NoSuchCommandException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * Executes the command with specified name
     * @return exit status of executable command
     * **/
    public boolean executeCommand(String command, String key){
        if(contains(command)){
            history.add(commands.get(command));
            switch(command){
                case "help": return help();
                case "history": return history();
                case "execute_script": {
                    for (String currentFile : scriptStack){
                        if (currentFile.equals(key)){
                            System.out.println("Recursion");
                            return false;
                        }
                    }
                    scriptStack.addElement(key);
                    boolean exitStatus = commands.get(command).execute(key);
                    scriptStack.pop();
                    return exitStatus;
                }
                default: return commands.get(command).execute(key);
            }
        } else{
            return false;
        }
    }
    /**
     * Executes HistoryCommand
     * @return exit status of command
     * **/
    public boolean history(){
        if(contains("history")){
            Iterator it = history.iterator();
            System.out.println("History of 13 last commands: ");
            while(it.hasNext()){
                Executable command = (Executable)it.next();
                System.out.println(command.getName());
            }
            return commands.get("history").execute("");
        }else{
            return false;
        }
    }
    /**
     * Executes HelpCommand
     * @return exit status of command
     * **/
    public boolean help(){
        if(contains("help")){
            Iterator it = commands.entrySet().iterator();
            while(it.hasNext()){
                do {
                    HashMap.Entry<String, Executable> entry = (HashMap.Entry) it.next();
                    System.out.println(entry.getKey() + " - " + entry.getValue().getDescription());
                } while(it.hasNext());
            }
            return commands.get("help").execute("");

        } else {
            return false;
        }
    }

}
