package ru.rzn.sbr.javaschool.lesson10.balls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.Timer;

import static java.lang.System.exit;

/**
 * 1. Измените метода {@link Solution#main(String[])} таким образом, чтобы вместо явного создания потоков использовался
 * какой-нибудь {@link java.util.concurrent.Executor}.
 * 2. Реализуйте последовательую "заморозку" потоков при попадании {@link Ball} на диагональ {@link BallWorld}
 * (где x == y). Попаданием считать пересечение описывающего прямоуголькника диагонали. При заморозке всех потоков
 * осуществляйте возобновление выполнения
 * 3. Введите в программу дополнительный поток, который уничтожает {@link Ball} в случайные моменты времени.
 * Начните выполнение этого потока c задержкой в 15 секунд после старта всех {@link Ball}. {@link Ball} должны
 * уничтожаться в случайном порядке. Под уничтожением {@link Ball} подразумевается
 * а) исключение из массива {@link BallWorld#balls} (нужно реализовать потокобезопасный вариант)
 * б) завершение потока, в котором выполняется соответствующая задача (следует использовать
 * {@link java.util.concurrent.Future}сформированный при запуске потока для прерывания
 * {@link java.util.concurrent.Future#cancel(boolean)})
 */
public class Solution {

    public static final Logger log = LogManager.getLogger(Solution.class);

    private static List<Future<String>> listOfFuture = new ArrayList<>();

    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void nap(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.println("Thread " + Thread.currentThread().getName() + " throwed exception " + e.getMessage());
        }
    }

    public static void main(String[] a) throws ExecutionException, InterruptedException {

        final BallWorld world = new BallWorld();
        final JFrame win = new JFrame();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                win.getContentPane().add(world);
                win.pack();
                win.setVisible(true);
            }
        });

        Thread.currentThread().setName("MyMainThread");

        nap((int) (5000 * Math.random()));
        Future<String> f1 = (Future<String>) service.submit(new Ball(world, 50, 80, 5, 10, Color.red));
        nap((int) (5000 * Math.random()));
        Future<String> f2 = (Future<String>)service.submit(new Ball(world, 70, 100, 8, 6, Color.blue));
        nap((int) (5000 * Math.random()));
        Future<String> f3 = (Future<String>)service.submit(new Ball(world, 150, 100, 9, 7, Color.green));
        nap((int) (5000 * Math.random()));
        Future<String> f4 = (Future<String>)service.submit(new Ball(world, 200, 130, 3, 8, Color.black));
        nap((int) (5000 * Math.random()));

        listOfFuture.add(f1);
        listOfFuture.add(f2);
        listOfFuture.add(f3);
        listOfFuture.add(f4);

        int time = (int) (Math.floor(Math.random() * (3000 + 1000 - 1)) + 1000);

        Timer timer = new Timer(time, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Solution.removeRandomBall(world);
            }
        });
        timer.setRepeats(true);
        timer.start();
        log.info("Start timer!");
    }

    public static void removeRandomBall(BallWorld world){
        ScheduledExecutorService executor
                = Executors.newSingleThreadScheduledExecutor();

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                int num = (int) (Math.floor(Math.random() * ((listOfFuture.size()) - 0 + 1)) + 0);

                world.getBalls().remove(num);
                log.info("Removed: " + String.valueOf(num) + " Ball");

                listOfFuture.get(num).cancel(false);

                log.info("Size of ListOfBalls : " + world.getBalls().size());


                listOfFuture.remove(num);
                log.info("Future is removed");

                log.info("Size of listOfFuture : " + listOfFuture.size());

                if (listOfFuture.isEmpty()){
                    log.info("End");
                    exit(0);
                }

            }
        }, 5, TimeUnit.SECONDS);
    }
}
