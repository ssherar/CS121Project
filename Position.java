
/**
 * @class Position
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
public class Position {
    private int posX;
    private int posY;
    
    Position(int x, int y) {
        posX = x;
        posY = y;
    }
    
    public void setX(int x) {
        posX = x;
    }
    
    public void setY(int y) {
        posY = y;
    }
    
    public int getX() {
        return posX;
    }
    
    public int getY() {
        return posY;
    }
    
    public String toString() {
        return "(" + posX + "," + posY + ")";
    }
}
