package org.nttdata.threads.racegame;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App(String title, int width, int height, int numCars) throws HeadlessException {
        super(title);
        setSize(width, height);

        add(new Race(this.getWidth(), numCars));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new App("Race Game", 600, 800, 7);
    }
}
