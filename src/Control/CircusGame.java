package Control;

import Model.BombObject;
import Model.ClassItrator;
import Model.Factory;
import Model.ClownObject;
import Model.Shape;
import Model.Strategy;
import java.util.LinkedList;
import java.util.List;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class CircusGame implements World {

    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private int j = 0;
    private int k = 0;
    private final Factory f = Factory.getinstance();
    private final Observer observer = new ScoreObserver(this);
    private final List<GameObject> constant = new LinkedList<>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private final List<GameObject> left = new LinkedList<GameObject>();
    private final List<GameObject> right = new LinkedList<GameObject>();
    private final Strategy gameStrategy;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public CircusGame(int screenWidth, int screenHeight, Strategy gameStrategy) {
        this.gameStrategy = gameStrategy;
        Shape ShL;
        Shape ShR;
        width = screenWidth;
        height = screenHeight;

        control.add(f.getShape("clown", screenWidth / 3, (int) (screenHeight * 0.6)));
        constant.add(f.getShape("bar", 0, 67));
        constant.add(f.getShape("bar", width - 250, 67));
        ClownObject backG = new ClownObject((width / 1000) - 50, (height / 1000) + 105, "/Backgroung.png");
        constant.add(backG);
        j = 0;
        k = 0;
        for (int i = 0; i < 10; i++) {

            ShL = (Shape) f.getShape("/enemy" + ((int) (Math.random() * 1500) % 4 + 1) + ".png", -70 * (2 * i), 60);
            ShR = (Shape) f.getShape("/enemy" + ((int) (Math.random() * 1500) % 4 + 1) + ".png", width + 70 * (2 * i), 60);

            ShR.setFlagLeftOrRight(1);

            if (ShL.getPath().equals("/enemy1.png")) {
                ShL.setY(27);

            }
            if (ShR.getPath().equals("/enemy1.png")) {
                ShR.setY(27);

            }
            j++;
            k++;
            moving.add(ShL);
            moving.add(ShR);

        }

    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        int t = gameStrategy.getTimeout() * 60 * 1000;
        boolean timeout = System.currentTimeMillis() - startTime > t; // time end and game over
        GameObject clown = control.get(0);
        ClassItrator itrator = new ClassItrator(moving);

        while (itrator.hasNext()) {
            GameObject GO = itrator.next();
            Shape Sh = (Shape) GO;
            int x = GO.getX();
            int y = GO.getY();
            y = y + (int) (Math.random() * 1000) % 4;
            if (Sh.getFlagLeftOrRight() == 0) {
                x = x + gameStrategy.getSpeed();
                if (GO.getX() + 150 > width / 2) {
                    Sh.setCurrentstState(new MovingState(Sh));
                }
            } else {
                x = x - gameStrategy.getSpeed();
                if (GO.getX() < width - 300) {
                    Sh.setCurrentstState(new MovingState(Sh));
                }
            }
            Sh.getCurrentstState().move(x, y);

            if (right.isEmpty()) {
                if (checkRight(GO)) {
                    if (Sh.getPath().equals("/enemy1.png")) {
                        moving.remove(GO);
                        if (score >= 1) {
                            observer.updateLow();

                        } else if (score <= 0) {
                            moving.clear();
                            startTime = 0;
                            return timeout;
                        }
                    } else {
                        PutOnTheRight(clown, GO, Sh);
                    }

                }
            } else {
                if (intersect(GO, right.get(right.size() - 1))) {

                    if (Sh.getPath().equals("/enemy1.png")) {
                        moving.remove(GO);
                        if (score >= 1) {
                            observer.updateLow();

                        } else if (score <= 0) {
                            moving.clear();
                            startTime = 0;
                            return timeout;
                        }
                    } else {
                        RightSide(clown, GO, Sh);

                    }
                }
            }

            if (left.isEmpty()) {
                if (checkLeft(GO)) {

                    if (Sh.getPath().equals("/enemy1.png")) {
                        moving.remove(GO);
                        if (score >= 1) {
                            observer.updateLow();

                        } else if (score <= 0) {
                            moving.clear();
                            startTime = 0;
                            return timeout;
                        }
                    } else {
                        PutOnTheLeft(clown, GO, Sh);
                    }
                }
            } else {
                if (intersect(GO, left.get(left.size() - 1))) {

                    if (Sh.getPath().equals("/enemy1.png")) {
                        moving.remove(GO);
                        if (score >= 1) {
                            observer.updateLow();

                        } else if (score <= 0) {
                            moving.clear();
                            startTime = 0;
                            return timeout;
                        }
                    } else {
                        leftSide(clown, GO, Sh);

                    }
                }
            }

            returnOnBar();
            ScoreUpLeft();
            ScoreUpRight();
            if (left.size() == gameStrategy.getNumberEnemy()) {
                return false;
            }
            if (right.size() == gameStrategy.getNumberEnemy()) {
                return false;
            }

        }
        return !timeout;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        int t = gameStrategy.getTimeout() * 60 * 1000;

        return "Score=" + score + "   |   Time=" + Math.max(0, (t - (System.currentTimeMillis() - startTime)) / 1000);	// update status
    }

    private void PutOnTheRight(GameObject clown, GameObject GO, Shape Sh) {

        moving.remove(GO);
        Sh.setClown((ClownObject) clown);
        Sh.setY(clown.getY() - Sh.getHeight() + 6);
        Sh.setType(2);
        Sh.setHorizontal(true);
        control.add(GO);
        right.add(GO);
    }

    private void PutOnTheLeft(GameObject clown, GameObject GO, Shape Sh) {
        moving.remove(GO);
        Sh.setClown((ClownObject) clown);
        Sh.setY(clown.getY() - Sh.getHeight() + 6);
        Sh.setType(1);
        Sh.setHorizontal(true);
        control.add(GO);
        left.add(GO);
    }

    private void RightSide(GameObject clown, GameObject GO, Shape Sh) {
        moving.remove(GO);
        Sh.setClown((ClownObject) clown);
        Sh.setY(right.get(right.size() - 1).getY() - Sh.getHeight());
        Sh.setType(2);
        Sh.setHorizontal(true);
        control.add(GO);
        right.add(GO);
    }

    private void leftSide(GameObject clown, GameObject GO, Shape Sh) {

        moving.remove(GO);
        Sh.setClown((ClownObject) clown);
        Sh.setY(left.get(left.size() - 1).getY() - Sh.getHeight());
        Sh.setType(1);
        Sh.setHorizontal(true);
        control.add(GO);
        left.add(GO);

    }

    private boolean checkLeft(GameObject o) {

        ClownObject clown = (ClownObject) control.get(0);
        if (o instanceof BombObject) {
            return (Math.abs(clown.getX() - o.getX()) <= 50
                    && (clown.getY() - o.getY() >= 8 && clown.getY() - o.getY() <= 10));
        } else {
            return (Math.abs(clown.getX() - o.getX()) <= o.getWidth() - 5
                    && (clown.getY() - o.getY() >= 8 && clown.getY() - o.getY() <= 10));
        }
    }

    private boolean checkRight(GameObject o) {
        ClownObject clown = (ClownObject) control.get(0);
        if (o instanceof BombObject) {
            return ((clown.getX() + clown.getWidth() - 140) <= o.getX()
                    && (clown.getX() + clown.getWidth() >= o.getX())
                    && (clown.getY() - o.getY() >= 8 && clown.getY() - o.getY() <= 10));
        }
        return ((clown.getX() + clown.getWidth() - 130) <= o.getX()
                && (clown.getX() + clown.getWidth() >= o.getX())
                && (clown.getY() - o.getY() >= 8 && clown.getY() - o.getY() <= 10));
    }

    private void returnOnBar() {
        ClassItrator itrator = new ClassItrator(moving);
        while (itrator.hasNext()) {
            GameObject GO = itrator.next();
            Shape Sh = (Shape) GO;
            if (GO.getY() > height) {
                moving.remove(Sh);
                Sh.setCurrentstState(new BarState(Sh));

                if (Sh.getPath().equals("/enemy1.png")) {
                    Sh.setY(27);
                } else {
                    Sh.setY(60);
                }
                if (Sh.getFlagLeftOrRight() == 0) {
                    Sh.setX(-80 * j);
                    j++;
                } else {
                    Sh.setX(width + 80 * k);
                    k++;
                }
                moving.add(Sh);
            }
        }
    }

    private void ScoreUpLeft() {
        if (left.size() >= 3) {
            Shape Plate1 = (Shape) left.get(left.size() - 1);
            Shape Plate2 = (Shape) left.get(left.size() - 2);
            Shape Plate3 = (Shape) left.get(left.size() - 3);
            if (Plate1.getPath().equals(Plate2.getPath()) && Plate2.getPath().equals(Plate3.getPath())) {
                left.remove(left.size() - 1);
                left.remove(left.size() - 1);
                left.remove(left.size() - 1);
                control.remove(Plate1);
                control.remove(Plate2);
                control.remove(Plate3);
                observer.update();
            }
        }
    }

    private void ScoreUpRight() {
        if (right.size() >= 3) {
            Shape Plate1 = (Shape) right.get(right.size() - 1);
            Shape Plate2 = (Shape) right.get(right.size() - 2);
            Shape Plate3 = (Shape) right.get(right.size() - 3);
            if (Plate1.getPath().equals(Plate2.getPath()) && Plate2.getPath().equals(Plate3.getPath())) {
                right.remove(right.size() - 1);
                right.remove(right.size() - 1);
                right.remove(right.size() - 1);
                control.remove(Plate1);
                control.remove(Plate2);
                control.remove(Plate3);
                observer.update();
            }
        }
    }

}
