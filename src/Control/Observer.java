
package Control;


public abstract class Observer {

    CircusGame circus;

    public Observer(CircusGame circus) {
        this.circus = circus;
    }

   public  abstract void update();
   public  abstract void updateLow();

}
