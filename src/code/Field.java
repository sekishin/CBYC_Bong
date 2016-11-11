package code;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import javax.swing.*;


public class Field extends GameObject implements ImageObserver{
	
	private static Color color = Color.BLUE;
	private Image image;
	private final int WIDTH, HEIGHT;
	private int width, height;
	private static String path = "img/homo3.jpg";
	
	public Field (int x, int y, int w, int h) {
		super(x, y);
		this.WIDTH = w;
		this.HEIGHT = h;
		loadImage(path);
	}
	
	public void draw (Graphics g) {
		paint(g);
	}
	
	
	public void loadImage (String path) {
		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		image = icon.getImage();
		
		width = image.getWidth(this);
		height = image.getHeight(this);
	}

	public void paint (Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		double sx = (double)WIDTH/width;
		double sy = (double)HEIGHT/height;

		System.out.printf("%f %f\n", sx, sy);
		//g2.setColor(color);      // 画像とRectが一致しないので、今消してます
		//g2.fillRect(this.x, this.y, WIDTH, HEIGHT);  // 画像とRectが一致しないので、今消してます
		g2.setTransform(AffineTransform.getScaleInstance(sx, sy));
		g2.drawImage(image, this.x, this.y, this);
		
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	
}