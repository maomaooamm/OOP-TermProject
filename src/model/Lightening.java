package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static model.GameFigure.STATE_ALIVE;
import static model.GameFigure.STATE_DONE;
import static model.GameFigure.STATE_DYING;
import view.GamePanel;

public class Lightening extends GameFigure {

    private static final int HEIGHT = 5;
    private static final int WIDTH = GamePanel.width;
    public Color color = Color.red;
    private Image lightening;

    
    public Lightening(float x, float y, Color color) {
        super(x, y);
        super.state = STATE_ALIVE;
        lightening = null;
        
        try {
            lightening = ImageIO.read(getClass().getResource("lightening.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open lightening.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        //g.setColor(color);
        //g.fillRect((int)this.x, (int)this.y, WIDTH, HEIGHT);
        g.drawImage(lightening, (int)super.x, (int)super.y, WIDTH, 10, null);
    }

    @Override
    public void update() {
        state = STATE_DONE;
    }

    @Override
    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Float(0, this.y, WIDTH, HEIGHT);
  }
}
