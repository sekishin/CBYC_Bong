package code;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test extends JApplet implements Runnable, KeyListener{
	
	private Thread drawThread = null;
	private RedRacket rr;
	private GreenRacket gr;
	private static final int WIDTH = 481;
	private static final int HEIGHT = 400;

	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);
		rr = new RedRacket(20, 20);
		gr = new GreenRacket(70, 20);
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		rr.draw(g);
		gr.draw(g);
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == drawThread) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'I': gr.move('I'); break;
		case 'M': gr.move('M'); break;
		case 'J': gr.move('J'); break;
		case 'K': gr.move('K'); break;
			
		case 'W': rr.move('W'); break;
		case 'Z': rr.move('Z'); break;
		case 'A': rr.move('A'); break;
		case 'S': rr.move('S'); break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	public void start() {
		if (drawThread == null) {
			drawThread = new Thread(this);
			drawThread.start();
		}
	}

	public void stop() {
		drawThread = null;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Test");       
			JApplet applet = new Test();
			applet.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			frame.add(applet);
			frame.pack();
			frame.setVisible(true);
			applet.init();
			applet.start();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}

}
