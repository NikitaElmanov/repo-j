package patterns.behavioral.visitor2;

public class Person implements AnimalVisitor {
    @Override
    public void visitLion() {
        System.out.println("visit Lion");
    }

    @Override
    public void visitMonkey() {
        System.out.println("visit Monkey");
    }

    @Override
    public void visitDolphin() {
        System.out.println("visit Dolphin");
    }
}
