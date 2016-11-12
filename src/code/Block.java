package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject {
	static Color color = Color.GRAY;
	
	
	public Block(int x, int y, int w, int h) {
		super(x, y, w, h);
		int left = x;
		int top = y;
		int right = x + this.width;
		int bottom = y + this.height;
		
		rect = new Rectangle(left, top, right, bottom);

	}
	
	public Type getType() {
		return Type.Block;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fill3DRect(x, y, width, height, true);
	}
		
}