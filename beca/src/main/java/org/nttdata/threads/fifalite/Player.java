package org.nttdata.threads.fifalite;

public class Player extends Thread {
    private final String name;
    private final Ball ball;

    public Player(String name, Ball ball) {
        this.name = name;
        this.ball = ball;
    }

    @Override
    public void run() {
        while (true) {
            ball.ReceiveRelease(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
