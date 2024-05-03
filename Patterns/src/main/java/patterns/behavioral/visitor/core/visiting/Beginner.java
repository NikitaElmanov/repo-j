package patterns.behavioral.visitor.core.visiting;

import patterns.behavioral.visitor.core.visited.VehicleInfo;

public class Beginner implements Mechanic {

    @Override
    public void repair(VehicleInfo vehicle) {
        System.out.println("I'm trying to repair " + vehicle.getTitle());
    }

}
