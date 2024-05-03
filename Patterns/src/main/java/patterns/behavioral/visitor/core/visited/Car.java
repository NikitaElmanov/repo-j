package patterns.behavioral.visitor.core.visited;

import patterns.behavioral.visitor.core.visiting.Mechanic;

public class Car extends VehicleInfo implements Vehicle {

    public Car(final String title) {
        super(title);
    }

    @Override
    public void bеRepaired(final Mechanic mechanic) {
        mechanic.repair(this);
    }

}
