package org.nttdata.threads.fifalite;

import java.util.Stack;

public class Ball {
    private int id;

    public Ball(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public synchronized void ReceiveRelease(Player player) {
        System.out.println(player + " received the ball");
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(player + " released the ball");
    }
}
