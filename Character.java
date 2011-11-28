/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @class Character
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
public class Character {
    private Position charPos = new Position(-1, -1);
    private Position defPos = new Position(-1,-1);
    
    /*Character(int x, int y) {
        charPos.setX(x);
        charPos.setY(y);
    }*/
    
    public void setPosition(int x, int y) {
        charPos.setX(x);
        charPos.setY(y);
    }
    
    public void setPosition(Position p) {
        charPos = p;
    }
    
    public Position getPosition() {
        return charPos;
    }
    
    public void setDefPosition(int x, int y) {
        defPos.setX(x);
        defPos.setY(y);
    }
    
    public void setDefPosition(Position p) {
        defPos = p;
    }
    
    public Position getDefPosition() {
        return defPos;
    }
    
    public boolean moveLeft() {
        if(charPos.getX() > 0) {
            charPos.setX(charPos.getX() - 1);
            return true;
        }
        return false;
    }
    
    public boolean moveRight() {
        if(charPos.getX() < 11) {
            charPos.setX(charPos.getX() + 1);
            return true;
        }
        return false;
    }
    
    public boolean moveUp() {
        if(charPos.getY() > 0) {
            charPos.setY(charPos.getY() - 1);
            return true;
        }
        return false;
    }
    
    public boolean moveDown() {
        if(charPos.getY() < 11) {
            charPos.setY(charPos.getY() + 1);
            return true;
        }
        return false;
    }
    
    public boolean comparePosition(Position p) {
        return ((p.getX() == charPos.getX()) && (p.getY() == charPos.getY())) ? true : false;
    }
}
