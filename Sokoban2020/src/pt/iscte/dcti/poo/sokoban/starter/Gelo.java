package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Gelo extends AbstractObject implements ActiveObject{

    public Gelo(Point2D position) {
        super(position, "Gelo", 1, true);
    }

    
    public boolean interact(AbstractObject a,Direction direction) {   
      if(a instanceof Player){
        a.setPosition(getPosition());
        player().move(direction);
        return false;   
      }
      if(a instanceof ObjectoMovivel){
        Point2D point = a.getPosition();
        a.setPosition(getPosition());
        ObjectoMovivel b= (ObjectoMovivel) a;
        b.move(direction);
        PlayerMove(point,direction);
     }
      return true; 
    }
    
    public Player player(){
        return SokobanGame.getInstance().player();
    }
    
    public void PlayerMove(Point2D point,Direction direction){
        player().setPosition(point);
        player().setImageName("Empilhadora_" + direction.toString().charAt(0));
        ImageMatrixGUI.getInstance().update();
    }
        
    
}


