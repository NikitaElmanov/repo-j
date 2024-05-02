package patterns.behavioral.command.core;

public class GoRight implements Command {

    @Override
    public void execute() {
        System.out.println("character goes to right");
    }

}
