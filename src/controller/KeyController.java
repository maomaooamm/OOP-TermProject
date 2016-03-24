package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import model.Lightening;
import model.Shooter;
import view.GamePanel;

public class KeyController implements KeyListener {
   
    private int x;
    private int y;
    private Image imageLeft;
    private Image imageRight;
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        x = 0;
        y = (int)(Math.random()*GamePanel.height);
        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                shooter.translate(-5, 0);
                break;
            case KeyEvent.VK_RIGHT:
                shooter.translate(5, 0);
                break;
            case KeyEvent.VK_UP:
                shooter.translate(0, -5);
                break;
            case KeyEvent.VK_DOWN:
                shooter.translate(0, 5);
                break;
            case KeyEvent.VK_SPACE:
                Lightening l = new Lightening(x,y,Color.red);
                synchronized (Main.gameData.friendFigures) {
                    Main.gameData.friendFigures.add(l);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {        
    }

}
