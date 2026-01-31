package patterns.behavioral.strategy.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rabbit implements Action {

    private Action action;

    @Override
    public void doSomething() {
        action.doSomething();
    }
}
