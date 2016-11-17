package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;


public class Field extends GameObject implements ImageObserver{
	
	private Image image;
	private String path = "../image/homo1.jpg";
	private boolean flag = false; 
	private static Color InitialColor = Color.BLUE;
	private Random rand = new Random();
	
	public Field (int x, int y, int w, int h) {
		super(x, y, w, h, InitialColor);
		switch (rand.nextInt(4)+1) {
		case 1 : this.path = "../image/homo1.jpg"; break;
		case 2 : this.path = "../image/homo2.jpg"; break;
		case 3 : this.path = "../image/homo3.jpg"; break;
		case 4 : this.path = "../image/homo4.jpg"; break;
		}
		loadImage(path);
		this.rect = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public void showImage() {
		if (this.flag) return;
		this.flag = true;
	}
	
	@Override
	public Type getType() {
		return Type.Field;
	}
	
	@Override
	public void draw (Graphics g) {
		g.setColor(color);
		g.fillRect(this.x, this.y, this.width, this.height);
		if (flag) g.drawImage(image, this.x, this.y, this.width, this.height, this);
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