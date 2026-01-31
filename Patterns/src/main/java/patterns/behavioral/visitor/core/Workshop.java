package patterns.behavioral.visitor.core;

import patterns.behavioral.visitor.core.visited.*;
import patterns.behavioral.visitor.core.visiting.Mechanic;

import java.util.List;

public class Workshop implements Vehicle {

    private List<Vehicle> vehicles;

    {
        this.vehicles = List.of(
                new Car("BMW"),
                new Motobike("Harley"),
                new Ship("Titanic")
        );
    }

    @Override
    public void bеRepaired(final Mechanic mechanic) {
        for (Vehicle vehicle : vehicles) {

            if (vehicle instanceof VehicleInfo info) {
                mechanic.repair(info);
            }
        }
    }
}
