package code;

import java.awt.Graphics;

public class PlayerManager extends Manager {


	private static Player rp;
	private static Player gp;

	public PlayerManager() {
		createPlayer();
	}

	/*
	 * プレイヤの生成
	 */
	public void createPlayer() {
		rp = new Player(PLAYER_X, RED_PLAYER_COLOR, RED_PLAYER_IMAGE, SCORE_PL1);
        gp = new Player(PLAYER_X + WALL_X + WALL_LENGTH_HORIZONTALLY, GREEN_PLAYER_COLOR, GREEN_PLAYER_IMAGE, SCORE_PL2);

	}

	/*
	 * プレイヤ情報の描画
	 */
	public void drawPlayer(Graphics g) {
		rp.draw(g);
		gp.draw(g);
	    g.fillRect(SCORE_BAR_X, SCORE_BAR_Y, SCORE_BAR_WIDTH, SCORE_BAR_HEIGHT);
	}

	/*
	 * プレイヤ赤の情報の取得
	 */
	public static Player getRedPlayer() {
		return rp;
	}

	/*
	 * プレイヤ緑の情報の取得
	 */
	public static Player getGreenPlayer() {
		return gp;
	}

}
