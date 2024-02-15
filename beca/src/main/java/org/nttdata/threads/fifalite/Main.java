package org.nttdata.threads.fifalite;

public class Main {
    public static void main(String[] args) {
        Ball ball = new Ball(1);
        Player[] players = new Player[5];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player " + (i + 1), ball);
            players[i].start();
        }
    }
}
