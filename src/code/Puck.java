package code;

import java.awt.Color;
import java.awt.Graphics;

public class Puck extends GameObject {
	int angle = 69;  // 初期位地からの角度
	double dx = 10 * Math.cos(Math.toRadians(angle));  // 加速度(x軸方向)
	double dy = 10 * Math.sin(Math.toRadians(angle));  // 加速度(y軸方向)
	Color color;  // パックの色

	// コンストラクタ
	public Puck(int x, int y) {
		super(x, y);
	}

	//-- パックの描画
	@Override
	public void draw(Graphics g) {
		g.setColor(color);  // 描画するときの色
		g.fillOval((int)x, (int)y, 12, 12);  // パックの描画
	}

	// パックの移動
	public void move() {
		x += dx;
		y += dy;
		// 一時的に実装
/*
 * 		if (x > SIZE) dx *= -1;
		if (y > SIZE) dy *= -1;
		if (x < 0) dx *= -1;
		if (y < 0) dy *= -1;
*/
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
