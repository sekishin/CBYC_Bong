package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RedRacket extends GameObject implements KeyListener {
	
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
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'W':  this.y -= 10; break;
		case 'Z':  this.y += 10; break;
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

}
