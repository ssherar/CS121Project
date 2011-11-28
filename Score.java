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
    
    Score() {
        this.currentScore = 0;
    }
    
    public void resetScore() {
        this.currentScore = 0;
    }
    
    public void incScore() {
        this.currentScore++;
    }
    
    public int showScore() {
        return this.currentScore;
    }
}
