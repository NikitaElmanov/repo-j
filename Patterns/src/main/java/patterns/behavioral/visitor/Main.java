package patterns.behavioral.visitor;

import patterns.behavioral.visitor.core.Workshop;
import patterns.behavioral.visitor.core.visiting.Beginner;
import patterns.behavioral.visitor.core.visiting.Skilled;

public class Main {

    public static void main(String[] args) {

        var workshop = new Workshop();

        var beginner = new Beginner();
        var skilled = new Skilled();

        workshop.bеRepaired(beginner);
        workshop.bеRepaired(skilled);

    }

}
