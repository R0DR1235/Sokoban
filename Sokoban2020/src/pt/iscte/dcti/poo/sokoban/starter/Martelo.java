package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Martelo extends AbstractObject implements ActiveObject{

    public Martelo(Point2D position) {
        super(position, "Martelo", 1, true);
    }
    
    public boolean interact(AbstractObject a,Direction direction) {
        if(a instanceof Player){
          SokobanGame.getInstance().player().martelo();
          SokobanGame.getInstance().removeOfGame(this);
          return true;
        }
        return false;
       
    }

}
