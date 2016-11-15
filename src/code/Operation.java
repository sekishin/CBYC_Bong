package src.code;

public class Operation extends GameObject {
	
	public Operation (int x, int y) {
		detection(x, y);
	}
	
	@Override
	public void draw(Graphics g) {
	}

	@Override
	public Type getType() {}
	
	public void detection (Graphics g, int x, int y) {
		private int a = x+50;
		private int b = y+50;
		private int c = x+100;
		private int d = y+100;
		
		g.drawString("赤", x, y);
		g.drawString("R", x, y+10);
		g.drawString("F", x, y+20);
		g.drawString("C", x, y+30);
		g.drawString("緑", x+30, y);
		g.drawString("T", x+30, y+10);
		g.drawString("G", x+30, y+20);
		g.drawString("B", x+30, y+30);
		g.drawString("不可視", x+15, y+10);
		g.drawString("剛球", x+15, y+20);
		g.drawString("巨大化", x+15, y+30);
		
		g.drawString("↑", a, b);
		g.drawString("←", a-30, b+10);
		g.drawString("→", a+30, b+10);
		g.drawString("↓", a, b+20);
		g.drawString("うえ", a, b+5);       // 上の操作
		g.drawString("みぎ", a-25, b+10);  // 左の操作
		g.drawString("ひだり", a+25, b+10); // 右の操作
		g.drawString("した", a-5, b+20);   // 下の操作
		
		g.drawString("↑", c, d);
		g.drawString("←", c-30, d+10);
		g.drawString("→", c+30, d+10);
		g.drawString("↓", c, d+20);
		g.drawString("うえ", c, d+5);        // 上の操作
		g.drawString("ひだり", c-25, d+10);  // 左の操作
		g.drawString("みぎ", c+25, d+10);   // 右の操作
		g.drawString("した", c-5, d+20);    // 下の操作
		
		
	}
	
}