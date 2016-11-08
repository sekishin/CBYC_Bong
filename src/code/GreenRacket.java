package code;

import java.awt.Color;
import java.awt.Graphics;

//=== プレイヤ緑の操作ラケット

public class GreenRacket extends GameObject {

	private static final Color COLOR = Color.GREEN;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 50;

	public GreenRacket(int x, int y) {
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
