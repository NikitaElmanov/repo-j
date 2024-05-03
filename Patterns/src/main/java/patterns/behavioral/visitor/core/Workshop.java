package patterns.behavioral.visitor.core;

import java.util.List;
import patterns.behavioral.visitor.core.visited.Car;
import patterns.behavioral.visitor.core.visited.Motobike;
import patterns.behavioral.visitor.core.visited.Ship;
import patterns.behavioral.visitor.core.visited.Vehicle;
import patterns.behavioral.visitor.core.visited.VehicleInfo;
import patterns.behavioral.visitor.core.visiting.Mechanic;

public class Workshop implements Vehicle {

    List<Vehicle> vehicles;

    public Workshop() {
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
