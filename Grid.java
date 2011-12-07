/**
 * @author Samuel B Sherar <sbs1@aber.ac.uk>
 * @class Grid
 *
 */
public class Grid {
	/**
	* 2-D array for the grid
	*/
    private char[][] gridLayout;
	
	/**
	* Position of the player
	*/
    private Position player;
	
	/**
	* Positions of the hunters
	*/
    private Position[] hunters;
    
    /**
	* Set up all the default values
	*/
    Grid(int gridSize, int numHunters) {
        gridLayout = new char[gridSize][gridSize];
        hunters = new Position[numHunters];
        for(int i = 0; i < numHunters; i++) {
            hunters[i] = new Position(11,11);
        }
		
        this.resetAllHunters();
        this.resetGrid();
        
    }
    
    /**
     * Prints out the grid
     * @return null
     * @params: null
     */
    public void drawGrid() {
		// Housekeeping
        this.resetGrid();
        this.setCharPOG();

        for (int i = 0; i < gridLayout.length; i++) {
            for(int j = 0; j < gridLayout.length; j++) {
                System.out.print("|" + gridLayout[j][i] + "|");
            } 
            System.out.println();
        }
    }
    
	
	/**
	* Set the player position for the grid
	* @param	p Position of the player currently
	* @return	null
	*/
    public void setPlayerPos(Position p) {
        this.player = p;
    }
    
	/**
	* Set a persiffic hunters position
	* @param 	p Position  of the hunter
	* @param 	hid Integer index of the hunter
	*/
    public void setHunterPos(Position p, int hID) {
        this.hunters[hID] = p;
    }
	
    /**
	* Reset the positions of all the hunters
	* @param 	null
	* @return	null
	*/
    private void resetAllHunters() {
        for(int i = 0; i < hunters.length; i++) {
            hunters[i].setX(11);
            hunters[i].setY(11);
        }
    }
    
    /**
	* Reset the grid to a uniform character
	* @param	null
	* @return	null
	*/
    private void resetGrid() {
        for (int i = 0; i < gridLayout.length; i++) {
            for (int j = 0; j < gridLayout.length; j++) {
                gridLayout[i][j] = '_';
            }
        }
    }
    
	/**
	* Clean the screen with blank characters
	* @param	null
	* @return	null
	*/
    public void clearScreen() {
        for(int i = 0; i <= 14; i++) {
            System.out.println();
        }
    }
    
    /**
	* Set the hunters and players on the grid
	* @param	null
	* @return 	null
	*/
    private void setCharPOG() {
        //player first
        gridLayout[player.getX()][player.getY()] = 'P';
        //hunter second
        for(int i = 0; i < hunters.length; i++) {
            int x = hunters[i].getX();
            int y = hunters[i].getY();
            gridLayout[x][y] = 'H';
        }
    }
    
}
