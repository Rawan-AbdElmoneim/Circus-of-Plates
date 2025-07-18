
package Model;




import Control.CircusGame;



public class MyFirstGame {

    
    public static void main(String[] args) {
         EasyStrategy e = new EasyStrategy();
        GameController gameController = new GameController(() -> new CircusGame(800, 500,e));
        gameController.start();
       
    }

}
