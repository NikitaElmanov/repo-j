package ru.rzn.sbr.javaschool.lesson10.cars;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class TrafficController {

    public static final Logger log = LogManager.getLogger(TrafficController.class);

    Semaphore sem = new Semaphore(1);

    public TrafficController() {
    }

    public void enterRight() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void leaveLeft() {
        sem.release();
    }

    public void leaveRight() {
        sem.release();
    }

    public void enterLeft() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}