package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import static model.GameFigure.STATE_ALIVE;

public class Flash extends GameFigure {

    private final int radius = 10;
    private Color color = Color.blue;
    private int size;
    private int MIN_SIZE = 0;
    Random rand = new Random();
    private Color randomColor;
    private float r, g, b;

    public Flash(float x, float y) {
        super(x, y);
        super.state = STATE_ALIVE;
        size = radius;       
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        // Note: use drawOval() to draw outline only
        g.fillRect((int)this.x - this.size, (int)this.y 
                - this.size, this.size * 2, this.size * 2);        
    }

    @Override
    public void update() {
        // Rectangle flash within gamepanel
        
        if (state == STATE_ALIVE) {
            updateColor();
        } else if (state == STATE_DYING) {
            updateSize();
        } 
        updateState();
    }
    public void updateColor(){ 
        r = rand.nextFloat();
        g = rand.nextFloat();
        b = rand.nextFloat();
        randomColor = new Color(r, g, b);
        color = randomColor;
    }
    public void updateSize(){
        size -= 1;  
        color = Color.green;
    }
    
    public void updateState() {
        if (size < MIN_SIZE)            
            state = STATE_DONE;        
    }
    
    @Override
    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Float(this.x - this.radius, this.y 
            - this.radius, this.radius * 2, this.radius * 2);
    }
}
