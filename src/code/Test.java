package src.code;

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

import src.code.Racket.Direction;

public class Test extends JApplet implements Runnable, KeyListener{

	private static final int WIDTH = 1050;
	private static final int HEIGHT = 600;
	private static final int OP_WIDTH = 400; // WIDTH-OP_WIDTH
	private static final int OP_HEIGHT = 100; // HEOGHT-OP_HEIGHT

	private final int P1_STARTX = 350;
	private final int P1_STARTY = 300;
	private final int P2_STARTX = 650;
	private final int P2_STARTY = 300;
	private final int RED_RACKET_STARTX = 260;
	private final int RED_RACKET_STARTY = 400;
	private final int GREEN_RACKET_STARTX = 660;
	private final int GREEN_RACKET_STRATY = 400;
	private final int RACKET_WIDTH = 20;
	private final int RACKET_HEIGHT = 50;
	private final int PUCK_SIZE = 15;
	private final int BLOCK_WIDTH = 20;
	private final int BLOCK_HEIGHT = 30;
	private final int WALL_X = 200;
	private final int WALL_Y = 150;
	private final int WALL_THICK = 20;
	private final int WALL_LENGTH_HORIZONTALLY = 630;
	private final int WALL_LENGTH_VERTICALLY = 300;
	private final int FIELD_X = 220;
	private final int FIELD_Y = 170;
	private final int FIELD_WIDTH = 590;
	private final int FIELD_HEIGHT = 300;
	private final int PLAYER_X = 50;
	private final String PLAYER_IMAGE_1 = "../image/homo1.jpg";
	private final String PLAYER_IMAGE_2 = "../image/homo2.jpg";
	private final Color P1_COLOR = Color.red;
	private final Color P2_COLOR = Color.green;

	private Thread drawThread = null;

	private Image back;
	private Graphics buffer;
	
	private boolean redRight = false;
	private boolean redUp = false;
	private boolean redDown = false;
	private boolean redLeft = false;
	private boolean greenRight = false;
	private boolean greenUp = false;
	private boolean greenDown = false;
	private boolean greenLeft = false;

