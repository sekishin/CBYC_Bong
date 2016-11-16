package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import code.Racket.Direction;

public class Test extends JApplet implements Runnable , KeyListener{

	private static final int WIDTH = 1050;
	private static final int HEIGHT = 650;
	private static final int OP_WIDTH = 400; // WIDTH-OP_WIDTH
	private static final int OP_HEIGHT = 100; // HEOGHT-OP_HEIGHT

	
	private Thread drawThread = null;
	private Image back;
	private Graphics buffer;

	private ObjectManager om;
	private PlayerManager pm;
	private GameSound bgm;
	private StartScreen ss;
	private Operation ope;

	boolean gameFlag = false;

	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);

		back = createImage(WIDTH, HEIGHT);
		buffer = back.getGraphics();

        ope = new Operation(WIDTH-OP_WIDTH, HEIGHT-OP_HEIGHT, 0, 0);   // 大きさの決定
        ss = new StartScreen(WIDTH, HEIGHT);
	    bgm = new GameSound("../music/yaranaika.wav");

	    setFocusable(true);
		addKeyListener(this);
	}
	
	public void update() {
		om.update();
	}
	
	public void createGame() {
		om = new ObjectManager();
		pm = new PlayerManager();			
	}


	@Override
	public void paint(Graphics g) {
		buffer.setColor(getBackground());
		buffer.fillRect(0, 0, WIDTH, HEIGHT);

        if ( gameFlag ) {
        	om.drawObject(buffer);
        	pm.drawPlayer(buffer);
		    ope.draw(buffer);		    
        } else {
            ss.start(buffer);
        }
        g.drawImage(back, 0, 0, this);
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == drawThread) {
			if ( gameFlag ) {
		        update();
			}
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
			}
			if ( gameFlag ) {
			    repaint();
			}
		}
	}


	@Override
    public void start() {
        if (drawThread == null) {
            drawThread = new Thread(this);
            createGame();
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
			//applet.start();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'W': om.getRacket(Color.RED).flagUp(Direction.UP); break;
		case 'Z': om.getRacket(Color.RED).flagUp(Direction.DOWN); break;
		case 'A': om.getRacket(Color.RED).flagUp(Direction.LEFT); break;
		case 'S': om.getRacket(Color.RED).flagUp(Direction.RIGHT); break;
		case 'R': PlayerManager.getRedPlayer().invisible(om.getPuck1(), om.getPuck2()); break;
		case 'F': PlayerManager.getRedPlayer().powerPuck(om.getPuck1(), om.getPuck2()); break;
		case 'C': PlayerManager.getRedPlayer().bigRacket(om.getRacket(Color.RED)); break;

		case 'I': om.getRacket(Color.GREEN).flagUp(Direction.UP); break;
		case 'M': om.getRacket(Color.GREEN).flagUp(Direction.DOWN); break;
		case 'J': om.getRacket(Color.GREEN).flagUp(Direction.LEFT); break;
		case 'K': om.getRacket(Color.GREEN).flagUp(Direction.RIGHT); break;
		case 'Y': PlayerManager.getGreenPlayer().invisible(om.getPuck1(), om.getPuck2()); break;
		case 'G': PlayerManager.getGreenPlayer().powerPuck(om.getPuck1(), om.getPuck2()); break;
		case 'B': PlayerManager.getGreenPlayer().bigRacket(om.getRacket(Color.GREEN)); break;

		case KeyEvent.VK_SPACE: if (! gameFlag ) { gameFlag = true; ss.START_BGM.stop(); start(); } break;
		case KeyEvent.VK_ESCAPE: System.exit(0); break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'W': om.getRacket(Color.RED).flagDown(Direction.UP); break;
		case 'Z': om.getRacket(Color.RED).flagDown(Direction.DOWN); break;
		case 'A': om.getRacket(Color.RED).flagDown(Direction.LEFT); break;
		case 'S': om.getRacket(Color.RED).flagDown(Direction.RIGHT); break;

		case 'I': om.getRacket(Color.GREEN).flagDown(Direction.UP); break;
		case 'M': om.getRacket(Color.GREEN).flagDown(Direction.DOWN); break;
		case 'J': om.getRacket(Color.GREEN).flagDown(Direction.LEFT); break;
		case 'K': om.getRacket(Color.GREEN).flagDown(Direction.RIGHT); break;
		}

	}

}
