package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
// vecmath package is not from standard Java SE
// You must add this library to your NetBeans project
import javax.vecmath.Vector2f;

public class Missile extends GameFigure {
    private int size = 4;
    // public properties for quick access
    public Color color;
    public Point2D.Float target;

    private static final int UNIT_TRAVEL_DISTANCE = 4; // per frame move

    private int count;
    private Image missile0, missile1, missile2, missile3;
    private Graphics g;

    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     * @param tx target x of the missile
     * @param ty target y of the missile
     * @param color color of the missile
     */
    public Missile(float sx, float sy, float tx, float ty, Color color) {
        super(sx, sy);
        super.state = STATE_ALIVE;
        color = Color.red;
        target = new Point2D.Float(tx, ty);
        missile0 = null;
        missile1 = null;
        missile2 = null;
        missile3 = null;
        count = 0;
        
        try {
            missile0 = ImageIO.read(getClass().getResource("missile0.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open missile0.png");
            System.exit(-1);
        }
        try {
            missile1 = ImageIO.read(getClass().getResource("missile1.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open missile1.png");
            System.exit(-1);
        }        
        try {
            missile2 = ImageIO.read(getClass().getResource("missile2.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open missile2.png");
            System.exit(-1);
        }        
        try {
            missile3 = ImageIO.read(getClass().getResource("missile3.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open missile3.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        if(count == 0){
            g.drawImage(missile0, (int)super.x, (int)super.y, 8, 8, null);        
        }else if(count == 1)
            g.drawImage(missile1,(int)super.x, (int)super.y, 40, 40, null);       
        else if(count == 2)
            g.drawImage(missile2,(int)super.x, (int)super.y, 40, 40, null);
        else if(count == 3)
            g.drawImage(missile3,(int)super.x, (int)super.y, 40, 40, null);
    }

    @Override
    public void update() {
        updateState();
        if (state == STATE_ALIVE) {
            updateLocation();
        } else if (state == STATE_DYING) {
            updatePic();
        }
    }

    // Vector arithmetic
    // A: current position
    // B: target position
    // d: distance to travel along the line from A to B
    //     A_moved = A + |B - A| * d where |  | represents 'norm'
    public void updateLocation() {
        Vector2f currentLoc = new Vector2f((float) super.x, (float) super.y);
        Vector2f update = new Vector2f(target.x, target.y);
        update.sub(currentLoc); // B - A
        update.normalize(); // |B - A|
        update.scale(UNIT_TRAVEL_DISTANCE); // |B - A| x dist
        currentLoc.add(update); // A + |B - A| x d

        super.x = currentLoc.x;
        super.y = currentLoc.y;
    }

    public void updatePic() {
        size = 40;
        count++;
    }

    public void updateState() {
        if (state == STATE_ALIVE) {
            double distance = target.distance(super.x, super.y);
            boolean targetReached = distance <= 2.0;
            if (targetReached) {
                state = STATE_DYING;                
            }
        } else if (state == STATE_DYING) {
            if (count > 3) {
                state = STATE_DONE;
            }
        }
    }

    @Override
    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Float(this.x - this.size / 2, this.y 
                - this.size / 2, this.size, this.size);
  }
}
