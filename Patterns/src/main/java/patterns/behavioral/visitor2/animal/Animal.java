package patterns.behavioral.visitor2.animal;

import patterns.behavioral.visitor2.AnimalVisitor;

public interface Animal {
    void accept(AnimalVisitor visitor);
}
