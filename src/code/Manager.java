package code;

import java.awt.Color;

public abstract class Manager {
	
	protected static final int P1_STARTX = 400;
	protected static final int P1_STARTY = 270;
	protected static final int P2_STARTX = 600;
	protected static final int P2_STARTY = 270;
	protected static final int RED_RACKET_STARTX = 270;
	protected static final int RED_RACKET_STARTY = 260;
	protected static final int GREEN_RACKET_STARTX = 740;
	protected static final int GREEN_RACKET_STRATY = 260;
	protected static final int RACKET_WIDTH = 20;
	protected static final int RACKET_HEIGHT = 50;
	protected static final int PUCK_SIZE = 15;
	protected static final int PUCK_SPEED = 5;
	protected static final int BLOCK_WIDTH = 20;
	protected static final int BLOCK_HEIGHT = 30;
	protected static final int WALL_X = 200;
	protected static final int WALL_Y = 110;
	protected static final int WALL_THICK = 20;
	protected static final int WALL_LENGTH_HORIZONTALLY = 630;
	protected static final int WALL_LENGTH_VERTICALLY = 300;
	protected static final int FIELD_X = WALL_X + WALL_THICK;
	protected static final int FIELD_Y = WALL_Y + WALL_THICK;
	protected static final int FIELD_WIDTH = 590;
	protected static final int FIELD_HEIGHT = 300;

	protected static final int PLAYER_X = 50;
	protected static final String RED_PLAYER_IMAGE = "image/girl.png";
	protected static final String GREEN_PLAYER_IMAGE = "image/boy.png";
	protected static final String RED_DEADLY_IMAGE = "image/reddead.png";
	protected static final String GREEN_DEADLY_IMAGE = "image/greendead.png";

	protected static final Color RED_PLAYER_COLOR = Color.RED;
	protected static final Color GREEN_PLAYER_COLOR = Color.GREEN;
	protected static final int SCORE_PL1 = 250;
	protected static final int SCORE_PL2 = -250;
	protected static final int SCORE_BAR_X = 465;
	protected static final int SCORE_BAR_Y = 60;
	protected static final int SCORE_BAR_WIDTH = 100;
	protected static final int SCORE_BAR_HEIGHT = 10;
	
	public Manager() {
	}
	
	

}
