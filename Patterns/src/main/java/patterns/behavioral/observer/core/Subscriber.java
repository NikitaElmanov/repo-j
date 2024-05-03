package patterns.behavioral.observer.core;

import java.util.List;

public class Subscriber implements Observer {

    private String name;

    public Subscriber(final String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(final List<String> videos) {
        System.out.println(
                "Dear " + this.name + " we are glad to say that videos list is changed!!!\n New video list looks like: "
                        + videos + "\n++++++++++++++++++++++++++++++++++++++++++++\n");
    }

}
