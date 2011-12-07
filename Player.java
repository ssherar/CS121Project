/**
 * @class Player
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */

public class Player extends Character {
	/**
	* The current strength of the player
	*/
    private int strength;
	
	/**
	* The default strength
	*/
    private int defaultStrength;
    
	/**
	* Constructor to set the players start
	* @param	x	X co-ord of the player
	* @param	y 	Y co-org of the player
	* @return	null
	*/
    Player(int x, int y) {
        super.setPosition(x,y);
        this.defaultStrength = 5;
    }
    
	/**
	* Minus the strength
	* @param	null
	* @return	null
	*/
    public void minusStrength() {
        if(this.strength > 0 ) {
            this.strength--;
        }
    }
    
	/**
	* Get the strength of the player
	* @param	null
	* @return 	the level of the strength
	*/
    public int getStrength() {
        return this.strength;
    }
    
	/**
	* reset the strength to default
	* @param	null
	* @return 	null
	*/
    public void resetStrength() {
        this.strength = this.defaultStrength;
    }
    
	/**
	* Return the position of the player
	* @param	null
	* @return	null
	*/
    public Position getPosition() {
        return super.getPosition();
    }
}
