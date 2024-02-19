package org.nttdata.threads.racegame;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class App extends JFrame {
    public Race race;

    public App(String title, int width, int height, int numCars) throws HeadlessException {
        super(title);
        setSize(width, height);

        race = new Race(this.getWidth(), numCars);
        add(race);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        App app = new App("Race Game", 600, 800, 7);
        try {
            Thread.sleep(1000);
            app.race.startRace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}