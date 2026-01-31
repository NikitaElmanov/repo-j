package patterns.behavioral.observer.core;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Subscriber implements Observer {

    private final String name;

    @Override
    public void handleEvent(final List<String> videos) {
        System.out.println(
                "Dear " + this.name + " we are glad to say that videos list is changed!!!\n New video list looks like: "
                        + videos + "\n++++++++++++++++++++++++++++++++++++++++++++\n");
    }
}
