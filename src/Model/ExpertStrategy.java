package Model;




public class ExpertStrategy implements Strategy{

    @Override
    public int getSpeed() {
        return 3;
    }

    @Override
    public int getTimeout() {
        return 1;
        
    }

    @Override
    public int getNumberEnemy() {
  return 6;
          }
    
}
