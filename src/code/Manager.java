package code;

import java.awt.Color;

public abstract class Manager {
	
	protected final int P1_STARTX = 350;
	protected final int P1_STARTY = 300;
	protected final int P2_STARTX = 650;
	protected final int P2_STARTY = 300;
	protected final int RED_RACKET_STARTX = 260;
	protected final int RED_RACKET_STARTY = 400;
	protected final int GREEN_RACKET_STARTX = 660;
	protected final int GREEN_RACKET_STRATY = 400;
	protected final int RACKET_WIDTH = 20;
	protected final int RACKET_HEIGHT = 50;
	protected final int PUCK_SIZE = 15;
	protected final int BLOCK_WIDTH = 20;
	protected final int BLOCK_HEIGHT = 30;
	protected final int WALL_X = 200;
	protected final int WALL_Y = 150;
	protected final int WALL_THICK = 20;
	protected final int WALL_LENGTH_HORIZONTALLY = 630;
	protected final int WALL_LENGTH_VERTICALLY = 300;
	protected final int FIELD_X = 220;
	protected final int FIELD_Y = 170;
	protected final int FIELD_WIDTH = 590;
	protected final int FIELD_HEIGHT = 300;

	protected final int PLAYER_X = 50;
	protected final String RED_PLAYER_IMAGE = "../image/homo1.jpg";
	protected final String GREEN_PLAYER_IMAGE = "../image/homo2.jpg";
	protected final Color RED_PLAYER_COLOR = Color.RED;
	protected final Color GREEN_PLAYER_COLOR = Color.GREEN;
	protected final int SCORE_PL1 = 250;
	protected final int SCORE_PL2 = -200;
	protected final int SCORE_BAR_X = 465;
	protected final int SCORE_BAR_Y = 60;
	protected final int SCORE_BAR_WIDTH = 100;
	protected final int SCORE_BAR_HEIGHT = 10;
	
	public Manager() {
	}
	
	

}
