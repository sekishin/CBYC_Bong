package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Operation {
	
	private int x, y;
	private int width, height;
	private int WIDTH = width+700;
	private int HEIGHT = height+71;
	
	public Operation (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public void draw(Graphics g) {
		java.awt.Font font = new Font("ＭＳ 明朝", 16, 16);
		java.awt.Font font1 = new Font("ＭＳ 明朝", 20, 20);
		java.awt.Font font2 = new Font("ＭＳ 明朝", 30, 30);
		int a = x-310;
		int b = y-50;
		int c = x-2;
		int d = y-50;

		g.setFont(font);
		Rule(g, x-500, y+55);
		
		g.setColor(Color.BLACK);
		g.setFont(font1);
		drawButton_Red(g, x-200, y-60, "R");
		drawButton_Red(g, x-200, y-20, "F");
		drawButton_Red(g, x-200, y+20, "C");
		drawButton_Green(g, x-100, y-60, "Y");
		drawButton_Green(g, x-100, y-20, "G");
		drawButton_Green(g, x-100, y+20, "B");
		DeadlyName(g, x-170, y-60, "不可視");
		DeadlyName(g, x-170, y-20, " 剛球");
		DeadlyName(g, x-170, y+20, "巨大化");
		
		
		//g.drawString("R", x-190, y);
		//g.drawString("F", x-190, y+30);
		//g.drawString("C", x-190, y+60);
		//g.drawString("Y", x-90, y+40);
		//g.drawString("G", x-90, y+60);
		//g.drawString("B", x-90, y+80);
		//g.drawString("不可視", x-160, y+40);
		//g.drawString("剛球", x-160, y+60);
		//g.drawString("巨大化", x-160, y+80);
		
		g.setFont(font2);
		drawButton_Red(g, a+5, b, "W");
		drawButton_Red(g, a-25, b+30, "A");
		drawButton_Red(g, a+35, b+30, "S");
		drawButton_Red(g, a+5, b+60, "Z");
		g.setColor(Color.RED);
		g.fill3DRect(a+5, b+30, width, height, true);
		
		//g.drawString("W", a+5, b+40);       // 上の操作
		//g.drawString("A", a-10, b+55);  // 左の操作
		//g.drawString("S", a+20, b+55); // 右の操作
		//g.drawString("Z", a+5, b+70);   // 下の操作
		
		drawButton_Green(g, c+5, d, "I");
		drawButton_Green(g, c-25, d+30, "J");
		drawButton_Green(g, c+35, d+30, "K");
		drawButton_Green(g, c+5, d+60, "M");
		g.setColor(Color.GREEN);
		g.fill3DRect(c+5, d+30, width, height, true);
		
		//g.drawString("I", c+5, d+40);        // 上の操作
		//g.drawString("J", c-10, d+55);  // 左の操作
		//g.drawString("K", c+20, d+55);   // 右の操作
		//g.drawString("M", c+5, d+70);    // 下の操作
	}
	
	public void drawButton_Red ( Graphics g, int x, int y, String str ) {
		g.setColor(Color.RED);
		g.fill3DRect(x, y, width, height, true);
		g.setColor(Color.BLACK);
		g.drawString(str, x+8, y+24);
	}
	
	public void drawButton_Green ( Graphics g, int x, int y, String str ) {
		g.setColor(Color.GREEN);
		g.fill3DRect(x, y, width, height, true);
		g.setColor(Color.BLACK);
		g.drawString(str, x+8, y+24);
	}
	
	public void DeadlyName (Graphics g, int x, int y, String str) {
		g.setColor(Color.BLACK);
		g.fill3DRect(x, y, width+40, height, true);
		g.setColor(Color.WHITE);
		g.drawString(str, x+8, y+24);
	}
	
	public void Rule (Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.draw3DRect(x, y, WIDTH, HEIGHT, true);
		g.setColor(Color.BLACK);
		g.drawString("100点先取で勝ち!!", x+290, y+18);
		g.drawString("ゴールと同じ色のボールが入ると得点が加算されます", x+166, y+34);
		g.drawString("ブロックを崩すと1点  ゴールすれば20点加算されます", x+162, y+50);
		g.setColor(Color.RED);
		g.drawString("80点を超えると、ブロックによる加算はなくなります", x+166, y+66);
	}

}