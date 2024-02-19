package org.nttdata.threads.racegame;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Race extends Panel {
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Thread> threads = new ArrayList<>();

    public Race(int width, int numCars) {
        this.setLayout(new GridLayout(numCars, 1));

        // list of colors
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.CYAN, Color.LIGHT_GRAY, Color.DARK_GRAY};
        
        CountDownLatch startSignal = new CountDownLatch(numCars);

        for (int i = 0; i < numCars; i++) {
            addCar(new Car(0, colors[i], "Car " + (i + 1), width - 140, startSignal));
        }

        this.validate();
    }

    public void startRace() {
        threads.forEach(Thread::start);
    }

    private void addCar(Car car) {
        cars.add(car);
        this.add(car);
        Thread thread = new Thread(car);
        threads.add(thread);
    }
}