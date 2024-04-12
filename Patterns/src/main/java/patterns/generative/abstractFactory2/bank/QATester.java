package patterns.generative.abstractFactory2.bank;

import patterns.generative.abstractFactory2.Tester;

public class QATester implements Tester {
    @Override
    public void testCode() {
        System.out.println(this.getClass().getSimpleName() + " is testing java code...");
    }
}
