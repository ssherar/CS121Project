/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @class Engine
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
import java.util.*;

public class Engine {
    private KeyListener in;
    private Grid map;
    private Player p;
    private Hunter[] h;
    private Score s;
    private Highscores hs;
    private boolean lose;
    private boolean level2;
    private int menuChoice = -1;
    private final Position HUNTER_START = new Position(11,11);
    
    Engine() {
        in = new KeyListener();
        map = new Grid(12, 5);
        p = new Player(0,11);
        h = new Hunter[5];
        s = new Score(1);
        hs = new Highscores("scores.xml");
        lose = false;
        for(int i = 0; i < h.length; i++) {
            h[i] = new Hunter(HUNTER_START);
        }
    }
    
    public void init() {
        do {
            this.echoMenu();
            
            menuChoice = in.readInt();
            
            switch(menuChoice) {
                case 1:
                    this.playGame();
                    break;
                case 2:
                    this.highScores();
                    break;
                default:
                    break;
            }
        } while(menuChoice != 0);
    }
    
    private void echoMenu() {
        System.out.println("Welcome to the game");
        System.out.println();
        System.out.println("1) Play New Game");
        System.out.println("2) Look At The Highscores");
        System.out.println("3) Options");
        System.out.println("0) Exit");
        System.out.println();
        System.out.print("Please enter a choice from the menu above > ");
    }

    private void playGame() {
        boolean moved = false;
        s.setInc(1);
        s.setInc(5);
        p.resetStrength();     
        s.resetScore();    
        while(!lose) {
            
            map.setPlayerPos(p.getPosition());
            for(int i = 0; i < h.length; i++) {
                    map.setHunterPos(h[i].getPosition(), i);
            }
            map.clearScreen();
            this.echoHeader();
            map.drawGrid();
            System.out.println();
            System.out.print("Please Choose Your Next Move > ");
            char move = in.readChar();
            switch(move) {
                case 'g':
                    moved = p.moveLeft();
                    break;
                case 'h':
                    moved = p.moveRight();
                    break;
                case 'j':
                    moved = p.moveUp();
                    break;
                case 'k':
                    moved = p.moveDown();
                    break;
                default:
                    continue;
                    
            }
            
            if(moved == true) {
                moved = false;
                for(Hunter i: h) {
                    if(!i.nextMove()) {
                          i.resetPos(p.getPosition());
                          s.incScore();
                    }
                }
            }
            
            if(s.showScore() > 42 && this.level2 == false) {
                this.level2 = true;
                s.setInc(2);
                for(Hunter i: h) {
                    i.setLevel(2);
                }
            }
            
            int collision = this.checkCollision();
            System.out.println();
            if(collision >= 0) {
                h[collision].resetPos(p.getPosition());
                p.minusStrength();
            }
            
            if(p.getStrength() == 0) {
                lose = true;
            }
        }
        this.lostTheGame();
    }

    private void highScores() {
        System.out.println(hs.viewHighscores()+"\n\n");
        
    }
    
    private void lostTheGame() {
        System.out.println("\n\n Well done, you managed to get " + s.showScore() + " points!");
        //check against highscores and see if is a highscore!
        int index = hs.indexOfLowest();
        if(hs.getScore(index) < s.showScore()) {
            System.out.print("Congraulations! You have made it on the scoreboard!\n Please enter your name (only 3 characters!) > ");
            String name = in.readString(3);
            hs.overwriteScore(index, name, s.showScore());
            hs.writeToFile();
            System.out.println();
        }
    }
    
    private int checkCollision() {
        for(int i = 0; i < h.length; i++) {
            if (h[i].comparePosition(p.getPosition())) {
                return i;
            }
        }
        return -1;
    }
    
    private void echoHeader() {
        System.out.print("Strength: " + p.getStrength() + "\t");
        System.out.print("Score: " + s.showScore() + "\t");
        System.out.print("Level: " + ((this.level2 == true) ? 2 : 1));
        System.out.println();
    }
}


