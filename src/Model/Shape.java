package Model;

import Control.State;
import Control.BarState;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Shape implements GameObject {

    private static final int NUM_STATE = 1;

    private BufferedImage[] spriteImages = new BufferedImage[NUM_STATE];
    private int x;
    private int y;
    private int type;
    private int FlagLeftOrRight;
    private boolean visible;
    private boolean horizontal;
    private String path;
    private State currentstState;
    private ClownObject c;

    public Shape(int posX, int posY, String path) {
        this(posX, posY, path, 0);
    }

    public Shape(int posX, int posY, String path, int type) {
        this.x = posX;
        this.y = posY;
        this.type = type;
        this.visible = true;
        this.path = path;
        this.currentstState = new BarState(this);
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
        }
    }

    public State getCurrentstState() {
        return currentstState;
    }

    public void setCurrentstState(State currentstState) {
        this.currentstState = currentstState;
    }

    public int getFlagLeftOrRight() {
        return FlagLeftOrRight;
    }

    public void setFlagLeftOrRight(int FlagLeftOrRight) {
        this.FlagLeftOrRight = FlagLeftOrRight;
    }

    public void setClown(ClownObject c) {
        this.c = c;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int GOx) {
        switch (type) {
            case 1:
                this.x = c.getX() + 40;
                break;
            case 2:
                this.x = c.getX() + c.getWidth() - this.getWidth() - 40;
                break;
            default:
                this.x = GOx;
                break;
        }
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (!horizontal) {
            this.y = mY;
        }
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
