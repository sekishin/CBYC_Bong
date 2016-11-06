package code;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test extends JApplet implements Runnable{
	
	private Thread drawThread = null;
	
	@Override
	public void init() {
		setSize(getSize());
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
