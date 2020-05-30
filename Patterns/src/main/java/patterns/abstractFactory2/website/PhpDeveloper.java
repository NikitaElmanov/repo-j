package patterns.abstractFactory2.website;

import patterns.abstractFactory2.Developer;

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println(this.getClass().getSimpleName() + " is writing php code...");
    }
}
