package patterns.behavioral.visitor.core.visiting;

import patterns.behavioral.visitor.core.visited.Vehicle;
import patterns.behavioral.visitor.core.visited.VehicleInfo;

public interface Mechanic {

    void repair(VehicleInfo vehicle);

}
