/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @class Score
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
public class Score {
    private int currentScore;
    private int inc;
    
    Score(int i) {
        this.currentScore = 40;
        this.inc = i;
    }
    
    public void resetScore() {
        this.currentScore = 0;
    }
    
    public void incScore() {
        this.currentScore += this.inc;
    }
    
    public int showScore() {
        return this.currentScore;
    }
    
    public void setInc(int i) {
        this.inc = i;
    }
    
}
