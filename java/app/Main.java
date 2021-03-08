package app;

import commands.*;
import utility.*;

import java.util.Scanner;

public class Main {
    public static void main(String... args){
        String filePath = "C:\\Users\\User\\Desktop\\input.json";

        FileManager fileManager = new FileManager(filePath);
        HashMapManager hashMapManager = new HashMapManager(fileManager);
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(hashMapManager);
        FlatAsker flatAsker = new FlatAsker(scanner, console);
        CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(hashMapManager),
                new ShowCommand(hashMapManager),
                new InsertCommand(hashMapManager, flatAsker),
                new UpdateCommand(hashMapManager, flatAsker),
                new RemoveKeyCommand(hashMapManager),
                new ClearCommand(hashMapManager),
                new SaveCommand(hashMapManager, filePath),
                new RemoveGreaterCommand(hashMapManager,flatAsker),
                new ReplaceIfLowerCommand(hashMapManager,flatAsker),
                new RemoveAllByHouseCommand(hashMapManager,flatAsker),
                new MinByAreaCommand(hashMapManager),
                new FilterByTransportCommand(hashMapManager,flatAsker),
                new HistoryCommand(),
                new ExitCommand(console),
                new ExecuteScriptCommand(console, flatAsker)
        );
        console.setCommandManager(commandManager);
        console.interactiveMode(scanner);

    }
}
