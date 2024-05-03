package patterns.behavioral.visitor;

import patterns.behavioral.visitor.core.Workshop;
import patterns.behavioral.visitor.core.visiting.Beginner;
import patterns.behavioral.visitor.core.visiting.Mechanic;
import patterns.behavioral.visitor.core.visiting.Skilled;

public class Main {

    public static void main(String[] args) {

        Workshop workshop = new Workshop();

        Mechanic beginner = new Beginner();
        Mechanic skilled = new Skilled();

        workshop.bеRepaired(beginner);
        workshop.bеRepaired(skilled);

    }

}
