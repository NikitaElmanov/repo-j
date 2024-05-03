package patterns.behavioral.visitor.core.visited;

import lombok.Getter;

@Getter
public abstract class VehicleInfo {

    private String title;

    public VehicleInfo(final String title) {
        this.title = title;
    }

}
