package code;

import java.awt.Graphics;

public abstract class Racket extends GameObject {

	public Racket(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	abstract public void draw(Graphics g);

	@Override
	abstract public Type getType();
	
	abstract public boolean isHit(GameObject go);
	
	abstract public void move(int dis);

}
