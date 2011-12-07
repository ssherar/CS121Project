/**
 * @author Samuel B Sherar <sbs1@aber.ac.uk>
 * @class Character
 * 
 * Superclass for both Hunter and Player classes.
 */
public class Character {
	/**
	* Position at character at any one time
	*/
    private Position charPos = new Position(-1, -1);
	/**
	* Default position of character
	*/
    private Position defPos = new Position(-1,-1);
    
	/**
	* Set the position
	* 
	* @param x		X co-ord of where to set the character
	* @param y		Y co-ord of where ot set the character
	*/
    public void setPosition(int x, int y) {
        charPos.setX(x);
        charPos.setY(y);
    }
	
    /**
	* Set the position using a class
	* @param 		p Class of the position
	*/
    public void setPosition(Position p) {
        charPos = p;
    }
    
	/**
	* Get the position of the character
	* @return		Position of Character
	*/
    public Position getPosition() {
        return charPos;
    }
    
	/**
	* Setting the default position of the character.
	* @param		x X co-ord for the default position
	*/
    public void setDefPosition(int x, int y) {
        defPos.setX(x);
        defPos.setY(y);
    }
    
	/**
	* Set the default position of a character 
	* using a class
	* @param 		p Class of the position
	*/
    public void setDefPosition(Position p) {
        defPos = p;
    }
    
	/**
	* Get the default position of the character
	* @return		Position of Character
	*/
    public Position getDefPosition() {
        return defPos;
    }
	
    /**
	* Move the character 1 step to the left
	* @return true if moved, false if out of bountries
	*/
    public boolean moveLeft() {
        if(charPos.getX() > 0) {
            charPos.setX(charPos.getX() - 1);
            return true;
        }
        return false;
    }
    
	/**
	* Move the character 1 step to the right
	* @return true if moved, false if out of boundries
	*/
    public boolean moveRight() {
        if(charPos.getX() < 11) {
            charPos.setX(charPos.getX() + 1);
            return true;
        }
        return false;
    }
    
	/**
	* Move the character 1 step forward
	* @return true if moved, false if out of boundries
	*/
    public boolean moveUp() {
        if(charPos.getY() > 0) {
            charPos.setY(charPos.getY() - 1);
            return true;
        }
        return false;
    }
    
	/**
	* Move the character 1 step backwards
	* @return true if moved, false if out of boundries
	*/
    public boolean moveDown() {
        if(charPos.getY() < 11) {
            charPos.setY(charPos.getY() + 1);
            return true;
        }
        return false;
    }
    
	/**
	* Compare the positions of this character and an inputted
	* character's position and see if there is an intersection
	*
	* @param		p Position of character to compare with
	* @return		true if intersection, false otherwiese
    public boolean comparePosition(Position p) {
        return ((p.getX() == charPos.getX()) && (p.getY() == charPos.getY())) ? true : false;
    }
}
