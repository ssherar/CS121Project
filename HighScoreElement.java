/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
import java.util.*;
import java.text.*;

public class HighScoreElement implements Comparable {
    private String name;
    private int score;
    private Date date;
    
    HighScoreElement(String n, int s, Date d) {
        this.name = n;
        this.score = s;
        this.date = d;
    }
    
    public void setName(String n) {
        this.name = n;
    }
    
    public void setScore(int s) {
        this.score = s;
    } 
    
    public void setDate(Date d) {
        this.date = d;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public String toString() {
        String tmp, strDate;
        
        strDate = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").format(this.date);
        
        tmp = this.pad(this.name, 4, " ") + "\t" + this.pad("" +this.score, 4, "0")+"\t"+this.pad(strDate, 20, " ");
        return tmp;
    }
    
    private String pad(String s, int n, String spacer) {
        
        return String.format("%"+n + "s", s).replace(" ", spacer);
    }
        
    public int compareTo(Object o) {
        HighScoreElement tmp = (HighScoreElement) o;
        if(this.score == tmp.getScore()) {
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
