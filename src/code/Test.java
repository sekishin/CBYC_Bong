package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test extends JApplet implements Runnable, KeyListener{

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private final int STARTX = 200;
	private final int STARTY = 200;

	private Thread drawThread = null;

	private RedRacket rr;
	private GreenRacket gr;
	private Puck p;
	private Wall wRight;
	private Wall wTop;
	private Wall wLeft;
	private Wall wBottom;

	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);
		rr = new RedRacket(20, 20);
		gr = new GreenRacket(70, 20);
		p = new Puck(STARTX, STARTY);
		wLeft = new Wall(50, 70, 20, 200, Color.RED);
		wTop = new Wall(50, 50, 420, 20, Color.BLACK);
		wRight = new Wall(450, 70, 20, 200, Color.RED);
		wBottom = new Wall(50, 270, 420, 20, Color.BLACK);

		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		rr.draw(g);
		gr.draw(g);
		p.draw(g);
		wLeft.draw(g);
		wTop.draw(g);
		wRight.draw(g);
		wBottom.draw(g);
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == drawThread) {
			p.move();
			if ( p.isHit(wLeft) ) { p.reflectX(); }
			if ( p.isHit(wRight) ) { p.reflectX(); }
			if ( p.isHit(wTop) ) { p.reflectY(); }
			if ( p.isHit(wBottom) ) { p.reflectY(); }
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			repaint();
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
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			applet.init();
			applet.start();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}

}
