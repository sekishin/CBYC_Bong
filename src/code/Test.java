package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
	private final int P2_STARTX = 500;
	private final int P2_STARTY = 200;
	private final int RED_RACKET_STARTX = 100;
	private final int RED_RACKET_STARTY = 300;
	private final int GREEN_RACKET_STARTX = 500;
	private final int GREEN_RACKET_STRATY = 300;
	private final int RACKET_WIDTH = 20;
	private final int RACKET_HEIGHT = 50;
	private final int PUCK_SIZE = 10;
	private final int BLOCK_WIDTH = 20;
	private final int BLOCK_HEIGHT = 30;
	private final int WALL_X = 50;
	private final int WALL_Y = 50;
	private final int WALL_THICK = 20;
	private final int WALL_LENGTH_HORIZONTALLY = 630;
	private final int WALL_LENGTH_VERTICALLY = 300;;
	private final int FIELD_X = 70;
	private final int FIELD_Y = 70;
	private final int FIELD_WIDTH = 590;
	private final int FIELD_HEIGHT = 300;

	private Thread drawThread = null;

	private Image back;
	private Graphics buffer;

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
		
		back = createImage(WIDTH, HEIGHT);
		buffer = back.getGraphics();
		
		createObject();
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void createObject() {
		rr = new RedRacket(RED_RACKET_STARTX, RED_RACKET_STARTY, RACKET_WIDTH, RACKET_HEIGHT);
		gr = new GreenRacket(GREEN_RACKET_STARTX, GREEN_RACKET_STRATY, RACKET_WIDTH, RACKET_HEIGHT);
		
		p1 = new Puck(P1_STARTX, P1_STARTY, PUCK_SIZE, PUCK_SIZE, 5, 5);
		p2 = new Puck(P2_STARTX, P2_STARTY, PUCK_SIZE, PUCK_SIZE, -5, -5);
		
		wLeft = new Wall(WALL_X, WALL_Y + WALL_THICK, WALL_THICK, WALL_LENGTH_VERTICALLY, Color.DARK_GRAY);
		wTop = new Wall(WALL_X, WALL_Y, WALL_LENGTH_HORIZONTALLY, WALL_THICK, Color.BLACK);
		wRight = new Wall(WALL_X + WALL_LENGTH_HORIZONTALLY - WALL_THICK, WALL_Y + WALL_THICK, WALL_THICK, WALL_LENGTH_VERTICALLY, Color.DARK_GRAY);
		wBottom = new Wall(WALL_X, WALL_Y + WALL_LENGTH_VERTICALLY + WALL_THICK, WALL_LENGTH_HORIZONTALLY, WALL_THICK, Color.BLACK);
		
		f = new Field(FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		
		lb = new ArrayList<Block>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < FIELD_HEIGHT / BLOCK_HEIGHT; j++) {
				Block b = new Block(FIELD_X + FIELD_WIDTH / 2 + BLOCK_WIDTH * (i-2), FIELD_Y + j * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
				lb.add(b);
			}
		}
	}
	
	public void update() {
		p1.move();
		if ( p1.isHit(wLeft) ) { p1.reflectX(); }
		if ( p1.isHit(wRight) ) { p1.reflectX(); }
		if ( p1.isHit(wTop) ) { p1.reflectY(); }
		if ( p1.isHit(wBottom) ) { p1.reflectY(); }
		p2.move();
		if ( p2.isHit(wLeft) ) { p2.reflectX(); }
		if ( p2.isHit(wRight) ) { p2.reflectX(); }
		if ( p2.isHit(wTop) ) { p2.reflectY(); }
		if ( p2.isHit(wBottom) ) { p2.reflectY(); }
		
		for (int i = 0; i < lb.size(); i++) {
			Block b = lb.get(i);
			if (p1.isHit(b) || p2.isHit(b)) { lb.remove(i); }
		}
		
		if (lb.size() == 0) { f.showImage(); }
	}
	
	@Override
	public void paint(Graphics g) {

		buffer.setColor(getBackground());
		buffer.fillRect(0, 0, WIDTH, HEIGHT);

		f.draw(buffer);
		rr.draw(buffer);
		gr.draw(buffer);
		p1.draw(buffer);
		p2.draw(buffer);
		wLeft.draw(buffer);
		wTop.draw(buffer);
		wRight.draw(buffer);
		wBottom.draw(buffer);
		for (int i = 0; i < lb.size(); i++) {
			lb.get(i).draw(buffer);
		}
		g.drawImage(back, 0, 0, this);

	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == drawThread) {
			update();
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
			repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'W': rr.move('W'); if (! canMove(rr, gr)) {rr.move('Z');} break;
		case 'Z': rr.move('Z'); if (! canMove(rr, gr)) {rr.move('W');} break;
		case 'A': rr.move('A'); if (! canMove(rr, gr)) {rr.move('S');} break;
		case 'S': rr.move('S'); if (! canMove(rr, gr)) {rr.move('A');} break;
		case 'I': gr.move('I'); if (! canMove(gr, rr)) {gr.move('M');} break;
		case 'M': gr.move('M'); if (! canMove(gr, rr)) {gr.move('I');} break;
		case 'J': gr.move('J'); if (! canMove(gr, rr)) {gr.move('K');} break;
		case 'K': gr.move('K'); if (! canMove(gr, rr)) {gr.move('J');} break;
		}
	}
	
	public boolean canMove(Racket r, Racket enemy) {
		if (r.isHit(wLeft)) return false;
		if (r.isHit(wTop)) return false;
		if (r.isHit(wRight)) return false;
		if (r.isHit(wBottom)) return false;
		if (r.isHit(enemy)) return false;
		for (int i = 0; i < lb.size(); i++) {
			if (r.isHit(lb.get(i))) return false;
		}
		return true;
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
    public void start() {
        if (drawThread == null) {
            drawThread = new Thread(this);
            drawThread.start();
        }
    }

	@Override
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
