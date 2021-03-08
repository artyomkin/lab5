package utility;

import exceptions.NoCommandManagerFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Controles the modes of input and processes user's input
 * **/
public class Console {
    private CommandManager commandManager;
    private HashMapManager hashMapManager;
    private ConsoleStatus status;
    public Console(HashMapManager hashMapManager){
        this.hashMapManager = hashMapManager;
        this.commandManager = commandManager;
        this.status = ConsoleStatus.OFF;
    }
    /**
     * Sets the console
     * **/
    public void setCommandManager(CommandManager commandManager){
        this.commandManager = commandManager;
    }
    /**
     * Interrupt the loop of input
     * **/
    public void turnOff(){
        status = ConsoleStatus.OFF;
    }
    /**
     * Turn on the mode that allows user to enter the commands interactive in console
     * **/
    public void interactiveMode(Scanner scanner){
        try{
            if (this.hashMapManager.getHashMap()==null) throw new NullPointerException();
            if (this.commandManager == null) throw new NoCommandManagerFoundException("You should specify the command manager in console");
        } catch (NullPointerException e){
            System.out.println("Cannot start interactive mode while collection is null");
            return;
        } catch (NoCommandManagerFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        status = ConsoleStatus.INTERACTIVE;
        while(!status.equals(ConsoleStatus.OFF)){
            System.out.println("Enter your command: ");
            String[] input = {"",""};
            input = (scanner.nextLine().trim()+" ").split(" ",2);
            input[1] = input[1].trim();
            if (commandManager.executeCommand(input[0],input[1])) System.out.println("Command completed successfully");
            else System.out.println("Execution failed");
        }
    }
    /**
     * Start executing the script with specified buffered reader
     * @param bufferedReader
     * **/
    public void scriptMode(BufferedReader bufferedReader, ConsoleStatus previousMode){
        try{
            if (this.hashMapManager.getHashMap()==null) throw new NullPointerException();
            if (this.commandManager == null) throw new NoCommandManagerFoundException("You should specify the command manager in console");
        } catch (NullPointerException e){
            System.out.println("Cannot start interactive mode while collection is null");
            return;
        } catch (NoCommandManagerFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        status = ConsoleStatus.SCRIPT;
        String rawInput;
        try{
            while(!status.equals(ConsoleStatus.OFF) && (rawInput = bufferedReader.readLine())!=null){
                if (rawInput.isEmpty()) continue;
                String[] input = {"",""};
                input = (rawInput.trim()+" ").split(" ",2);
                input[1] = input[1].trim();
                if(commandManager.executeCommand(input[0],input[1])) System.out.println("Command " + input[0] + " completed successfully");
                else System.out.println("Execution of " + input[0] + " failed");
            }
        } catch (IOException e){
            System.out.println("Reading exception. Script execution failed");
            status = previousMode;
            return;
        }

    }
    /**
     * @return true if console is in iteractive mode, else returns false
     * **/
    public ConsoleStatus getStatus(){
        return this.status;
    }

    /**
     * Prints argument if console mode is interactive
     * **/
    public void println(String s){
        if(/*status.equals(ConsoleStatus.INTERACTIVE)*/true) System.out.println(s);
    }
    /**
     * @return true if interactive mode is ective
     * **/
    public boolean isInteractive(){
        return status.equals(ConsoleStatus.INTERACTIVE);
    }
}
