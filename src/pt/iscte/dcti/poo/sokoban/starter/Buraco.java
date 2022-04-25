package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractObject implements ActiveObject{

	public Buraco(Point2D position) {
		super(position,"Buraco", 1, true);
	}

    
    public boolean interact(AbstractObject a,Direction direction) {
        if(a instanceof Player){
          ImageMatrixGUI.getInstance().removeImage(a);
          SokobanGame.getInstance().playerNull();
        }
        if(a instanceof ObjectoMovivel){
            if(a instanceof BigStone){
                SokobanGame.getInstance().map().remove(a);
                SokobanGame.getInstance().map().add(new Parede(getPosition()));
                SokobanGame.getInstance().map().remove(this);
                return false;
            }else{
                SokobanGame.getInstance().removeOfGame(a);
         } 
       }
        return false; 
    }


}