	private Racket rr;
	private Racket gr;
	private Puck p1;
	private Puck p2;
	private Goal rg;
	private Goal gg;
	private Wall wt;
	private Wall wb;
	private Field f;
	private List<Block> lb;
	private Player pl1;
	private Player pl2;
	private GameSound bgm;
	private Operation ope;


	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);

		back = createImage(WIDTH, HEIGHT);
		buffer = back.getGraphics();

		createObject();
		
		pl1 = new Player(PLAYER_X, P1_COLOR, PLAYER_IMAGE_1);
		pl2 = new Player(PLAYER_X + WALL_X + WALL_LENGTH_HORIZONTALLY, P2_COLOR, PLAYER_IMAGE_2);

		bgm = new GameSound("../music/zangyousenshi.wav");

		setFocusable(true);
		addKeyListener(this);
	}

	public void createObject() {
		rr = new Racket(RED_RACKET_STARTX, RED_RACKET_STARTY, RACKET_WIDTH, RACKET_HEIGHT, Color.RED);
		gr = new Racket(GREEN_RACKET_STARTX, GREEN_RACKET_STRATY, RACKET_WIDTH, RACKET_HEIGHT, Color.GREEN);
		p1 = new Puck(P1_STARTX, P1_STARTY, PUCK_SIZE, PUCK_SIZE, 5, 5);
		p2 = new Puck(P2_STARTX, P2_STARTY, PUCK_SIZE, PUCK_SIZE, -5, -5);

		gg = new Goal(WALL_X, WALL_Y + WALL_THICK, WALL_THICK, WALL_LENGTH_VERTICALLY, Color.GREEN);
		rg = new Goal(WALL_X + WALL_LENGTH_HORIZONTALLY - WALL_THICK, WALL_Y + WALL_THICK, WALL_THICK, WALL_LENGTH_VERTICALLY, Color.RED);
		wt = new Wall(WALL_X, WALL_Y, WALL_LENGTH_HORIZONTALLY, WALL_THICK, Color.BLACK);
		wb = new Wall(WALL_X, WALL_Y + WALL_LENGTH_VERTICALLY + WALL_THICK, WALL_LENGTH_HORIZONTALLY, WALL_THICK, Color.BLACK);

		f = new Field(FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		
		ope = new Operation(WIDTH-OP_WIDTH, HEIGHT-OP_HEIGHT, 0, 0);   // 大きさの決定

		lb = new ArrayList<Block>();
		for (int i = 0; i < FIELD_HEIGHT/ BLOCK_HEIGHT; i++) {
			Block b = new Block(FIELD_X, FIELD_Y + BLOCK_HEIGHT * i, BLOCK_WIDTH, BLOCK_HEIGHT);
			lb.add(b);
			b = new Block(FIELD_X + FIELD_WIDTH - BLOCK_WIDTH, FIELD_Y + BLOCK_HEIGHT * i, BLOCK_WIDTH, BLOCK_HEIGHT);
			lb.add(b);
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < FIELD_HEIGHT / BLOCK_HEIGHT; j++) {
				Block b = new Block(FIELD_X + FIELD_WIDTH / 2 + BLOCK_WIDTH * (i-2), FIELD_Y + j * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
				lb.add(b);
			}
		}
	}

	public void update() {
		racketMove();
		p1.move();
		puckreflect(p1);
		p2.move();
		puckreflect(p2);
		if (lb.size() == 0) { f.showImage(); }
	}
	
	public void puckreflect(Puck p) {
		if (p.isHit(rr)) { p.reflect(rr); p.changeColor(rr.getColor()); pl1.gaugeUp(); }
		if (p.isHit(gr)) { p.reflect(gr); p.changeColor(gr.getColor()); pl2.gaugeUp(); }
		if (p.isHit(gg)) { p.reflect(gg);}
		if (p.isHit(rg)) { p.reflect(rg);}
		if (p.isHit(wt)) { p.reflect(wt);}
		if (p.isHit(wb)) { p.reflect(wb);}
		for (int i = 0; i < lb.size(); i++) {
			Block b = lb.get(i);
			if (p.isHit(b)) { p.reflect(b); lb.remove(i); }

		}		
	}

	public boolean canMove(Racket r, Racket enemy) {
		if (r.isHit(gg)) return false;
		if (r.isHit(wt)) return false;
		if (r.isHit(rg)) return false;
		if (r.isHit(wb)) return false;
		if (r.isHit(enemy)) return false;
		if (r.isHit(p1)) return false;
		if (r.isHit(p2)) return false;
		for (int i = 0; i < lb.size(); i++) {
			if (r.isHit(lb.get(i))) return false;
		}
		return true;
	}
	
	public void racketMove() {
		if (redUp) rr.move(Direction.UP); if (! canMove(rr, gr) || rr.isOut(f)) {rr.move(Direction.DOWN);}
		if (redDown) rr.move(Direction.DOWN); if (! canMove(rr, gr) || rr.isOut(f)) {rr.move(Direction.UP);}
		if (redRight) rr.move(Direction.RIGHT); if (! canMove(rr, gr) || rr.isOut(f)) {rr.move(Direction.LEFT);}
		if (redLeft) rr.move(Direction.LEFT); if (! canMove(rr, gr) || rr.isOut(f)) {rr.move(Direction.RIGHT);}
		
		if (greenUp) gr.move(Direction.UP); if(! canMove(gr, rr) || gr.isOut(f)) { gr.move(Direction.DOWN);}
		if (greenDown) gr.move(Direction.DOWN); if(! canMove(gr, rr) || gr.isOut(f)) { gr.move(Direction.UP);}
		if (greenRight) gr.move(Direction.RIGHT); if(! canMove(gr, rr) || gr.isOut(f)) { gr.move(Direction.LEFT);}
		if (greenLeft) gr.move(Direction.LEFT); if(! canMove(gr, rr) || gr.isOut(f)) { gr.move(Direction.RIGHT);}
	}
	
	@Override
	public void paint(Graphics g) {

		buffer.setColor(getBackground());
		buffer.fillRect(0, 0, WIDTH, HEIGHT);

		f.draw(buffer);
		rr.draw(buffer);
		gr.draw(buffer);
		gg.draw(buffer);
		wt.draw(buffer);
		rg.draw(buffer);
		wb.draw(buffer);
		p1.draw(buffer);
		p2.draw(buffer);
		pl1.draw(buffer);
		pl2.draw(buffer);
		ope.draw(buffer);   // 説明文表示
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
				Thread.sleep(40);
			} catch (InterruptedException e) {
			}
			repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'W': redUp = true; break;
		case 'Z': redDown = true; break;
		case 'A': redLeft = true; break;
		case 'S': redRight = true; break;
		case 'R': pl1.invisible(p1, p2); break;
		case 'F': pl1.powerPuck(p1, p2); break;
		
		case 'I': greenUp = true; break;
		case 'M': greenDown = true; break;
		case 'J': greenLeft = true; break;
		case 'K': greenRight = true; break;
		case 'Y': pl2.invisible(p1, p2); break;
		case 'G': pl2.powerPuck(p1, p2); break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'W': redUp = false; break;
		case 'Z': redDown = false; break;
		case 'A': redLeft = false; break;
		case 'S': redRight = false; break;
		
		case 'I': greenUp = false; break;
		case 'M': greenDown = false; break;
		case 'J': greenLeft = false; break;
		case 'K': greenRight = false; break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
    public void start() {
        if (drawThread == null) {
            drawThread = new Thread(this);
            drawThread.start();
            bgm.start();
        }
    }

	@Override
    public void stop() {
        drawThread = null;
        bgm.stop();
    }

	public static void main(String[] arbgm) {
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
