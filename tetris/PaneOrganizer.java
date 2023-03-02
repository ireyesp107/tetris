package tetris;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The PaneOrganizer class is in charge of setting up the graphical elements of the game. Here we set up the different
 * buttons, labels, and panes.
 */
public class PaneOrganizer {

    /**
     * All instance variables declared below
     */
    private BorderPane root;
    private VBox myVbox;
    private Pane gamePane;
    private Game game;
    private Label score;
    private Label highScore;
    /**
     * This is the constructor, which sets up the graphics
     */
    public PaneOrganizer(){
        this.root= new BorderPane();

        this.gamePane = new Pane();
        this.gamePane.setFocusTraversable(true);
        this.root.setCenter(gamePane);

        this.setUpButtons();

        this.game = new Game(gamePane,this.score,this.highScore);
    }

    /**
     * Returns this.root so it can be accessed in other classes
     * @return
     */
    public Pane getRoot(){
        return this.root;
    }

    /**
     * Sets up pane that holds buttons: easy, medium, and hard difficulties, as well as a restart and quit button.
     * Sets up buttons within pane, and adds some functionality
     * Sets up labels for score and highscore.
     * setFocusTraversable is set to false for everything because only the gamePane responds to key input.
     */
    private void setUpButtons(){
        this.myVbox = new VBox(10);
        this.myVbox.setAlignment(Pos.CENTER);
        this.root.setRight(this.myVbox);

        this.highScore = new Label ("High Score: " + 0);
        this.score = new Label("Score: " + 0);
        //Easy, medium, and hard level buttons
        Button easy = new Button("Easy");
        easy.setOnAction((ActionEvent e) -> this.game.setupTimeline(Difficulty.EASY));
        easy.setFocusTraversable(false);

        Button medium = new Button("Medium");
        medium.setOnAction((ActionEvent e) -> this.game.setupTimeline(Difficulty.MEDIUM));
        medium.setFocusTraversable(false);

        Button hard = new Button("Hard");
        hard.setOnAction((ActionEvent e) -> this.game.setupTimeline(Difficulty.HARD));
        hard.setFocusTraversable(false);

        //restart button
        Button restart = new Button("Restart");
        restart.setOnAction((ActionEvent e) -> this.game.restart());
        restart.setFocusTraversable(false);

        Button quitButton = new Button("Quit");
        quitButton.setOnAction((ActionEvent e) -> System.exit(0)); //game quits when button is pressed
        quitButton.setFocusTraversable(false);

        this.myVbox.setFocusTraversable(false);
        this.myVbox.getChildren().addAll(this.highScore, this.score, easy, medium, hard, restart, quitButton);
    }

}
