package patterns.behavioral.strategy.core;

public class Sleeping implements Action {

    @Override
    public void doSomething() {
        System.out.println("Sleeping...");
    }

}
