package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//=== プレイヤ緑の操作ラケット

public class GreenRacket extends Racket {

	private static final Color COLOR = Color.GREEN;
	
	public GreenRacket(int x, int y, int w, int h) {
		super(x, y, w, h);		
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
	
	@Override
	public boolean isHit(GameObject go) {
		this.rect = new Rectangle(this.x, this.y, this.width, this.height);
		return this.rect.intersects(go.getRect());
	}

	@Override
	public void move(int dis) {
		switch (dis) {
		case 'I': this.y -= 10; break;
		case 'M': this.y += 10; break;
		case 'J': this.x -= 5; break;
		case 'K': this.x += 5; break;
		}
	}
}