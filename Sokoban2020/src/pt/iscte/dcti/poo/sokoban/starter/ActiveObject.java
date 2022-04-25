package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;

public interface ActiveObject {
	
	boolean interact(AbstractObject a,Direction direction);
    
}
