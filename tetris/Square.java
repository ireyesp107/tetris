package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Wraps an individual square that can be made into pieces and added to array in Board class.
 */
public class Square {

    /**
     * All instance variables declared below
     */
    private Pane gamePane;
    private Rectangle myRect;


    /**
     * Constructor takes in gamePane and constructs new Rectangle and adds it to the gamePane.
     * @param gamePane
     */
    public Square(Pane gamePane){
        this.gamePane = gamePane;
        this.myRect=new Rectangle(Constants.SQUARE_SIZE,Constants.SQUARE_SIZE); // same size for all squares
        this.gamePane.getChildren().add(this.myRect);
        this.myRect.setStroke(Color.BLACK); // outline of all squares is black

    }

    /**
     * Sets up border in color blue, returns square. Called in Board class to make border.
     * @param row
     * @param col
     * @return
     */
    public Square makeBorderSquares(int row,int col) {
        this.myRect.setX(col*Constants.SQUARE_SIZE);
        this.myRect.setY(row*Constants.SQUARE_SIZE);
        this.myRect.setFill(Color.BLUE);
        return this;

    }

    /**
     * Sets square to null
     * @return
     */
    public Square setNull(){

        return null;
    }

    /**
     * Sets up color of any square. Takes in argument Color.
     * @param c
     */
    public void setColor(Color c){
    this.myRect.setFill(c);
}
    public Color getColor(){
        return (Color)this.myRect.getFill();
    }
    public Rectangle getRect(){
        return this.myRect;
    }

    /**
     * 2 getter methods: return Y and X locations (respectively) of a single square.
     * @return
     */
    public int getY(){
        return (int)this.myRect.getY();
    }

    public int getX() {
        return (int)this.myRect.getX();
    }


    /**
     * 2 setter methods: set X and Y locations (respectively) of a single square.
     * @param x
     */
    public void setX2(int x){
        this.myRect.setX(x);
    }

    public void setY2(int y){
        this.myRect.setY(y);

    }

}
