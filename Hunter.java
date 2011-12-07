/**
 * @class Hunter
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
import java.util.*;

public class Hunter extends Character {

	/**
	* The path of the hunter
	*/
    private Vector<Position> path = new Vector<Position>();
	
	/**
	* The current index of the path
	*/
    private int pathIndex;
	
	/**
	* The amount of steps
	*/
    private int amountSteps;
	
	/**
	* If on level 2 yet
	*/
    private boolean level2;
    
	/**
	* Constructor to set the both Default and Current
	* position
	* @param	x	X co-ord
	* @param	y	Y co-ord
	*/
    Hunter(int x, int y) {
        super.setPosition(x, y);
        super.setDefPosition(x,y);
        this.createPath();
        this.level2 = false;
    }
    
	/**
	* Constructor to set the both Default and Current
	* position
	* @param	p	Position class
	*/
    Hunter(Position p) {
        super.setPosition(p);
        super.setDefPosition(p);
        this.createPath();
		this.level2 = false;
    }
	
    /**
	* Create the path for the hunter to follow
	* @param	null
	* @return	null
	*/
    private void createPath() {
		// Housekeeping
        pathIndex = 0;
        amountSteps = 0;
        path.clear();
		
		// Init the start of the path
        path.addElement(new Position(11,11));
        amountSteps++;
        int moveX = super.getPosition().getX();
        int moveY = super.getPosition().getY();
        
		// Create the rest of the path
        this.level1Path(moveX, moveY);
    }
    
	/**
	* Create the path for the hunter to follow
	* @param	player	Position class of the Player to bias towards
	* @return	null
	*/
    private void createPath(Position player) {
		// Housekeeping
        pathIndex = 0;
        amountSteps = 0;
        path.clear();
		
		//Init the start of the path
        path.addElement(new Position(11,11));
        amountSteps++;
        int moveX = super.getPosition().getX();
        int moveY = super.getPosition().getY();
        
		//Create the rest of the path
        this.level2Path(moveX, moveY, player);
    }
    
	/**
	* Create the level 1 path
	* @param	moveX	the start X co-ord of the hunter
	* @param	moveY	the start Y co-ord of the hunter
	* @return	null
	*/
    private void level1Path(int moveX, int moveY) {
		// Loop through until both X and Y are at the end
        while((moveX > 0) || (moveY > 0)) {
			// init some temp variables
            int randomAmount = 0, cellsLeft = 0;
			
			// Make the hunters move at random amount
            cellsLeft = (moveX < moveY) ? moveX : moveY;
            cellsLeft = (cellsLeft < 3) ? cellsLeft : 3;
            randomAmount = (int)(Math.random()*cellsLeft)+1;
			
			// If at the edges, just move up
            if(moveX == 0) {
                moveY -= randomAmount;
            } else if (moveY == 0) {
                moveX -=randomAmount;
            } else {
				// Choose which way to go
                int whichWay = (int)((Math.random()*2)+1);
                if(whichWay == 1) {
                    moveY -=randomAmount;
                } else {
                    moveX -=randomAmount;
                }
            }
			// add this to the rest of the path and increment
            path.addElement(new Position(moveX, moveY));
            amountSteps++;
        }
    }
    
	/**
	* Create the level 1 path
	* @param	moveX	the start X co-ord of the hunter
	* @param	moveY	the start Y co-ord of the hunter
	* @param	player	Position of the player
	* @return	null
	*/
    private void level2Path(int moveX, int moveY, Position player) {
		// Loop through until both X and Y are above the the player
        while((moveX > player.getX()) || (moveY > player.getY())) {
			// If at the edges, just move up
            if(moveX > player.getX()) {
                moveX--;
            }
            if(moveY > player.getY()) {
                moveY--;
            }
            path.addElement(new Position(moveX, moveY));
            amountSteps++;
        }
		// Carry on with creating a level 1 path when past
        this.level1Path(moveX, moveY);
    }
    
	/**
	* Debugging the path
	* @param	null
	* @return 	null
	*/
    public void echoPath() {
        for(int i = 0; i < path.size(); i++) {
            System.out.println("("+path.elementAt(i).getX()+","+path.elementAt(i).getY()+")");
        }
    }
    
	/**
	* Move the huner to the next position
	* @param	null
	* @return	true if moved, false if not
	*/
    public boolean nextMove() {
        if(pathIndex < amountSteps) {
            super.setPosition(path.elementAt(pathIndex));
        } else {
            return false;
        }
        pathIndex++;
        return true;
    }
    
	/**
	* Reset the position of the hunter and create a new path
	* @param	player	Position of the player
	* @return	null
	*/
    public void resetPos(Position player) {
        super.setPosition(super.getDefPosition());
        this.pathIndex = 0;
        if(level2 == true) {
            this.createPath(player);
        } else {
            this.createPath();
        }
        
    }
	
    /**
	* Set level for the path creation
	* @param	level	What level are we on
	* @return	null
	*/
    public void setLevel(int level) {
        if(level == 1) {
            this.level2 = false;
        } else if (level == 2) {
            this.level2 = true;
        }
    }
    
}
