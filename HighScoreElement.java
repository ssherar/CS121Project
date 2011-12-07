/**
 * @author Samuel B Sherar <sbs1@aber.ac.uk>
 * @class HighScoreElement
 *
 */
import java.util.*;
import java.text.*;

public class HighScoreElement implements Comparable {
	/**
	* Name of the player
	*/
    private String name;
	
	/**
	* The High Score
	*/
    private int score;
	
	/**
	* The date on which this highscore was achieved
	*/
    private Date date;
    
	
	/**
	* Constructor to set the variables
	* @param	n	Name of the player
	* @param	s	The score
	* @param	d	The date
	*/
    HighScoreElement(String n, int s, Date d) {
        this.name = n;
        this.score = s;
        this.date = d;
    }
    
	/**
	* Set the name of the player
	* @param	n	Name of the player
	* @return	null
	*/
    public void setName(String n) {
        this.name = n;
    }
    
	/**
	* Set the score of the player
	* @param	s	Score of the player
	* @return	null
	*/
    public void setScore(int s) {
        this.score = s;
    } 
    
	/**
	* Set the date of the highscore
	* @param	d	The date
	* @return	null
	*/
    public void setDate(Date d) {
        this.date = d;
    }
    
	/**
	* Returns the name of the player
	* @param	null
	* @return	players name
	*/
    public String getName() {
        return this.name;
    }
    
	/**
	* Returns the score of the player
	* @param	null
	* @return	players score
	*/
    public int getScore() {
        return this.score;
    }
    
	/**
	* Returns the date of the current highscore record
	* @param	null
	* @return	the date in non-parsed format
	*/
    public Date getDate() {
        return this.date;
    }
    
	/**
	* Calling the inherited toString method to display the record in a
	* formatted string
	* @param	null
	* @return	Formatted string of the name, score and date
	*/
    public String toString() {
        String tmp, strDate;
        
        strDate = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").format(this.date);
        
        tmp = this.pad(this.name, 4, " ") + "\t" + this.pad("" +this.score, 4, "0")+"\t"+this.pad(strDate, 20, " ");
        return tmp;
    }
    
	/**
	* Pad out the strings with a user defined spacer
	* @param	s		String to be padded
	* @param	n		How much to pad
	* @param	spacer	What to space with
	* @return	String whih has been padded
	*/
    private String pad(String s, int n, String spacer) {
        
        return String.format("%"+n + "s", s).replace(" ", spacer);
    }
    
	/**
	* Comparing two HighScoreElements together for
	* the Collections.sort() method
	* @param	o	Object to be compared with
	* @return	either -1 for lower, 1 for higher
	*/
    public int compareTo(Object o) {
		// Change the object to a HighSoreElement
        HighScoreElement tmp = (HighScoreElement) o;
		//If in the awkward moment where two scores are the same...
        if(this.score == tmp.getScore()) {
			// ...compare the dates instead
            if(this.date.compareTo(tmp.getDate()) == 1) {
                return -1;
            } else {
                return 1;
            }
        } else if(this.score > tmp.getScore()) {
            return -1;
        } else {
            return 1;
        }
    }
}
