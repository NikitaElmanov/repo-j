package patterns.factoryMethod2;

public class Main {
    public static void main(String[] args) {
        AbstractJob one = Fabric.createJob("one");
        AbstractJob two = Fabric.createJob("two");

        one.execute();
        two.execute();
    }
}

interface AbstractJob {
    void execute();
}

class Job1 implements AbstractJob{

    @Override
    public void execute() {
        System.out.println("Job1 run executing...");
    }
}

class Job2 implements AbstractJob{

    @Override
    public void execute() {
        System.out.println("Job2 run executing...");
    }
}

enum Types {
    ONE, TWO
}

class Fabric {
    static AbstractJob createJob(String type) {
        Types mask = Types.valueOf(type.toUpperCase());
        switch (mask) {
            case ONE:
                return new Job1();
            case TWO:
                return new Job2();
            default:
                return null;
        }
    }
}