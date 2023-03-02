package tetris;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



/**
 * This class is in charge of the board and wraps an array composed of squares.
 */
public class Board {
    /**
     * Instance variables declared below
     */
    private Square[][] tiles;
    private Game game;
    private Pane gamePane;
    private int rowFilled;
    private int numFilled;
    private boolean isfilled;
    private int scoreCounter;

    /**
     * Constructs a board, associated with gamePane
     * @param gamePane
     */
    public Board(Pane gamePane, Game game){
        this.gamePane=gamePane;
        this.game=game;
        this.setBorder();
        this.numFilled=0;
    }

    /**
     * Instantiates new Boardsquare array and sets up border around the edge of the board.
     */
    private void setBorder() {
        this.tiles = new Square[Constants.NUM_ROWS][Constants.NUM_COLS];
        for (int row = 0; row < Constants.NUM_ROWS; row++) { // for each row
            for (int col = 0; col < Constants.NUM_COLS; col++) { // for each column
                if (col == 0 || col == Constants.NUM_COLS - 1) { // if it's the first or last column
                    this.tiles[row][col] = new Square(this.gamePane).makeBorderSquares(row,col);
                }
                if (row == 0 || row == Constants.NUM_ROWS - 1) { // if it's the first or last row
                    this.tiles[row][col] = new Square(this.gamePane).makeBorderSquares(row,col);
                }

            }
        }
    }

    /**
     * Sets up a square at a particular row and column in the 2D array.
     * @param row
     * @param col
     * @param sq
     */
    public void setTiles(int row, int col, Square sq) {
        if (this.tiles[row][col]!=null) {
            this.gamePane.getChildren().remove(this.tiles[row][col].getRect());
        }
        this.tiles[row][col]=sq;
    }


    /**
     * Supposed to clear line when the row is full
     */
    public void clearLines() {

        if (this.isfilled) {
            for (int j = 1; j < Constants.NUM_COLS - 1; j++) {
                this.gamePane.getChildren().remove(this.tiles[rowFilled][j].getRect());
                this.tiles[rowFilled][j].setNull();
            }
            for (int row = rowFilled; row > 1; row--) {

                for (int col = 1; col < Constants.NUM_COLS - 1; col++) {
                    if (this.tiles[row][col] != null && this.tiles[row][col].getColor() != Color.BLUE) {
                        this.tiles[row][col].setY2((row + 1) * Constants.SQUARE_SIZE);
                        this.tiles[row][col]=this.tiles[row-1][col];
                    }
                }
            }
        }
    }

    /**
     * boolean method to check if a row is full (called in clearLines method)
     * @return
     */
    public void isFull(){
        this.scoreCounter = 0;
        for (int i=1; i<Constants.NUM_ROWS-1; i++) {

                this.numFilled=0;
                this.isfilled=false;
                for (int j=1; j<Constants.NUM_COLS-1; j++) {

                    if (this.tiles[i][j] != null) {
                        this.numFilled++;
                    }
                    }
            if (this.numFilled == 9) {
                this.isfilled = true;

                this.rowFilled = i;
                this.clearLines();

                this.scoreCounter++;
                this.isfilled = false;

                this.numFilled = 0;

            }
        }
    }

    /**
     * Returns this.scoreCounter so that score can be calculated in game class.
     * @return
     */
    public int getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * Returns square (element of 2D array)
     * @return
     */
    public Square[][] getTiles(){
        return this.tiles;
    }

    /**
     * Clears all tiles on the board.
     */
    public void clearBoard() {
        this.game.addToBoard(); // bug bc it doesn't add pieces that aren't full
        for (int row = 1; row<Constants.NUM_ROWS-1; row++) {
            for (int col = 1; col<Constants.NUM_COLS-1; col++) {
                if (this.tiles[row][col]!=null) {
                    this.gamePane.getChildren().remove(this.tiles[row][col].getRect());
                    this.tiles[row][col]=null;

                }
            }
        }
    }

    /**
     * Checks if there is a tile in the top row and if so, returns true.
     * @return
     */
    public boolean checkEndGame() {
        for (int col=1; col<Constants.NUM_COLS-1; col++) {
            if (this.tiles[1][col]!=null) {
                return true;
            }
        }
        return false;
    }
}


