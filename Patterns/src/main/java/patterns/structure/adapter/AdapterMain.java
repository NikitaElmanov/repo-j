package patterns.structure.adapter;

import lombok.AllArgsConstructor;

public class AdapterMain {

    public static void main(String[] args) {
        new AdapterMain().test();
    }

    private void test() {
        Judge judge = new Judge();
        judge.sendToPrison(new GuyWrapper(new Steelmaker()));
    }


    interface BadGuy {

        void beSentToPrison();
    }

    class Maniac implements BadGuy {

        @Override
        public void beSentToPrison() {
            System.out.println("i'n maniac was sent to prizon");
        }

    }

    interface GoodGuy {

        void beSentToPrisonByMistake();
    }

    class Steelmaker implements GoodGuy {

        @Override
        public void beSentToPrisonByMistake() {
            System.out.println("i'm steelmaker was sent to prison by mistake");
        }

    }

    class Judge {

        public void sendToPrison(BadGuy badGuy) {
            badGuy.beSentToPrison();
        }
    }

    @AllArgsConstructor
    class GuyWrapper implements BadGuy {

        private GoodGuy goodGuy;

        @Override
        public void beSentToPrison() {
            goodGuy.beSentToPrisonByMistake();
        }

    }

}
