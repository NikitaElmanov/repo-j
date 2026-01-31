package patterns.behavioral.visitor2.animal;

import patterns.behavioral.visitor2.AnimalVisitor;

public class Dolphin implements Animal {

    @Override
    public void accept(AnimalVisitor visitor) {
        visitor.visitDolphin();
    }
}
