package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends AbstractObject {
	
	private int energy=100;
	private int moves=0;
	private boolean martelo;
	
	public Player(Point2D initialPosition){
		super(initialPosition,"Empilhadora_U",2,false);
		martelo=false;
	}
	
	public void move(Direction direction) {
        Point2D newPosition = getPosition().plus(direction.asVector());
        if(!podeAndar(newPosition)) return;
        boolean movimento=true;
        ObjectoMovivel a= SokobanGame.getInstance().Movivel(newPosition);
        if(a!=null){
            movimento= a.move(direction);
              if(!movimento) return;
        }
        ActiveObject b= SokobanGame.getInstance().Active(newPosition);
        if(b!=null)
            movimento=b.interact(this,direction);

        playerMovement(newPosition, movimento);
        setImageName("Empilhadora_" + direction.toString().charAt(0));
        ImageMatrixGUI.getInstance().update();
    }
	
	
	public void playerMovement(Point2D position, boolean movimento){
	    if(movimento){
	      setPosition(position);
          energy-=2;
          moves++;
	    }
	}
	
	public int vida(){
		return energy;
	}
	
	public boolean podeAndar(Point2D point){
	    for(AbstractObject a:SokobanGame.getInstance().map())
	        if(a.getPosition().equals(point))
	            if(a instanceof Parede)
	                return false;
	    return true;
	}
	    
	
	public int moves(){
		return moves;
	}
	
	public void setVida(int e){
	    energy=e;
	}
	
	public void martelo(){
        martelo=true;
    }
    
    public boolean temMartelo(){
        return martelo;
    }
             
	    
	    
	
	
	
	
	
	
	
	
	
	
}
