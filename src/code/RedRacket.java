package code;

import java.awt.Color;
import java.awt.Graphics;

//=== プレイヤ赤の操作ラケット

public class RedRacket extends GameObject {
	
	private static final Color COLOR = Color.RED;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 50;

	public RedRacket(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(COLOR);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}

	public void move(int dis) {
		
	}
}
