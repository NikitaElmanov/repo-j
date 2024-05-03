package patterns.behavioral.visitor.core.visited;

import patterns.behavioral.visitor.core.visiting.Mechanic;

public class Motobike extends VehicleInfo implements Vehicle {

    public Motobike(final String title) {
        super(title);
    }

    @Override
    public void bеRepaired(Mechanic mechanic) {
        mechanic.repair(this);
    }

}
