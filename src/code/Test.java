package code;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test extends JApplet implements Runnable{
	
	private Thread drawThread = null;
	Block block = new Block(10, 10);
	
	@Override
	public void init() {
		setSize(getSize());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		block.draw(g);
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
			/* Bong 縺ｯ繧ｯ繝ｩ繧ｹ縺ｮ蜷榊燕縺ｫ縺ゅｏ縺帙ｋ */
			JApplet applet = new Test();
			/* 繧｢繝励Ξ繝�繝磯Κ蛻�縺ｮ繧ｵ繧､繧ｺ繧呈欠螳壹☆繧� */
			applet.setPreferredSize(new Dimension(480, 400));
			frame.add(applet);
			frame.pack();
			frame.setVisible(true);
			applet.init();
			applet.start();
			/* ﾃ励�懊ち繝ｳ繧呈款縺励◆縺ｨ縺阪�ｮ蜍穂ｽ懊ｒ謖�螳壹☆繧� */
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}

}
