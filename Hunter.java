/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @class Hunter
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
import java.util.*;

public class Hunter extends Character {
    private Vector<Position> path = new Vector<Position>();
    private int pathIndex;
    private int amountSteps;
    //private boolean isPlaying;
    
    Hunter(int x, int y) {
        super.setPosition(x, y);
        super.setDefPosition(x,y);
        this.createPath();
        //this.isPlaying = false;
    }
    
    Hunter(Position p) {
        super.setPosition(p);
        super.setDefPosition(p);
        this.createPath();
        //this.isPlaying = false;
    }
    
    private void createPath() {
        pathIndex = 0;
        amountSteps = 0;
        path.clear();
        path.addElement(new Position(11,11));
        amountSteps++;
        int moveX = super.getPosition().getX();
        int moveY = super.getPosition().getY();
        
        while((moveX > 0) || (moveY > 0)) {
            int randomAmount = 0, cellsLeft = 0;
            cellsLeft = (moveX < moveY) ? moveX : moveY;
            cellsLeft = (cellsLeft < 3) ? cellsLeft : 3;
            
            randomAmount = (int)(Math.random()*cellsLeft)+1;
            if(moveX == 0) {
                moveY -= randomAmount;
            } else if (moveY == 0) {
                moveX -=randomAmount;
            } else {
                int whichWay = (int)((Math.random()*2)+1);
                if(whichWay == 1) {
                    moveY -=randomAmount;
                } else {
                    moveX -=randomAmount;
                }
            }
            path.addElement(new Position(moveX, moveY));
            amountSteps++;
        }
    }
    
    public void echoPath() {
        for(int i = 0; i < path.size(); i++) {
            System.out.println("("+path.elementAt(i).getX()+","+path.elementAt(i).getY()+")");
        }
    }
    
    public boolean nextMove() {
        if(pathIndex < amountSteps) {
            super.setPosition(path.elementAt(pathIndex));
        } else {
            return false;
        }
        pathIndex++;
        return true;
    }
    
    public void resetPos() {
        super.setPosition(super.getDefPosition());
        this.createPath();
        this.pathIndex = 0;
    }
    
    /*public void setState(boolean b) {
        this.isPlaying = b;
    }
    
    public boolean getState() {
        return this.isPlaying;
    }*/
    
}
