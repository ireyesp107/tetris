README <tetris>

OVERVIEW: The tetris project has 6 classes: App, PaneOrganizer, Game, Board, Square, and Pieces. App starts the program,
PaneOrganizer sets up the graphical features, and Game sets up the logical functioning of the game. Board is in charge
of dealing with the tiles on the board -- adding, removing, etc. Pieces sets up the pieces and dictates movement. The
square class models one square, which we use to create different shaped pieces. I have an Enum called difficulty which
is in charge of different levels of difficulty, which affects the speed of the game and the scoring. There is also a
constants class to keep track of frequently used numbers.


DESIGN CHOICES:
I used an array of four square to set up pieces in different arrangements, and a 2D array to model the board.
Throughout our program we use helper methods to make the code neater and less repetitive. We used an enum to store
values for different levels in the game, with switch statements to control what changes in each level. I also
used null checking to see if there was a square or not within our screen. We also used Math.max
statement to determine which score was the max in our program.



KNOWN BUGS: On rare occasions there will be a single square which does not clear, but doesn't affect game play.
We attempted several times to fix at Debugging hours but were told to submit since it doesn’t occur often.

