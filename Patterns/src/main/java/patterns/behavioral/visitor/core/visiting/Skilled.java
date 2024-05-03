package patterns.behavioral.visitor.core.visiting;

import patterns.behavioral.visitor.core.visited.VehicleInfo;

public class Skilled implements Mechanic {

    @Override
    public void repair(VehicleInfo vehicle) {
        System.out.println(
                "I'm checking job after beginner mechanic and fixing his mistakes with " + vehicle.getTitle());
    }

}
