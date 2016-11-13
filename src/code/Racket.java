package code;

import java.awt.Graphics;

public abstract class Racket extends GameObject {

	static enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
	
	public Racket(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	abstract public void draw(Graphics g);

	@Override
	abstract public Type getType();
	
	abstract public boolean isHit(GameObject go);
	
	public void move(Direction dis) {
		switch (dis) {
		case UP : this.y -= 10; break;
		case DOWN : this.y += 10; break;
		case RIGHT : this.x += 5; break;
		case LEFT : this.x -= 5; break;
		}
	}

}
