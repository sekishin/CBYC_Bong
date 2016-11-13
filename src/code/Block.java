package code;

import java.awt.Graphics;
import java.awt.Color;

public class Block extends GameObject {
	int block[] = new int[30];
	int weight[] = new int[30];
	int height[] = new int[30]; 
	static Color color = Color.PINK;
	static int NUM = 30;
	
	public Block(int x, int y) {
		super(x, y);
	}
	
	public void draw(Graphics g) {
		create(g);
	}
	
	public void create(Graphics g) {
		int i; // カウント
		for ( i = 0; i < NUM; i++ ) {
			g.setColor(color);
			g.fillRect(weight[i], height[i], weight[i]+x, height[i]+y);
		}
		
	}
	
}