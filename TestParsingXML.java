/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
                
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;

public class TestParsingXML {
    public static void main(String[] args) {
        
        //HighScoreElement hse1 = new HighScoreElement("AAA", 23, new Date());
        //HighScoreElement hse2 = new HighScoreElement("AAA", 231, "01-01-1970 12:34:56");
        //System.out.println(hse1);
        //System.out.println(hse2);
        
        Highscores hs = new Highscores("scores.xml");
        System.out.println(hs.viewHighscores());
        hs.overwriteScore(0, "SAM", 46);
        System.out.println();
        hs.writeToFile();
        
        
        
    }
}
