package patterns.behavioral.strategy;

import patterns.behavioral.strategy.core.Eating;
import patterns.behavioral.strategy.core.Rabbit;
import patterns.behavioral.strategy.core.Running;
import patterns.behavioral.strategy.core.Sitting;
import patterns.behavioral.strategy.core.Sleeping;

public class Main {

    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();

        rabbit.setAction(new Sitting());
        rabbit.doSomething();

        rabbit.setAction(new Running());
        rabbit.doSomething();

        rabbit.setAction(new Eating());
        rabbit.doSomething();

        rabbit.setAction(new Sleeping());
        rabbit.doSomething();
    }

}
