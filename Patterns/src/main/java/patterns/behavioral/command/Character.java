package patterns.behavioral.command;

import patterns.behavioral.command.core.Command;

public record Character(Command goAhead, Command goBack, Command goRight, Command goLeft) {

    public void ahead() {
        goAhead.execute();
    }

    public void back() {
        goBack.execute();
    }

    public void right() {
        goRight.execute();
    }

    public void left() {
        goLeft.execute();
    }
}
