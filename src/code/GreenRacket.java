package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GreenRacket extends GameObject implements KeyListener{

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
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'I':  this.y -= 10; break;
		case 'M':  this.y += 10; break;
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
