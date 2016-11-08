package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//=== プレイヤ赤の操作ラケット

public class RedRacket extends GameObject {
	
	private static final Color COLOR = Color.RED;
	private final int WIDTH = 10;
	private final int HEIGHT = 50;
	
	public RedRacket(int x, int y) {
		super(x, y);		
		int left = x;
		int top = y;
		int right = x + this.WIDTH;
		int bottom = y + this.HEIGHT;
		
		rect = new Rectangle(left, top, right, bottom);

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(COLOR);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
	
	@Override
	public Type getType() {
		return Type.Racket;
	}

	public void move(int dis) {
		switch (dis) {
		case 'W': this.y -= 10; break;
		case 'Z': this.y += 10; break; 
		case 'A': this.x -= 5; break;
		case 'S': this.x += 5; break;
		}
	}
}
