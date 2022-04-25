package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Teleporte extends AbstractObject implements ActiveObject{

    public Teleporte(Point2D position, String imageName) {
        super(position, imageName,1,true);
    }

    @Override
    public boolean interact(AbstractObject a,Direction direction) {
        if( a instanceof ObjectoMovivel){
            return false;
        }
        if(a instanceof Player && !obstructed()){
            a.setPosition(teleport());
            return false;
          }
              
        return true;

    }
    
    
    public boolean obstructed(){
        ArrayList<AbstractObject> listaTeleportes = new ArrayList<>();
        for(AbstractObject a : SokobanGame.getInstance().map())
            if(a instanceof Teleporte) listaTeleportes.add(a);
        for(AbstractObject tel : listaTeleportes){
          if((SokobanGame.getInstance().objectsPosition(tel.getPosition())).size()>1) 
              return true;
        }
        
        return false;
    }
    
    
    public Point2D teleport(){
        Point2D point=null;
        for(AbstractObject obj: SokobanGame.getInstance().map()){
            if(obj instanceof Teleporte){
                if(!(obj.equals(this)))
                 point= obj.getPosition();
            }
        }
        return point;
    }
        
    
    

}

