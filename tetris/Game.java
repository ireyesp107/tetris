package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This class is in charge of the logical functioning of the game, and delegates to Board and Pieces classes.
 */
public class Game {

    /**
     * Instance variables declared below
     */
    private Board board;
    private Pane gamePane;
    private Pieces myPiece;
    private Timeline myTimeline;
    private int score;
    private int highscore;
    private Label gameLabel;
    private int numKeyPressed;
    private Label labelScore;
    private Label labelHighScore;

    /**
     * This is the constructor. Here we instantiate the board and pieces.
     * @param gamePane
     */
    public Game(Pane gamePane,Label myScore,Label highScore) {

        this.gamePane=gamePane;

        this.labelScore=myScore;
        this.labelHighScore=highScore;
        this.board = new Board(gamePane, this);

        this.myPiece = new Pieces(gamePane,this.board);
        this.setupTimeline(Difficulty.EASY);
        this.numKeyPressed = 0;
        this.score = 0;

        this.gameLabel = new Label();
        this.gameLabel.setTextFill(Color.RED);
        this.gameLabel.setFocusTraversable(false);
        this.gameLabel.setLayoutY(15);
        this.gameLabel.setLayoutX(140);
        this.gamePane.getChildren().add(this.gameLabel);
    }

    /**
     * This method sets up the keyframe and timeline.
     */
    public void setupTimeline(Difficulty difficulty) {
        if (this.myTimeline!=null) { // make sure existing platform stops
            this.myTimeline.stop();
        }
        KeyFrame kf = new KeyFrame(Duration.seconds(difficulty.getSpeed()),
                (ActionEvent e) -> this.updateGame(difficulty.getScore()));
        this.myTimeline = new Timeline(kf);
        this.myTimeline.setCycleCount(Animation.INDEFINITE);
        this.myTimeline.play();
    }

    /**
     * This method updates the screen based on the timeline. At every duration, it checks if a piece can move further
     * down. If so, it moves down another step; if not, it calls the stopPiece method below. Also in charge of
     * making sure piece responds to keyboard input, as well as clearing lines.
     * Method takes in int i because it is called in each keyFrame, which changes based on Enum difficulty. See methods
     * in enum.
     */
    public void updateGame(int i){


        this.handleScore(i);

        this.changeDirection(); //handles key input
        this.board.isFull();

        if(this.myPiece.canMove(0,1)) { // if piece can move down, move it down
            this.myPiece.moveDown();
        } else {
            //add to board
            //generate piece
            this.stopPiece();
            this.score+= i*5;
        }


        if (this.board.checkEndGame()) {
            this.gameLabel.setText("GAME OVER");
            this.labelHighScore.setText("HighScore: "+this.highscore );
            this.myTimeline.stop();
        }
    }

    /**
     * Assigns methods to certain keys so that method is called when key is pressed
     * @param e
     */
    private void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case LEFT:
                this.myPiece.moveLeft();
                break;
            case RIGHT:
                this.myPiece.moveRight();
                break;
            case DOWN:
                this.myPiece.moveDown();
                break;
            case UP:
                this.myPiece.rotate();
                break;
            case SPACE:
                this.myPiece.dropDown();
                break;
            case P:
                this.pause();
                break;
            default:
                break;
        }
        e.consume();
    }

    /**
     * Calls the above methods so that the game responds to keyPresses.
     */
    private void changeDirection() {
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPress(e));
        this.gamePane.setFocusTraversable(true);
    }

    /**
     * Stops piece from moving if it shouldn't be able to move, and generates a new piece at the top of the board.
     */
    private void stopPiece() {
        //add the piece to the board
        this.addToBoard();

        if (!this.board.checkEndGame()) {
            this.myPiece = new Pieces(this.gamePane, this.board);
        }
    }


    /**
     * Adds piece to board. Public method because it is called in other classes.
     */
    public void addToBoard() {
        for(int i=0; i<4;i++){
            Square s = this.myPiece.getArray()[i];
            this.board.setTiles(s.getY()/Constants.SQUARE_SIZE,s.getX()/ Constants.SQUARE_SIZE, s);
        }
    }

    /**
     * Handles score calculations and updating scores.
     * @param i
     */
    private void handleScore(int i) {
        if (this.board.getScoreCounter()<4) {
            this.score += i*(this.board.getScoreCounter()*50);
        } else if (this.board.getScoreCounter()==4) {
            this.score += i*400;
        } else if (this.board.getScoreCounter()>4) {
            this.score = i*((this.board.getScoreCounter()/4)*200 + (this.board.getScoreCounter()%4)*50);
        }

        int currentHigh = this.highscore;
        this.highscore = Math.max(this.score, currentHigh);

        this.labelScore.setText("Score: "+this.score );
        this.labelHighScore.setText("HighScore: "+this.highscore );
    }

    /**
     * Handles restarting game
     */
    public void restart() {
        this.gameLabel.setText("");
        this.myTimeline.stop();

        this.board.clearBoard();
        this.addToBoard();
        this.board.clearBoard();
        this.score = 0;
        this.myTimeline.play();
        this.myPiece = new Pieces(this.gamePane,this.board);
    }

    /**
     * handles game pause functionality
     */
    private void pause() {
        if (this.numKeyPressed==0){
            this.myTimeline.stop();
            this.gameLabel.setText("PAUSED");
            this.numKeyPressed =1;
        } else if (this.numKeyPressed == 1) {
            this.myTimeline.play();
            this.gameLabel.setText("");
            this.numKeyPressed=0;
        }
    }
}




