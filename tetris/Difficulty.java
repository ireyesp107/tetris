package tetris;

/**
 * Sets up difficulty level of game.
 */
public enum Difficulty {

    EASY, MEDIUM, HARD;

    /**
     * Called in setUpTimeline: determines length of keyframe for each level
     * @return
     */
    public double getSpeed() {
        switch (this) {
            case EASY:
                return 1;
            case MEDIUM:
                return .66;
            case HARD:
                return .33;
            default:
                return 1;
        }
    }

    /**
     * Called in Game class, determines how much we multiply the score based on the level
     * @return
     */
    public int getScore() {
        switch (this) {
            case EASY:
                return 1;
            case MEDIUM:
                return 2;
            case HARD:
                return 3;
            default:
                return 1;
        }
    }
}
