package patterns.behavioral.strategy.core;

public class Sitting implements Action {

    @Override
    public void doSomething() {
        System.out.println("Sitting...");
    }

}
