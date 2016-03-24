package model;

import view.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bomb extends GameFigure {

    private final int radius;
    private final Color color;
    private int dx = 3;
    private int dy = 3;
    private int size;
    private int MAX_SIZE = 20;

    public Bomb(float x, float y, int radius, Color color) {
        super(x, y);
        super.state = STATE_ALIVE;
        this.radius = radius;
        this.color = color;
        this.size = radius;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        // Note: use drawOval() to draw outline only
        g.fillOval((int)(x - size), (int)(y - size), 
                size * 2, size * 2);
    }

    @Override
    public void update() {
        // ball bounces on the wall        
        updateState();
        if (state == STATE_ALIVE) {
            updateLocation();
        } else if (state == STATE_DYING) {
            updateSize();
        }
    }
    
    public void updateLocation() {
        super.x += dx;
        super.y += dy;

        if (super.x + radius > GamePanel.width) {
            dx = -dx;
            super.x = GamePanel.width - radius;
        } else if (super.x - radius < 0) {
            dx = -dx;
            super.x = radius;
        }

        if (super.y + radius > GamePanel.height) {
            dy = -dy;
            super.y = GamePanel.height - radius;
        } else if (super.y - radius < 0) {
            dy = -dy;
            super.y = radius;
        }
    }
    
    public void updateSize() {
        size += 2;
    }

    public void updateState() {
        if (size >= MAX_SIZE) {
            state = STATE_DONE;
        }
    }
    
    @Override
    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Float(this.x - this.radius, this.y 
            - this.radius, this.radius * 2, this.radius * 2);
    }
}
