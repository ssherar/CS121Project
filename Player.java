/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @class Player
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
/*public class Player{
    private Position charPos;
    private int Strength;
    Player (int x, int y) {
        charPos = new Position(x, y);
        this.Strength = 5;
    }
    
    public Position getPosition() {
        return charPos;
    }
    
    public void setPosition(int x, int y) {
        this.charPos.setX(x);
        this.charPos.setY(y);
    }
    
    
}
 */

public class Player extends Character {
    private int strength;
    Player(int x, int y) {
        super.setPosition(x,y);
        this.strength = 5;
    }
    
    public void minusStrength() {
        if(this.strength > 0 ) {
            this.strength--;
        }
    }
    
    public int getStrength() {
        return this.strength;
    }
}
