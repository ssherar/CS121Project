/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @class KeyListener
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */

import java.util.*;

public class KeyListener {
    private Scanner input;
    KeyListener() {
        input = new Scanner(System.in);
    }
    
    public char readChar() {
        try {
           char ret = (char)(input.nextLine().charAt(0));
           return ret;
        } catch (Exception e) {
           return '0';
        }
    }
    
    public boolean readChar(char c) {
        return (c==readChar()) ? true : false;
    }
    
    public int readInt() {
        try { 
            return (int)input.nextInt();
        } catch(Exception e) {
            
        }
        return Integer.MIN_VALUE;
    }
    
}
