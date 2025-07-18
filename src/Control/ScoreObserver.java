
package Control;


public class ScoreObserver extends Observer{
     
    public ScoreObserver(CircusGame circus) {
        super(circus);
    }

    

    @Override
    public void update() {
        circus.setScore(circus.getScore()+1);
     }

    @Override
    public void updateLow() {
        circus.setScore(circus.getScore()-1);
    }
    
    
}
