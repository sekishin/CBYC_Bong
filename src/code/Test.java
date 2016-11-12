package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test extends JApplet implements Runnable, KeyListener{

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private final int P1_STARTX = 200;
	private final int P1_STARTY = 200;
	private final int RACKET_WIDTH = 20;
	private final int RACKET_HEIGHT = 50;
	private final int PUCK_SIZE = 10;
	private final int BLOCK_WIDTH = 20;
	private final int BLOCK_HEIGHT = 30;
	private final int FIELD_X = 70;
	private final int FIELD_Y = 70;
	private final int FIELD_WIDTH = 590;
	private final int FIELD_HEIGHT = 300;

	private Thread drawThread = null;

	private RedRacket rr;
	private GreenRacket gr;
	private Puck p1;
	private Puck p2;
	private Wall wRight;
	private Wall wTop;
	private Wall wLeft;
	private Wall wBottom;
	private Field f;
	private List<Block> lb;
	

	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);
		rr = new RedRacket(20, 20, RACKET_WIDTH, RACKET_HEIGHT);
		gr = new GreenRacket(70, 20, RACKET_WIDTH, RACKET_HEIGHT);
		p1 = new Puck(P1_STARTX, P1_STARTY, PUCK_SIZE, PUCK_SIZE, 1, 1);
		
		wLeft = new Wall(50, 70, 20, 300, Color.BLUE);
		wTop = new Wall(50, 50, 630, 20, Color.BLACK);
		wRight = new Wall(660, 70, 20, 300, Color.BLUE);
		wBottom = new Wall(50, 370, 630, 20, Color.BLACK);
		
		//f = new Field(FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		
		lb = new ArrayList<Block>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < FIELD_HEIGHT / BLOCK_HEIGHT; j++) {
				Block b = new Block(FIELD_X + FIELD_WIDTH / 2 + BLOCK_WIDTH * (i-2), FIELD_Y + j * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
				lb.add(b);
			}
		}

		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		rr.draw(g);
		gr.draw(g);
		p1.draw(g);
		wLeft.draw(g);
		wTop.draw(g);
		wRight.draw(g);
		wBottom.draw(g);
		for (int i = 0; i < lb.size(); i++) {
			lb.get(i).draw(g);
		}
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == drawThread) {
			p1.move();
			if ( p1.isHit(wLeft) ) { p1.reflectX(); }
			if ( p1.isHit(wRight) ) { p1.reflectX(); }
			if ( p1.isHit(wTop) ) { p1.reflectY(); }
			if ( p1.isHit(wBottom) ) { p1.reflectY(); }
			try {
				Thread.sleep(10);
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
