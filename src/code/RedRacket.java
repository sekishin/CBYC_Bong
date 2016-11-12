package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//=== プレイヤ赤の操作ラケット

public class RedRacket extends GameObject {
	
	private static final Color COLOR = Color.RED;
	
	public RedRacket(int x, int y, int w, int h) {
		super(x, y, w, h);		
		int left = x;
		int top = y;
		int right = x + this.width;
		int bottom = y + this.height;
		
		rect = new Rectangle(left, top, right, bottom);

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(COLOR);
		g.fill3DRect(this.x, this.y, this.width, this.height, true);
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
