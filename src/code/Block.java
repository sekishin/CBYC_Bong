package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject {
	private Color color = Color.GRAY;
	
	
	public Block(int x, int y, int w, int h) {
		super(x, y, w, h);		
		rect = new Rectangle(this.x, this.y, this.width, this.height);

	}
	
	public Type getType() {
		return Type.Block;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fill3DRect(x, y, width, height, true);
	}
	
	public void changeColor() {
		this.color = Color.RED;
	}
		
}