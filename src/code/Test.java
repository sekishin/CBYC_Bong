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
	
	@Override
	public void init() {
		setSize(600, 600);
/*		rr = new RedRacket(20, 20);
		gr = new GreenRacket(70, 20);
		setFocusable(true);
		addKeyListener(rr);
		addKeyListener(gr);
*/
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("TEST", 10, 10);
		
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
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 'I':  
		case 'M':  
		case 'J':
		case 'K':
			
		case 'W':  
		case 'Z':
		case 'A':
		case 'S':
		}
	}

	public void keyReleased(KeyEvent e) {}
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
			/* Bong はクラスの名前にあわせる */
			JApplet applet = new Test();
			/* アプレット部分のサイズを指定する */
			applet.setPreferredSize(new Dimension(481, 400));
			frame.add(applet);
			frame.pack();
			frame.setVisible(true);
			applet.init();
			applet.start();
			/* ×ボタンを押したときの動作を指定する */
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}

}
