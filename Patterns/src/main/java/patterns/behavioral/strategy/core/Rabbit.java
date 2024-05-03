package patterns.behavioral.strategy.core;

import lombok.Setter;

@Setter
public class Rabbit implements Action {

    private Action action;

    @Override
    public void doSomething() {
        action.doSomething();
    }

}
