package Model;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BarObject implements GameObject {

    public static final int SPRITE_HEIGHT = 5;
    private static final int NUM_STATE = 1;
    private BufferedImage[] spriteImages = new BufferedImage[NUM_STATE];
    private int x;
    private int y;
    private int width;
    private boolean visible;
    private boolean horizontal;

    public BarObject(int posX, int posY, int width, boolean horizontalOnly, Color color) {
        this.x = posX;
        this.y = posY;
        this.width = width;
        this.horizontal= horizontalOnly;
        this.visible = true;
        spriteImages[0] = new BufferedImage(width, SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = spriteImages[0].createGraphics();
        g2.setColor(color);
        g2.setStroke(new BasicStroke(15));
        g2.drawLine(0, 0, getWidth(), 0);
        g2.dispose();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (horizontal) {
            return;
        }
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return SPRITE_HEIGHT;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
