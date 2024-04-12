package patterns.generative.factoryMethod3;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Client client = new Client();
        client.make(FabricOne.class);
        client.make(FabricTwo.class);

        client.create(new FabricOne());
        client.create(new FabricTwo());
    }
}

interface AbstractJob {
    void perform();
}

class Job1 implements AbstractJob{

    @Override
    public void perform() {
        System.out.println("Job1 is rinning...");
    }
}

class Job2 implements AbstractJob{

    @Override
    public void perform() {
        System.out.println("Job2 is rinning...");
    }
}

abstract class AbstractFabric <T extends AbstractJob> {
    abstract T createJob();
}

class FabricOne extends AbstractFabric<Job1> {

    @Override
    Job1 createJob() {
        return new Job1();
    }
}

class FabricTwo extends AbstractFabric<Job2> {

    @Override
    Job2 createJob() {
        return new Job2();
    }
}

class Client {
    void make(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        AbstractFabric<AbstractJob> af = (AbstractFabric<AbstractJob>) clazz.newInstance();
        AbstractJob aj = af.createJob();
        aj.perform();
    }

    void create(AbstractFabric abstractFabric) {
        AbstractJob aj = abstractFabric.createJob();
        aj.perform();
    }
}