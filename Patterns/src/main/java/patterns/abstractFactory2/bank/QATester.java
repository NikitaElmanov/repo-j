package patterns.abstractFactory2.bank;

import patterns.abstractFactory2.Tester;

public class QATester implements Tester {
    @Override
    public void testCode() {
        System.out.println(this.getClass().getSimpleName() + " is testing java code...");
    }
}
