package patterns.behavioral.command.core;

public class GoLeft implements Command {

    @Override
    public void execute() {
        System.out.println("character goes to left");
    }

}
