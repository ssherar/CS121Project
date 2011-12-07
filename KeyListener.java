/**
 * @class KeyListener
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
 
import java.util.*;

public class KeyListener {
	/**
	* Javas input class
	*/
    private Scanner input;
	
	/**
	* Constructor for the class
	* @param	null
	*/
    KeyListener() {
        input = new Scanner(System.in);
    }
    
	/**
	* Read a Character from the users input
	* @param	null
	* @return	first character of the input, or nothing depending on errors
	*/
    public char readChar() {
        try {
           char ret = (char)(input.nextLine().charAt(0));
           return ret;
        } catch (Exception e) {
           return '';
        }
    }
    
	/**
	* Check if the character inputted is the same as
	* the param
	* @param	c
	* @return	true if correct, false otherwise
	*/
    public boolean readChar(char c) {
        return (c==readChar()) ? true : false;
    }
    
	/**
	* Read an Int from the users input
	* @param	null
	* @return	The integer, or Int.MIN_VALUE otherwise
	*/
    public int readInt() {
        try { 
            return (int)input.nextInt();
        } catch(Exception e) {
            
        }
        return Integer.MIN_VALUE;
    }
    
	/**
	* Read in a string of a certain length
	* @param	length	Length of the string
	* @return	String or nothing otherwise
	*/
    public String readString(int length) {
        try {
            return ""+ input.next().substring(0,length -1);
        } catch (Exception e) {
            
        }
        return "";
    }
    
}
