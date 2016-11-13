package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket extends GameObject {
	
	protected Color color;

	static enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
	
	public Racket(int x, int y, int w, int h, Color c) {
		super(x, y, w, h);
		this.color = c;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fill3DRect(this.x, this.y, this.width, this.height, true);
	}

	@Override
	public Type getType() {
		return Type.Racket;
	}
	
	public boolean isHit(GameObject go) {
		this.rect = new Rectangle(this.x, this.y, this.width, this.height);
		return this.rect.intersects(go.getRect());
	}
	
	public void move(Direction dis) {
		switch (dis) {
		case UP : this.y -= 10; break;
		case DOWN : this.y += 10; break;
		case RIGHT : this.x += 5; break;
		case LEFT : this.x -= 5; break;
		}
	}
	
	public Color getColor() {
		return this.color;
	}

}
