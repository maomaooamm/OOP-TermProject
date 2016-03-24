package controller;

import java.awt.geom.Rectangle2D;
import model.GameFigure;

public class Animator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 20;

    @Override
    public void run() {

        while (running) {
            long startTime = System.currentTimeMillis();
            
            processCollisions();

            Main.gameData.update();
            Main.gamePanel.gameRender();
            Main.gamePanel.printScreen();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // ms
                } catch (InterruptedException e) {

                }
            }
        }
        System.exit(0);
    }
    
    private void processCollisions() {
        for (int m = 0; m < Main.gameData.friendFigures.size(); m++){
            Rectangle2D friendBox = ((GameFigure)Main.gameData
                    .friendFigures.get(m)).getCollisionBox();
            for (int i = 0; i < Main.gameData.enemyFigures.size(); i++){
                GameFigure enemy = (GameFigure)Main.gameData.enemyFigures.get(i);
                if (friendBox.intersects(enemy.getCollisionBox())) {
                    enemy.state = 2;
                }
            }
        }
    }

}
