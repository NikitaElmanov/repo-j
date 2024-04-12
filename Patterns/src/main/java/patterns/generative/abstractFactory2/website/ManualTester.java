package patterns.generative.abstractFactory2.website;

import patterns.generative.abstractFactory2.Tester;

public class ManualTester implements Tester {
    @Override
    public void testCode() {
        System.out.println(this.getClass().getSimpleName() + " is testing php code...");
    }
}
