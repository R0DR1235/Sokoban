package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractObject implements ActiveObject{
    

	public Bateria(Point2D position) {
		super(position,"Bateria",1, true);
	}

    
    public boolean interact(AbstractObject a,Direction direction) {
        if(a instanceof Player){
          SokobanGame.getInstance().player().setVida(102);
          SokobanGame.getInstance().removeOfGame(this);
        }
        if(a instanceof ObjectoMovivel){
            return false;
        }
        return true;
       
    }
	

  
	

	
	

}
