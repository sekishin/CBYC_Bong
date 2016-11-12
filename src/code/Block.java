package code;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObject {
	static Color color = Color.GRAY;
	
	
	public Block(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Type getType() {
		return Type.Block;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fill3DRect(x, y, width, height, true);
	}
		
}