/**
 * @author Samuel B Sherar <sbs1@aber.ac.uk>
 * @class Engine
 *
 */
import java.util.*;

public class Engine {
	/**
	* Class for gathering input from the user
	*/
    private KeyListener in;
	
	/**
	* Class for outputing grid and characters
	*/
    private Grid map;
	
	/**
	* Class for the player
	*/
    private Player p;
	
	/**
	* An array of Hunter classes
	*/
    private Hunter[] h;
	
	/**
	* Class for keeping score
	*/
    private Score s;
	
	/**
	* Class for writing and displaying Highscores
	*/
    private Highscores hs;
	
	/**
	* Boolean for the main loop
	*/
    private boolean lose;
	
	/**
	* Boolean for advancing to level 2
	*/
    private boolean level2;
	
	/**
	* Choosing what menu to go to
	*/
    private int menuChoice = -1;
	
	/**
	* Constant for the start position for the Hunters
	*/
    private final Position HUNTER_START = new Position(11,11);
    
	/**
	* Initalisation of the Engine Class
	* @param	null
	*/
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
    
	/**
	* Main method for the whole game
	* @param	null
	* @return	null
	*/
    public void init() {
        do {
			//Echo out the menu
            this.echoMenu();
            
            menuChoice = in.readInt();
            
            switch(menuChoice) {
                case 1:
                    this.playGame(false);
                    break;
                case 2:
                    this.playGame(true);
                case 3:
                    this.highScores();
                    break;
                default:
                    break;
            }
        } while(menuChoice != 0);
    }
    
	/**
	* Method to print out the menu
	* @param	null
	* @return	null
	*/
    private void echoMenu() {
        System.out.println("Welcome to the game");
        System.out.println();
        System.out.println("1) Play New Game");
        System.out.println("2) Play From Level 2");
        System.out.println("3) Look At The Highscores");
        System.out.println("0) Exit");
        System.out.println();
        System.out.print("Please enter a choice from the menu above > ");
    }
	
	/**
	* Method to play the game!
	* @param		lvl2 True if you want to play level 2, false is not
	* @return		null
	*/
    private void playGame(boolean lvl2) {
        boolean moved = false;
		
		// Set up the score Increments and reset hunters to chase the player
        if(lvl2) {
			s.setInc(2);
            for(Hunter i : h) {
                i.setLevel(2);
                i.resetPos(p.getPosition());
            }
        } else {
			s.setInc(1);
		}
		
		//Housekeeping...
        p.resetStrength();     
        s.resetScore();
		
		// Where the magic happens!
        while(!lose) {
			
			//Set the position of the player on the grid
            map.setPlayerPos(p.getPosition());
			// And loop through the hunters
            for(int i = 0; i < h.length; i++) {
                    map.setHunterPos(h[i].getPosition(), i);
            }
			
			// Draw out the playing scren
            map.clearScreen();
            this.echoHeader();
            map.drawGrid();
            System.out.println();
            System.out.print("Please Choose Your Next Move > ");
			
			// Get the user input and act upon it
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
            
			// Check if we have actually moved before we move our hunters
			// shall we?
            if(moved == true) {
                moved = false;
                for(Hunter i: h) {
                    if(!i.nextMove()) {
                          i.resetPos(p.getPosition());
                          s.incScore();
                    }
                }
            }
            
			// check if we shall move to level2?
            if(s.showScore() > 42 && lvl2 == false) {
                lvl2 = true;
                s.setInc(2);
                for(Hunter i: h) {
                    i.setLevel(2);
                }
            }
            
			// Check if there has been a collision
            int collision = this.checkCollision();
            System.out.println();
            if(collision >= 0) {
                h[collision].resetPos(p.getPosition());
                p.minusStrength();
            }
            
			// Let's see if the player has died
            if(p.getStrength() == 0) {
                lose = true;
            }
        }
        this.lostTheGame();
    }
	
	/**
	* Display the HighScores
	* @param	null
	* @return	null
	*/
    private void highScores() {
        System.out.println("Name \t Score \t Date");
        System.out.println(hs.viewHighscores()+"\n\n");
        
    }
    
	/**
	* Displaying the final score and check the highscores
	* @return null
    * @params null
	*/
    private void lostTheGame() {
        System.out.println("\n\n Well done, you managed to get " + s.showScore() + " points!");
        //check against highscores and see if is a highscore!
        int index = hs.indexOfLowest();
		//Sorting the wheat from the chaff
        if(hs.getScore(index) < s.showScore()) {
            System.out.print("Congraulations! You have made it on the scoreboard!\n Please enter your name (only 3 characters!) > ");
            String name = in.readString(3);
            hs.overwriteScore(index, name, s.showScore());
            hs.writeToFile();
            System.out.println();
        }
    }
    
	/**
	* Checking the player and the hunters have a collision
	* @param 	null
	* @return 	index of the hunter who hit, or returns -1 if none
	*/
    private int checkCollision() {
        for(int i = 0; i < h.length; i++) {
            if (h[i].comparePosition(p.getPosition())) {
                return i;
            }
        }
        return -1;
    }
    
	/**
	* Method for echoing out the header for the game
	* @param	null
	* @return	null
	*/
    private void echoHeader() {
        System.out.print("Strength: " + p.getStrength() + "\t");
        System.out.print("Score: " + s.showScore() + "\t");
        System.out.print("Level: " + ((this.level2 == true) ? 2 : 1));
        System.out.println();
    }
}