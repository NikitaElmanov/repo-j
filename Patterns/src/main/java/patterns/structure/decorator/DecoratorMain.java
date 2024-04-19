package patterns.structure.decorator;

public class DecoratorMain {

    public static void main(String[] args) {
        new DecoratorMain().test();
    }

    private void test() {
        Person person = new Strongman(new Average(new Weakling()));
        person.doSomething();
    }

    interface Person {

        void doSomething();
    }

    class Weakling implements Person {

        @Override
        public void doSomething() {
            System.out.print("lift 5 kg sport disk. ");
        }
    }

    class PersonDecorator implements Person {

        private Person person;

        private PersonDecorator(final Person person) {
            this.person = person;
        }

        @Override
        public void doSomething() {
            person.doSomething();
        }
    }

    class Average extends PersonDecorator {

        private Average(final Person person) {
            super(person);
        }

        void makeSomeMove() {
            System.out.print("made 15 pull ups. ");
        }

        @Override
        public void doSomething() {
            super.doSomething();
            this.makeSomeMove();
        }
    }

    class Strongman extends PersonDecorator {

        private Strongman(final Person person) {
            super(person);
        }

        void makeSomeMove() {
            System.out.print("lift the whole cow. ");
        }

        @Override
        public void doSomething() {
            super.doSomething();
            this.makeSomeMove();
        }
    }

}
