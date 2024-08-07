package patterns.structure.facade;

public class FacadeMain {

    public static void main(String[] args) {
        new FacadeMain().test();
    }

    private void test() {
        var dinnerMaker = new DinnerMaker();
        dinnerMaker.makeDish();
    }

    interface Step {

        void execute();
    }

    class CatchAnimal implements Step {

        private String target;

        private CatchAnimal(final String target) {
            this.target = target;
        }

        @Override
        public void execute() {
            System.out.printf("Man catch an animal with target: %s. ", this.target);
        }
    }

    class KillAnimal implements Step {

        @Override
        public void execute() {
            System.out.print("Kill animal before cooking. ");
        }
    }

    class CookMile implements Step {

        private String recipe;

        private CookMile(final String recipe) {
            this.recipe = recipe;
        }

        @Override
        public void execute() {
            System.out.printf("Cooking meal by recipe of: %s. ", this.recipe);
        }
    }

    class DinnerMaker {

        private CatchAnimal catchAnimal;
        private KillAnimal killAnimal;
        private CookMile cookMile;

        private DinnerMaker() {
            this.catchAnimal = new CatchAnimal("cow");
            this.killAnimal = new KillAnimal();
            this.cookMile = new CookMile("chop");
        }

        void makeDish() {
            this.catchAnimal.execute();
            this.killAnimal.execute();
            this.cookMile.execute();
        }
    }

}
