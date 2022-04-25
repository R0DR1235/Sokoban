package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class ParedeQuebradica extends AbstractObject implements ActiveObject{

    public ParedeQuebradica(Point2D position) {
        super(position,"Parede_Partida",2,false);
    }
    
    public boolean interact(AbstractObject a,Direction direction) {
        if(a instanceof Player){
            if(SokobanGame.getInstance().player().temMartelo()){
             SokobanGame.getInstance().removeOfGame(this);
             return true;
            }else return false;
        }
        
        if(a instanceof ObjectoMovivel){
            return true;
       }
        return true; 
    }

}
