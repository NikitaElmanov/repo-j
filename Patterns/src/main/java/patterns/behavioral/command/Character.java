package patterns.behavioral.command;

import patterns.behavioral.command.core.Command;

public class Character {

    private Command goAhead;
    private Command goBack;
    private Command goRight;
    private Command goLeft;

    public Character(final Command goAhead, final Command goBack, final Command goRight, final Command goLeft) {
        this.goAhead = goAhead;
        this.goBack = goBack;
        this.goRight = goRight;
        this.goLeft = goLeft;
    }

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
