package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static model.GameFigure.STATE_ALIVE;
import view.GamePanel;

public class Bird extends GameFigure {
    
    private final int HEIGHT = 10;
    private Image bird0;
    private final int UNIT_TRAVEL = 5; // per frame
    
    private int direction = 1; // +1: upwards; -1 downwards
    private int left = 0;
    private int count = 0;
    
    public Bird(float x, float y) {
        super(x, y);
        super.state = STATE_ALIVE;
        
        bird0 = null;
        try {
            bird0 = ImageIO.read(getClass().getResource("bird.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open bird.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
            g.drawImage(bird0, (int)super.x, (int)super.y, 30, 30, null);        
    }

    @Override
    public void update() {
        updateState();
        if (state == STATE_ALIVE) {
            updateLocation();
        } else if (state == STATE_DYING) {
            updateLeft();
        }
    }
    
    public void updateLocation() {
        if (direction > 0) {
            // moving down
            super.y += UNIT_TRAVEL;
            if (super.y + HEIGHT/2 > GamePanel.height) {
                direction = -1;
            }
        } else {
            // moving up
            super.y -= UNIT_TRAVEL;
            if (super.y - HEIGHT/2 <= 0) {
                direction = 1;
            }
        }
    }
    
    public void updateLeft() {
        super.x += UNIT_TRAVEL;
            
    }

    public void updateState() {
        if (super.x + 32/2 > GamePanel.width)
            state = STATE_DONE;
    }
    
    
    @Override
    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Float(this.x, this.y, 30.0F, 30.0F);
    }
}
