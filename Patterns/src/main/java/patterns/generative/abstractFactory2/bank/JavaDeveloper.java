package patterns.generative.abstractFactory2.bank;

import patterns.generative.abstractFactory2.Developer;

public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println(this.getClass().getSimpleName() + " is writing code...");
    }
}
