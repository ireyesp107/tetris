package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This class creates pieces and handles key presses that change the movements of the pieces.
 */
public class Pieces {

    /**
     * All instance variables declared below
     */
    private Pane gamePane;
    private Square[] myPieces;

    private Board myBoard;

    /**
     * this is the constructor, which
     *
     * @param gamePane
     */
    public Pieces(Pane gamePane, Board myBoard) {

        this.gamePane = gamePane;
        this.myBoard=myBoard;
        this.myPieces=new Square[4];
        this.createArray();

        this.generatePiece();

    }

    /**
     * Creates array myPieces of size 4, containing elements of type Square.
     */
    public void createArray(){
        for(int i=0; i<4;i++){
            this.myPieces[i]=new Square(this.gamePane);

        }
    }

    /**
     * Returns array created above.
     * @return
     */
    public Square[] getArray(){
        return this.myPieces;
    }


    /**
     * Builds piece using setter methods. takes in coordinates from Constants class and a color.
     */
    public void buildPiece(int[][] coords, Color color) {
        for (int i = 0; i < 4; i++) {
            this.myPieces[i].setX2(coords[i][0]);
            this.myPieces[i].setY2(coords[i][1]);
            this.myPieces[i].setColor(color);
        }
    }

    /**
     * Generates pieces based on math.random and switch statements
     * Uses helper method buildPiece above, with argument specific to each piece
     */
    public void generatePiece() {
        int random = (int) (Math.random() * 7);
        switch (random) {
            case 0:
                this.buildPiece(Constants.I_PIECE_COORDS, Color.RED);
                break;
            case 1:
                this.buildPiece(Constants.T_PIECE_COORDS, Color.ORANGE);
                break;
            case 2:
                this.buildPiece(Constants.BOX_PIECE_COORDS, Color.PINK);
                break;
            case 3:
                this.buildPiece(Constants.L_PIECE_COORDS, Color.YELLOW);
                break;
            case 4:
                this.buildPiece(Constants.BL_PIECE_COORDS, Color.GREEN);
                break;
            case 5:
                this.buildPiece(Constants.S_PIECE_COORDS, Color.LIGHTGRAY);
                break;
            case 6:
                this.buildPiece(Constants.Z_PIECE_COORDS, Color.DEEPSKYBLUE);
                break;
            default:
                break;
        }
    }


    /**
     * When called, moves piece to the right if piece can move.
     */
    public void moveRight() {
        if(this.canMove(1,0)) {
            for (int i = 0; i < 4; i++) {
                this.myPieces[i].setX2(this.myPieces[i].getX() + Constants.SQUARE_SIZE);
            }
        }
    }

    /**
     * Drops piece down as far as it can go
     */
    public void dropDown(){
        int index=1;

        while(this.canMove(0,index)) {
            index++;
        }
            for(int i=0;i<4;i++) {
                this.myPieces[i].setY2(this.myPieces[i].getY() +((index-1)* Constants.SQUARE_SIZE));

        }
    }
    /**
    * When called, moves piece to the left if piece can move.
    */
    public void moveLeft() {
        if(this.canMove(-1,0)) {
            for (int i = 0; i < 4; i++) {
                    this.myPieces[i].setX2(this.myPieces[i].getX() - Constants.SQUARE_SIZE);

            }
        }
    }

    /**
     * checks is piece can move based on board tiles (checks for null)
     * @param dx
     * @param dy
     * @return
     */
    public boolean canMove(int dx, int dy){
        for(int i=0;i<4;i++){
            int newRow=this.myPieces[i].getY()/Constants.SQUARE_SIZE +dy;
            int newCol=this.myPieces[i].getX()/Constants.SQUARE_SIZE+dx;
            if(this.myBoard.getTiles()[newRow][newCol]!=null){
                return false;
            }
        }
        return true;
    }

    /**
     * When called, moves piece down
     */
    public void moveDown() {
        if(this.canMove(0,1)) {

            for (int i = 0; i < 4; i++) {
                this.myPieces[i].setY2(this.myPieces[i].getY() + Constants.SQUARE_SIZE);
            }
        }
    }


    /**
     * Checks if the piece can rotate.
     * @return
     */
    public boolean canRotate() {
        int centerOfRotationX = this.myPieces[0].getX();
        int centerOfRotationY = this.myPieces[0].getY();
        for (int i=0; i<4; i++) {
            int oldXLoc = this.myPieces[i].getX();
            int oldYLoc = this.myPieces[i].getY();
            int newXLoc = centerOfRotationX - centerOfRotationY + oldYLoc;
            int newYLoc = centerOfRotationY + centerOfRotationX - oldXLoc;
            int newRow = newYLoc / Constants.SQUARE_SIZE;
            int newCol = newXLoc / Constants.SQUARE_SIZE;
            if (this.myBoard.getTiles()[newRow][newCol] != null||this.myPieces[i].getColor()==Color.PINK) {
                return false;
            }
        }
        return true;
    }

    /**
     * When called, rotates piece by 90 degrees. There is some repeated code her from the method above. When we tried
     * to combine the methods into one, it didn't work properly, so it seemed like having some repeated code was the
     * best solution.
     */
    //Check if it rotates by creating a helper method and using dx to check if the move ahead goes past the left or
    // right side
    public void rotate() {
        if (canRotate()) {
            int centerOfRotationX = this.myPieces[0].getX();
            int centerOfRotationY = this.myPieces[0].getY();
            for (int i = 0; i < 4; i++) {
                int oldXLoc = this.myPieces[i].getX();
                int oldYLoc = this.myPieces[i].getY();
                int newXLoc = centerOfRotationX - centerOfRotationY + oldYLoc;
                int newYLoc = centerOfRotationY + centerOfRotationX - oldXLoc;
                this.myPieces[i].setX2(newXLoc);
                this.myPieces[i].setY2(newYLoc);
            }
        }
    }
}

