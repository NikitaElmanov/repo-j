package patterns.behavioral.visitor2.animal;

import patterns.behavioral.visitor2.AnimalVisitor;

public class Monkey implements Animal {

    @Override
    public void accept(AnimalVisitor visitor) {
        visitor.visitMonkey();
    }
}
