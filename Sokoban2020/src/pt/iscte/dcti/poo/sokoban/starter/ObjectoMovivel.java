package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class ObjectoMovivel extends AbstractObject{
    

    public ObjectoMovivel(Point2D position, String imageName, int layer, boolean tranponivel) {
        super(position, imageName, layer, tranponivel);
    }
    
    public boolean move(Direction direction) {
        Point2D newPosition = getPosition().plus(direction.asVector());
        if(!podeAndar(newPosition)) return false;
        ActiveObject b= SokobanGame.getInstance().Active(newPosition);
        if(b!=null){
           boolean interacao=b.interact(this,direction);
           if(interacao) return false;
        }
        setPosition(newPosition);
        ImageMatrixGUI.getInstance().update();
        return true;
    }
    
    public boolean podeAndar(Point2D point){
        for(AbstractObject a:SokobanGame.getInstance().map())
            if(a.getPosition().equals(point))
                if(!a.isTransponivel())
                    return false;
        return true;
    }
    
    

}
