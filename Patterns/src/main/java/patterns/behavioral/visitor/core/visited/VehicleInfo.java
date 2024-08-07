package patterns.behavioral.visitor.core.visited;

import lombok.Getter;

@Getter
public abstract class VehicleInfo {

    private String title;

    protected VehicleInfo(final String title) {
        this.title = title;
    }

}
