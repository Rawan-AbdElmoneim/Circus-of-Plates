package Model;


public class HardStrategy implements Strategy {

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public int getTimeout() {
        return 2;
    }

    @Override
    public int getNumberEnemy() {
   return 8;
           }
}
