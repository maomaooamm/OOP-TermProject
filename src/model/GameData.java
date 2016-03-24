package model;

import controller.Main;
import view.GamePanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import view.MainWindow;

public class GameData {

    private final int RADIUS = 6;
    public final List<GameFigure> enemyFigures;
    public final List<GameFigure> friendFigures;
    public static Shooter shooter;
    private int totalBomb = 0;
    private int killedBomb = 0;
    private int totalUFO = 0;
    private int killedUFO = 0;
    private int totalBird = 0;
    private int killedBird = 0;
    private int totalFlash = 0;
    private int killedFlash = 0;

    public GameData() {
        enemyFigures = Collections.synchronizedList(
                new ArrayList<GameFigure>() );
        friendFigures = Collections.synchronizedList(
                new ArrayList<GameFigure>() );

        shooter = new Shooter(Main.WIN_WIDTH / 2 - 10, Main.WIN_HEIGHT / 2 + 75);

        friendFigures.add(shooter);
    }

    public void add(int n) {
        totalBomb += n;
        synchronized (enemyFigures) {
            for (int i = 0; i < n; i++) {
                float red = (float) Math.random();
                float green = (float) Math.random();
                float blue = (float) Math.random();
                // adjust if too dark since the background is black
                if (red < 0.5) {
                    red += 0.3;
                }
                if (green < 0.5) {
                    green += 0.3;
                }
                if (blue < 0.5) {
                    blue += 0.3;
                }
                enemyFigures.add(new Bomb(
                        (int) (Math.random() * GamePanel.width),
                        (int) (Math.random() * GamePanel.height),
                        RADIUS,
                        new Color(red, green, blue)));
            }
        }
    }
    
    public void addUFO() {
        totalUFO += 1;
        enemyFigures.add(new FlyingSaucer(
                (float)(Math.random() * GamePanel.width), 
                (float)(Math.random() * GamePanel.height)));
    }
    
    public void addBird() {
        totalBird += 2;
        for(int i = 0; i < 2; i++){
        enemyFigures.add(new Bird(
                (float)(Math.random() * GamePanel.width), 
                (float)(Math.random() * GamePanel.height)));
        }
    }
    
    public void addFlash() {
        int n = (int)(Math.random()*10);
        totalFlash += n;
        synchronized (enemyFigures) {
            for (int i = 0; i < n; i++) {
                enemyFigures.add(new Flash(
                        (int) (Math.random() * GamePanel.width),
                        (int) (Math.random() * GamePanel.height)));
            }
        }
    }
    
    public void update() {
        
        synchronized (enemyFigures) {
            ArrayList<GameFigure> remove = new ArrayList<>();
            GameFigure f;
            for (int i = 0; i < enemyFigures.size(); i++) {
                f = enemyFigures.get(i);
                if(f.state == GameFigure.STATE_DONE){
                    remove.add(f);
                    if(f instanceof Bomb)
                        killedBomb += 1;
                    else if(f instanceof FlyingSaucer)
                        killedUFO += 1;
                    else if(f instanceof Bird)
                        killedBird += 1;
                    else if(f instanceof Flash)
                        killedFlash += 1;
                }
            }
            enemyFigures.removeAll(remove);

            for (GameFigure g : enemyFigures) {
                g.update();
                MainWindow.messageBomb
                    .setText("Bombs: total = " + totalBomb + 
                            ", killed = " + killedBomb + 
                            ", alive:" + (totalBomb-killedBomb));
                MainWindow.messageUFO
                    .setText("UFO's: total = " + totalUFO + 
                            ", killed = " + killedUFO + 
                            ", alive:" + (totalUFO-killedUFO));
                MainWindow.messageBird
                    .setText("Bird: total = " + totalBird + 
                            ", killed = " + killedBird + 
                            ", alive:" + (totalBird-killedBird));
                MainWindow.messageFlash
                    .setText("Flash's: total = " + totalFlash + 
                            ", killed = " + killedFlash + 
                            ", alive:" + (totalFlash-killedFlash));
            }
            
        }
        
        // missiles are removed if explosion is done
        synchronized (friendFigures) {
            ArrayList<GameFigure> remove = new ArrayList<>();
            GameFigure f;
            for (int i = 0; i < friendFigures.size(); i++) {
                f = friendFigures.get(i);
                if (f.state == GameFigure.STATE_DONE) {
                    remove.add(f);
                }
            }
            friendFigures.removeAll(remove);

            for (GameFigure g : friendFigures) {
                g.update();
            }
        }
    }    

    public int getTotalBomb() {
        return totalBomb;
    }
    
    public void setTotalBomb(int totalBomb){
        this.totalBomb = totalBomb;
    }

    public int getKilledBomb() {
        return killedBomb;
    }
    
    public void setKilledBomb(int killedBomb){
        this.killedBomb = killedBomb;
    }    

    public int getTotalUFO() {
        return totalUFO;
    }

    public void setTotalUFO(int totalUFO) {
        this.totalUFO = totalUFO;
    }
    
    public int getKilledUFO() {
        return killedUFO;
    }
    
    public void setKilledUFO(int killedUFO) {
        this.killedUFO = killedUFO;
    }  

    public int getTotalBird() {
        return totalBird;
    }

    public void setTotalBird(int totalBird) {
        this.totalBird = totalBird;
    }

    public int getKilledBird() {
        return killedBird;
    }

    public void setKilledBird(int killedBird) {
        this.killedBird = killedBird;
    }

    public int getTotalFlash() {
        return totalFlash;
    }

    public void setTotalFlash(int totalFlash) {
        this.totalFlash = totalFlash;
    }

    public int getKilledFlash() {
        return killedFlash;
    }

    public void setKilledFlash(int killedFlash) {
        this.killedFlash = killedFlash;
    }

}
