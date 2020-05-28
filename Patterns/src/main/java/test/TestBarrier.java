package test;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestBarrier {
    public static void main(String[] args) {
        Auction auction = new Auction();
        int startPrice = new Random().nextInt(100);
        for (int i = 0; i < auction.BIDS_SIZE; i++) {
            Bid bid = new Bid((i+1), startPrice, auction.getCyclicBarrier());
            auction.setBids(bid);
            bid.start();
        }
    }
}

class Auction {
    private List<Bid> bids;
    private CyclicBarrier cyclicBarrier;

    public static final int BIDS_SIZE = 3;

    public Auction() {
        bids = new ArrayList<>();
        cyclicBarrier = new CyclicBarrier(BIDS_SIZE, () -> {
            Bid winner = getWinner();
            System.out.println("man with bidId = " + winner.getBidId() + " and price = " + winner.getPrice() + " wins");
        });
    }

    public Bid getWinner() {
        return Collections.max(bids, Comparator.comparingInt(Bid::getPrice));
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public void setBids(Bid bid) {
        this.bids.add(bid);
    }
}

class Bid extends Thread {
    private int bidId;
    private int price;
    private CyclicBarrier cyclicBarrier;

    @Override
    public void run() {
        System.out.println("client with bidId = " + this.bidId + " are thinking about price...");
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int delta = new Random().nextInt(50);
        price += delta;
        System.out.println("Bid " + this.bidId + " : " + price);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("client with bidId = " + bidId + " finished");
    }

    public Bid(int bidId, int price, CyclicBarrier cyclicBarrier) {
        this.bidId = bidId;
        this.price = price;
        this.cyclicBarrier = cyclicBarrier;
    }

    public int getBidId() {
        return bidId;
    }

    public int getPrice() {
        return price;
    }
}