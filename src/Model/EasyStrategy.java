package Model;



public class EasyStrategy implements Strategy {

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getTimeout() {
        return 3;
    }

    @Override
    public int getNumberEnemy() {
 return 10;
    }

}
