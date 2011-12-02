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
    private boolean level2;
    //private boolean isPlaying;
    
    Hunter(int x, int y) {
        super.setPosition(x, y);
        super.setDefPosition(x,y);
        this.createPath();
        //this.isPlaying = false;
        this.level2 = false;
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
        
        this.level1Path(moveX, moveY);
    }
    
    private void createPath(Position player) {
        pathIndex = 0;
        amountSteps = 0;
        path.clear();
        path.addElement(new Position(11,11));
        amountSteps++;
        int moveX = super.getPosition().getX();
        int moveY = super.getPosition().getY();
        
        this.level2Path(moveX, moveY, player);
    }
    
    private void level1Path(int moveX, int moveY) {
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
    
    private void level2Path(int moveX, int moveY, Position player) {
        // if the hunter further past the player
            
        while((moveX >= player.getX()) || (moveY >= player.getY())) {
            if(moveX > player.getX()) {
                moveX--;
            }
            if(moveY > player.getY()) {
                moveY--;
            }
            path.addElement(new Position(moveX, moveY));
            amountSteps++;
        }
        this.level1Path(moveX, moveY);
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
    
    public void resetPos(Position player) {
        super.setPosition(super.getDefPosition());
        this.pathIndex = 0;
        if(level2 == true) {
            this.createPath(player);
        } else {
            this.createPath();
        }
        
    }
    
    public void setLevel(int level) {
        if(level == 1) {
            this.level2 = false;
        } else if (level == 2) {
            this.level2 = true;
        }
    }
    
}
