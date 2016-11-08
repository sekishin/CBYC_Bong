package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Puck extends GameObject {
	
	private int angle = 69;  // 初期位地からの角度
	private double dx = 10 * Math.cos(Math.toRadians(angle));  // 加速度(x軸方向)
	private double dy = 10 * Math.sin(Math.toRadians(angle));  // 加速度(y軸方向)
	private Color color = Color.BLACK;  // パックの色
	private final int SIZE = 10; 
	
	// コンストラクタ
	public Puck(int x, int y) {
		super(x, y);
		int left = x;
		int top = y;
		int right = x + this.SIZE;
		int bottom = y + this.SIZE;
		
		rect = new Rectangle(left, top, right, bottom);	
	}

	//-- パックの描画
	@Override
	public void draw(Graphics g) {
		g.setColor(color);  // 描画するときの色
		g.fillOval((int)x, (int)y, this.SIZE, this.SIZE);  // パックの描画
	}
	
	@Override
	public Type getType() {
		return Type.Puck;
	}

	// パックの移動
	public void move() {
		x += dx;
		y += dy;
	}
	
	public boolean isHit(GameObject go) {
		if ( this.rect.intersects(go.rect) ) { return true; }
		return false;
	}

	// x軸方向の反射
	public void reflectX() {
		dx *= -1;
	}

	// y軸方向の反射
	public void reflectY() {
		dy *= -1;
	}

	// 打った人のバーの色に変わる
	public void changeColor(Color c) {
		this.color = c;
	}
}
