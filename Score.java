/**
 * @class Score
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
public class Score {
	/**
	* The current score of the game
	*/
    private int currentScore;
	
	/**
	* How much we increment the score by
	*/
    private int inc;
    
	/**
	* Construct. Set the increment and init the variables
	* @param	i	increment
	* @return	null
	*/
    Score(int i) {
        this.currentScore = 0;
        this.inc = i;
    }
    
	/**
	* Reset the score ready for the next game
	* @param	null
	* @return	null
	*/
    public void resetScore() {
        this.currentScore = 0;
    }
    
	/**
	* Incremenent the score
	*/
    public void incScore() {
        this.currentScore += this.inc;
    }
    
	/**
	* Show the score
	* @param	null
	* @return	the current score
	*/
    public int showScore() {
        return this.currentScore;
    }
    
	/**
	* Set the incrementation
	* @param	i	increment
	* @return	null
	*/
    public void setInc(int i) {
        this.inc = i;
    }
}
