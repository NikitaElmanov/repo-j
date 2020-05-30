package patterns.abstractFactory2.website;

import patterns.abstractFactory2.Tester;

public class ManualTester implements Tester {
    @Override
    public void testCode() {
        System.out.println(this.getClass().getSimpleName() + " is testing php code...");
    }
}
