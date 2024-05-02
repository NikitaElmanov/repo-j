package patterns.behavioral.command;

import patterns.behavioral.command.core.GoForward;
import patterns.behavioral.command.core.GoBack;
import patterns.behavioral.command.core.GoLeft;
import patterns.behavioral.command.core.GoRight;

public class Main {

    public static void main(String[] args) {
        Character character = new Character(
                new GoForward(),
                new GoBack(),
                new GoRight(),
                new GoLeft()
        );

        character.ahead();
        character.back();
        character.left();
        character.right();
    }

}
