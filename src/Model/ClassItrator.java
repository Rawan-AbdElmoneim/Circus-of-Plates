package Model;



import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.Iterator;
import java.util.List;

public class ClassItrator implements Iterator<GameObject> {

    int i = 0;
    List<GameObject> ArrOfObjects;

    public ClassItrator(List<GameObject> ArrOfObjects) {
        this.ArrOfObjects = ArrOfObjects;
    }

    @Override
    public boolean hasNext() {
        if (i < ArrOfObjects.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GameObject next() {
        GameObject GO=ArrOfObjects.get(i);
        i++;
        return GO;
    }
}
