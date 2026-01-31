package patterns.behavioral.visitor2;

import patterns.behavioral.visitor2.animal.Dolphin;
import patterns.behavioral.visitor2.animal.Lion;
import patterns.behavioral.visitor2.animal.Monkey;

public class Main {
    public static void main(String[] args) {
        Monkey monkey = new Monkey();
        Dolphin dolphin = new Dolphin();
        Lion lion = new Lion();

        Person person = new Person();

        monkey.accept(person);
        lion.accept(person);
        dolphin.accept(person);
    }
}
