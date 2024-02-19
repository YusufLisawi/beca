package org.nttdata.threads.racegame;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class Car extends Canvas implements Runnable {
    private int x;
    private Color color;
    private final String name;
    private int speed;
    private int finish;
    private final CountDownLatch startSignal;

    public Car(int x, Color color, String name, int finish, CountDownLatch startSignal) {
        this.x = x;
        this.color = color;
        this.name = name;
        this.finish = finish;
        this.startSignal = startSignal;
        speed = (int)Math.floor(Math.random() * 100);
        setSize(120, 50);
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            while (x < finish) {
                advance(1);
                Thread.sleep(speed);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void advance(int dx) {
        x += dx;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(x, 10, 120, 30, 10, 10);
        g.fillOval(x + 10, 20, 32, 32);
        g.fillOval(x + 75, 20, 32, 32);

        g.setColor(Color.BLACK);
        g.drawString(name, x + 10, 30);
        startSignal.countDown();
    }
}