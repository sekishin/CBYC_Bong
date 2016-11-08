package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//=== プレイヤ緑の操作ラケット

public class GreenRacket extends GameObject {

	private static final Color COLOR = Color.GREEN;
	private final int WIDTH = 10;
	private final int HEIGHT = 50;
	
	public GreenRacket(int x, int y) {
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
		switch(dis) {
		case 'I': this.y -= 10; break;
		case 'M': this.y += 10; break;
		case 'J': this.x -= 5; break;
		case 'K': this.x += 5; break;
		}
	}
	
}
