package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Puck extends GameObject {
	
//	private int angle = 69;  // 初期位地からの角度
//	private double dx = 10 * Math.cos(Math.toRadians(angle));  // 加速度(x軸方向)
//	private double dy = 10 * Math.sin(Math.toRadians(angle));  // 加速度(y軸方向)
	private int dx, dy;
	private Color color = Color.BLUE;  // パックの色
	
	// コンストラクタ
	public Puck(int x, int y, int w, int h, int dx, int dy) {
		super(x, y, w, h);
		this.dx = dx;
		this.dy = dy;
	}
	
	//-- パックの描画
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);  // 描画するときの色
		g.fillOval(this.x, this.y, this.width, this.height);  // パックの描画
	}
	
	@Override
	public Type getType() {
		return Type.Puck;
	}

	// パックの移動
	public void move() {
		this.x += this.dx;
		this.y += this.dy;
	}
	
	public boolean isHit(GameObject go) {
		rect = new Rectangle(this.x, this.y, this.width, this.height);
		return rect.intersects(go.rect);
	}
	
	public boolean isOut(GameObject go) {
		return false;
	}

	// x軸方向の反射
	public void reflectX() {
		this.dx *= -1;
	}

	// y軸方向の反射
	public void reflectY() {
		this.dy *= -1;
	}

	// 打った人のバーの色に変わる
	public void changeColor(Color c) {
		this.color = c;
	}
}
