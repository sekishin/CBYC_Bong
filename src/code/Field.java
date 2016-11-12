package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public class Field extends GameObject implements ImageObserver{
	
	private static Color color = Color.WHITE;
	private Image image;
	private String path = "../image/homo1.jpg";
	
	public Field (int x, int y, int w, int h) {
		super(x, y, w, h);
		loadImage(path);
		
		int left = x;
		int top = y;
		int right = x + this.width;
		int bottom = y + this.height;
		
		rect = new Rectangle(left, top, right, bottom);

	}
	
	@Override
	public Type getType() {
		return Type.Field;
	}
	
	@Override
	public void draw (Graphics g) {
		g.setColor(color);
		g.drawImage(image, this.x, this.y, this.width, this.height, this);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	
	public void loadImage (String path) {
		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		image = icon.getImage();		
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
	
}