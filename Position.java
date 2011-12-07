/**
 * @class Position
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
public class Position {
	/**
	* X co-ord
	*/
    private int posX;
	
	/**
	* y co-ord
	*/
    private int posY;
    
	/**
	* Set up the variables
	* @param	x
	* @param	y
	*/
    Position(int x, int y) {
        posX = x;
        posY = y;
    }
    
	/**
	* Set X co-ord
	* @param	x
	* @return 	null
	*/
    public void setX(int x) {
        posX = x;
    }
    
	/**
	* Set Y co-ord
	* @param	y
	* @return 	null
	*/
    public void setY(int y) {
        posY = y;
    }
    
	/**
	* Get X co-ord
	* @param	null
	* @return 	x
	*/
    public int getX() {
        return posX;
    }
    
	/**
	* Get Y co-ord
	* @param	null
	* @return 	x
	*/
    public int getY() {
        return posY;
    }
    
	/**
	* Return the position for debugging
	* @param	null
	* @return 	String	(x, y)
	*/
    public String toString() {
        return "(" + posX + "," + posY + ")";
    }
}
