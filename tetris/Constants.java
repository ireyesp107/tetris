package tetris;

/**
 * Holds constants for numbers so as to make the code more readable
 */
public class Constants {

    // width of each square orignal 30
    public static final int SQUARE_SIZE = 30;
    public static final int HALF_GAME_WIDTH=5*SQUARE_SIZE;

    // coordinates for squares in each tetris piece
    public static final int[][] I_PIECE_COORDS = {{5*SQUARE_SIZE, SQUARE_SIZE*1}, {5*SQUARE_SIZE, 2*SQUARE_SIZE},
            {5*SQUARE_SIZE, 3 * SQUARE_SIZE}, {5*SQUARE_SIZE, 4 * SQUARE_SIZE}};

    public static final int[][] T_PIECE_COORDS = {{HALF_GAME_WIDTH, SQUARE_SIZE}, {HALF_GAME_WIDTH, 2*SQUARE_SIZE},
            {HALF_GAME_WIDTH, 3*SQUARE_SIZE}, {6*SQUARE_SIZE, 2*SQUARE_SIZE}};

    public static final int[][] BOX_PIECE_COORDS = {{HALF_GAME_WIDTH, SQUARE_SIZE}, {HALF_GAME_WIDTH, 2*SQUARE_SIZE},
        {6*SQUARE_SIZE, SQUARE_SIZE}, {6*SQUARE_SIZE, 2*SQUARE_SIZE}};

    public static final int[][] L_PIECE_COORDS = {{HALF_GAME_WIDTH, SQUARE_SIZE}, {HALF_GAME_WIDTH, 2*SQUARE_SIZE},
        {HALF_GAME_WIDTH, 3*SQUARE_SIZE}, {6*SQUARE_SIZE, SQUARE_SIZE}};

    public static final int[][] BL_PIECE_COORDS={{-1*SQUARE_SIZE+HALF_GAME_WIDTH, SQUARE_SIZE},
            {HALF_GAME_WIDTH, SQUARE_SIZE}, {HALF_GAME_WIDTH, 2*SQUARE_SIZE},
            {HALF_GAME_WIDTH, 3*SQUARE_SIZE}};
    public static final int[][] S_PIECE_COORDS= {{HALF_GAME_WIDTH, SQUARE_SIZE}, {HALF_GAME_WIDTH, 2*SQUARE_SIZE},
            {-1*SQUARE_SIZE+ HALF_GAME_WIDTH, 2*SQUARE_SIZE},
            {-1*SQUARE_SIZE+ HALF_GAME_WIDTH, 3*SQUARE_SIZE}};
    public static final int[][] Z_PIECE_COORDS= {{-1*SQUARE_SIZE+ HALF_GAME_WIDTH, SQUARE_SIZE},
            {-1*SQUARE_SIZE+ HALF_GAME_WIDTH, 2*SQUARE_SIZE}, {HALF_GAME_WIDTH, 2*SQUARE_SIZE},
            {HALF_GAME_WIDTH, 3*SQUARE_SIZE}};

    public static final int SCENE_HEIGHT = 510;
    public static final int GAME_WIDTH = 330;
    public static final int SCENE_WIDTH=430;

    public static final int NUM_ROWS= SCENE_HEIGHT/SQUARE_SIZE;
    public static final int NUM_COLS=GAME_WIDTH/SQUARE_SIZE;

    public static final int BOTTOM_ROW = SCENE_HEIGHT-SQUARE_SIZE;

}
