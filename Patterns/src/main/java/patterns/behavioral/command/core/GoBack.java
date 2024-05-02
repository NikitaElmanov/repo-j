package patterns.behavioral.command.core;

public class GoBack implements Command {

    @Override
    public void execute() {
        System.out.println("character goes to back...");
    }

}
