package patterns.behavioral.visitor.core.visited;

import patterns.behavioral.visitor.core.visiting.Mechanic;

public class Ship extends VehicleInfo implements Vehicle {

    public Ship(final String title) {
        super(title);
    }

    @Override
    public void bеRepaired(Mechanic mechanic) {
        mechanic.repair(this);
    }

}
