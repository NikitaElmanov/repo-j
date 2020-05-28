package test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadMain {
    public static void main(String[] args) {
        Market market = new Market("market", new AtomicLong(100));
        market.start();

        for (int i = 0; i < 20; i++) {
            new Broker("broker - " + (i+1), market).start();
        }
    }
}

class Broker extends Thread {
    private Market market;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + market.getIndex());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Broker(String name, Market market) {
        super(name);
        this.market = market;
        this.setPriority(MAX_PRIORITY);
    }
}

class Market extends Thread {
    private AtomicLong index;

    @Override
    public void run() {
        while (true) {
            index.addAndGet(new Random().nextInt(10));
            System.out.println(Thread.currentThread().getName() + " " + index);
            try {
                Thread.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Market(String name, AtomicLong index) {
        super(name);
        this.index = index;
    }

    public AtomicLong getIndex() {
        return index;
    }
}
