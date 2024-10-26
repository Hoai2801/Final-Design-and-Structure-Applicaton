package org.example.adapter.command;

public class CommandProcessor {
    private static CommandProcessor commandProcessor;

    private CommandProcessor() {
    }

    public static CommandProcessor makeCommandProcessor() {
        if (commandProcessor == null) {
            commandProcessor = new CommandProcessor();
        }
        return commandProcessor;
    }
    
    public void execute(Command command) {
        command.execute();
    }
}
