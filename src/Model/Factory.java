package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;

public class Factory {

    private static Factory g = null;

    private Factory() {

    }

    public static Factory getinstance() {
        if (g == null) {
            g = new Factory();
        }
        return g;
    }

    public GameObject getShape(String type, int x, int y) {
        if (type == null) {
            return null;
        }

        if (type.equals("/enemy2.png")) {
            return new PlateObject(x, y,type );
        }
        else  if (type.equals("/enemy3.png")) {
            return new PlateObject(x, y,type );
        }
        else if (type.equals("/enemy4.png")) {
            return new PlateObject(x, y,type );
        }
        else if (type.equals("/enemy1.png")) {
            return new BombObject(x, y, type);
        }
        

        else if (type.equals("clown")) {
            return new ClownObject(x, y, "/clown.png");
        }

        else  if (type.equals("bar")) {
            return new BarObject(x, y, 250, true, Color.PINK);
        }

        return null;
    }
}
