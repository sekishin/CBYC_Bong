package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Operation {
	
	private int x, y;
	private int width, height;
	
	public Operation (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public void draw(Graphics g) {
		java.awt.Font font = new Font("ＭＳ 明朝", 20, 20);
		int a = x-150;
		int b = y;
		int c = x;
		int d = y;
		
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("赤", x-405, y+15);
		g.drawString("R", x-400, y+40);
		g.drawString("F", x-400, y+60);
		g.drawString("C", x-400, y+80);
		g.drawString("緑", x-305, y+15);
		g.drawString("T", x-300, y+40);
		g.drawString("G", x-300, y+60);
		g.drawString("B", x-300, y+80);
		g.drawString("不可視", x-370, y+40);
		g.drawString("剛球", x-370, y+60);
		g.drawString("巨大化", x-370, y+80);
		
		g.drawString("↑", a, b+20);
		g.drawString("←", a-30, b+55);
		g.drawString("→", a+30, b+55);
		g.drawString("↓", a, b+90);
		
		g.drawString("A", a+5, b+40);       // 上の操作
		g.drawString("A", a-10, b+55);  // 左の操作
		g.drawString("A", a+20, b+55); // 右の操作
		g.drawString("A", a+5, b+70);   // 下の操作
		
		g.drawString("↑", c, d+20);
		g.drawString("←", c-30, d+55);
		g.drawString("→", c+30, d+55);
		g.drawString("↓", c, d+90);
		
		g.drawString("B", c+5, d+40);        // 上の操作
		g.drawString("B", c-10, d+55);  // 左の操作
		g.drawString("B", c+20, d+55);   // 右の操作
		g.drawString("B", c+5, d+70);    // 下の操作
	}

}