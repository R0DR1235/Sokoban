package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class AbstractObject implements ImageTile{
	
    private boolean transponivel;
	private Point2D position;
	private String imageName;
	private int layer;
	
	public AbstractObject(Point2D position,String imageName,int layer,boolean tranponivel){
		this.position=position;
		this.imageName=imageName;
		this.layer=layer;
		this.transponivel=tranponivel;
	}

	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D position){
		this.position=position;
	}

	public String getName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public int getLayer() {
		return layer;
	}
	
	public boolean isTransponivel(){
	    return transponivel;
	}
	
	public static AbstractObject fabrica(char c, int x, int y) {
	    switch(c){
        case '#' : return new Parede(new Point2D(x,y));
        case 'm' : return new Martelo(new Point2D(x,y));
        case 'g' : return new Gelo(new Point2D(x,y));
        case '%' : return new ParedeQuebradica(new Point2D(x,y));
        case 't' : return new Teleporte(new Point2D(x,y),"Portal_Azul");
        case 'T' : return new Teleporte(new Point2D(x,y),"Portal_Verde");
        case 'b' : return new Bateria(new Point2D(x,y));
        case 'X' : return new Alvo(new Point2D(x,y));
        case 'C' : return new Caixote(new Point2D(x,y));
        case 'O' : return new Buraco(new Point2D(x,y));
        case 'E' : return new Player(new Point2D(x,y));
        case 's' : return new SmallStone(new Point2D(x,y));
        case 'S' : return new BigStone(new Point2D(x,y));
        default : throw new IllegalArgumentException();
        }
    }
	    
	    
}
	
	




	
	
	
	
		


