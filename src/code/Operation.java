package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import code.Racket.Direction;

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
		draw3DButton_Red(g, x-200, y-60, Test.RED_INVISIBLE_PUCK, PlayerManager.getRedPlayer().isGaugeMax());
		draw3DButton_Red(g, x-200, y-20, Test.RED_POWER_PUCK, PlayerManager.getRedPlayer().isGaugeMax());
		draw3DButton_Red(g, x-200, y+20, Test.RED_BIG_RACKET, PlayerManager.getRedPlayer().isGaugeMax());
		draw3DButton_Green(g, x-100, y-60, Test.GREEN_INVISIBLE_PUCK, PlayerManager.getGreenPlayer().isGaugeMax());
		draw3DButton_Green(g, x-100, y-20, Test.GREEN_POWER_PUCK, PlayerManager.getGreenPlayer().isGaugeMax());
		draw3DButton_Green(g, x-100, y+20, Test.GREEN_BIG_RACKET, PlayerManager.getGreenPlayer().isGaugeMax());
		DeadlyName(g, x-170, y-60, "不可視");
		DeadlyName(g, x-170, y-20, " 剛球");
		DeadlyName(g, x-170, y+20, "巨大化");
				
		g.setFont(font2);
		draw3DButton_Red(g, a+5, b, Test.RED_UP, ! ObjectManager.getEnemyRacket(Color.GREEN).getFlag(Direction.UP));
		draw3DButton_Red(g, a-25, b+30, Test.RED_LEFT, ! ObjectManager.getEnemyRacket(Color.GREEN).getFlag(Direction.LEFT));
		draw3DButton_Red(g, a+35, b+30, Test.RED_RIGHT, ! ObjectManager.getEnemyRacket(Color.GREEN).getFlag(Direction.RIGHT));
		draw3DButton_Red(g, a+5, b+60, Test.RED_DOWN, ! ObjectManager.getEnemyRacket(Color.GREEN).getFlag(Direction.DOWN));
		g.setColor(Color.RED);
		g.fill3DRect(a+5, b+30, width, height, true);
		
		draw3DButton_Green(g, c+5, d, Test.GREEN_UP, ! ObjectManager.getEnemyRacket(Color.RED).getFlag(Direction.UP));
		draw3DButton_Green(g, c-25, d+30, Test.GREEN_LEFT, ! ObjectManager.getEnemyRacket(Color.RED).getFlag(Direction.LEFT));
		draw3DButton_Green(g, c+35, d+30, Test.GREEN_RIGHT, ! ObjectManager.getEnemyRacket(Color.RED).getFlag(Direction.RIGHT));
		draw3DButton_Green(g, c+5, d+60, Test.GREEN_DOWN, ! ObjectManager.getEnemyRacket(Color.RED).getFlag(Direction.DOWN));
		g.setColor(Color.GREEN);
		g.fill3DRect(c+5, d+30, width, height, true);
		
	}
	

	public void draw3DButton_Red ( Graphics g, int x, int y, int str, boolean flag) {
		g.setColor(Color.RED);
		g.fill3DRect(x, y, width, height, flag);
		g.setColor(Color.BLACK);
		g.drawString(String.format("%c", str), x+8, y+24);
	}
	
	public void draw3DButton_Green ( Graphics g, int x, int y, int str, boolean flag) {
		g.setColor(Color.GREEN);
		g.fill3DRect(x, y, width, height, flag);
		g.setColor(Color.BLACK);
		g.drawString(String.format("%c", str), x+8, y+24);
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
		g.drawString("ゴールと同じ色のボールが入ると得点が加点されます", x+166, y+34);
		g.drawString("ブロックを崩すと1点  ゴールすれば20点加加点されます", x+162, y+50);
		g.setColor(Color.RED);
		g.drawString("80点を超えると、ブロックによる加点はなくなります", x+166, y+66);
	}

}