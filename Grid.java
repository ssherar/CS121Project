
/**
 * @class Grid
 * @description: Creates the playing 
 * grid for the game
 *
 * @author Sam Sherar (sbs1)
 */
public class Grid {
    //leaving it empty to expand at a later date
    private char[][] gridLayout;
    private Position player;
    private Position[] hunters;
    //private 
    
    
    Grid(int gridSize, int numHunters) {
        gridLayout = new char[gridSize][gridSize];
        hunters = new Position[numHunters];
        for(int i = 0; i < numHunters; i++) {
            hunters[i] = new Position(11,11);
        }
        this.resetAllHunters();
        //populate the array with the grid lines
        this.resetGrid();
        
    }
    
    /**
     * @method drawGrid
     * @description: echos the board to
     * the window
     * @returnValue: null
     * @params: null
     */
    public void drawGrid() {
        this.resetGrid();
        this.setCharPOG();
        //this.printHeader();
        for (int i = 0; i < gridLayout.length; i++) {
            for(int j = 0; j < gridLayout.length; j++) {
                System.out.print("|" + gridLayout[j][i] + "|");
            } 
            System.out.println();
        }
    }
    
    public void setPlayerPos(Position p) {
        this.player = p;
    }
    
    public void setHunterPos(Position p, int hID) {
        this.hunters[hID] = p;
    }
    
    private void resetAllHunters() {
        for(int i = 0; i < hunters.length; i++) {
            hunters[i].setX(11);
            hunters[i].setY(11);
        }
        //hunters[0].setX(11);
        //hunters[0].setY(11);
    }
    
    
    
    private void resetGrid() {
        for (int i = 0; i < gridLayout.length; i++) {
            for (int j = 0; j < gridLayout.length; j++) {
                gridLayout[i][j] = '_';
            }
        }
    }
    
    public void clearScreen() {
        for(int i = 0; i <= 14; i++) {
            System.out.println();
        }
    }
    
    /**
     * @method setPosOnGrid
     * @description: sets the position
     * @returnValue: null
     * @params: p : Position
     *          c : characterName
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
