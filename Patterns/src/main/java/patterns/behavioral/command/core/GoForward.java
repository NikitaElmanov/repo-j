package patterns.behavioral.command.core;

public class GoForward implements Command {

    @Override
    public void execute() {
        System.out.println("character goes forward...");
    }

}
