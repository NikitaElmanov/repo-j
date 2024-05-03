package patterns.behavioral.strategy.core;

public class Running implements Action {

    @Override
    public void doSomething() {
        System.out.println("Running...");
    }

}
