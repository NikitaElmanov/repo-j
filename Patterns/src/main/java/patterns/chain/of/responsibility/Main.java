package patterns.chain.of.responsibility;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

public class Main {
    public static void main(String[] args) {
        OrderAttendant orderFinalizer = new OrderFinalizer(null);
        OrderAttendant orderDelivery = new OrderDelivery(orderFinalizer);
        OrderAttendant orderMaker = new OrderMaker(orderDelivery);

        Order order = new Order(1L, "title-1", "default description");

        orderMaker.launchServing(order);
    }
}

record Order(Long id, String title, String description) {
}

@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
abstract class OrderAttendant {

    OrderAttendant nextAttendant;

    public void launchServing(Order order) {
        this.serve(order);

        if (Objects.nonNull(nextAttendant)) {
            nextAttendant.launchServing(order);
        }
    }

    protected abstract void serve(Order order);
}

class OrderMaker extends OrderAttendant {

    public OrderMaker(OrderAttendant nextAttendant) {
        super(nextAttendant);
    }

    @Override
    protected void serve(Order order) {
        System.out.println("Made order. It will be done soon), " + order.id());
    }
}

class OrderDelivery extends OrderAttendant {

    public OrderDelivery(OrderAttendant nextAttendant) {
        super(nextAttendant);
    }

    @Override
    protected void serve(Order order) {
        System.out.println("Delivering order. It will be in pickPoint so soon), " + order.title());
    }
}

class OrderFinalizer extends OrderAttendant {

    public OrderFinalizer(OrderAttendant nextAttendant) {
        super(nextAttendant);
    }

    @Override
    protected void serve(Order order) {
        System.out.println("Order was finished. How do you like it?), " + order.description());
    }
}

