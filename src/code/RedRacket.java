package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//=== プレイヤ赤の操作ラケット

public class RedRacket extends Racket {
	
	private static final Color COLOR = Color.RED;
	
	public RedRacket(int x, int y, int w, int h) {
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
}
