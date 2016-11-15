package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Puck extends GameObject {
	
//	private int angle = 69;  // 初期位地からの角度
//	private double dx = 10 * Math.cos(Math.toRadians(angle));  // 加速度(x軸方向)
//	private double dy = 10 * Math.sin(Math.toRadians(angle));  // 加速度(y軸方向)
	private int dx, dy;
	private Color color = Color.WHITE;  // パックの色
	private GameSound boin;
	private GameSound beam;
	private GameSound racket;
	private GameSound goal;
	
	// コンストラクタ
	public Puck(int x, int y, int w, int h, int dx, int dy) {
		super(x, y, w, h);
		this.dx = dx;
		this.dy = dy;
		boin = new GameSound("../music/car.wav");
		beam = new GameSound("../music/beam.wav");
		racket = new GameSound("../music/racket.wav");
		goal = new GameSound("../music/bom.wav");

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
		Rectangle o = go.getRect();
		if (this.x + this.width < o.x) return false;
		if (this.y + this.height < o.y) return false;
		if (this.x > o.x + o.width) return false;
		if (this.y > o.y + o.height) return false;
		return true;
		
	}
	
	public boolean isOut(GameObject go) {
		return false;
	}

	// x軸方向の反射
	public void reflectX() {
		this.dx *= -1;
		if (this.dx > 0) this.x += this.width;
		else this.x -= this.width;
	}

	// y軸方向の反射
	public void reflectY() {
		this.dy = -this.dy;
		if (this.dy > 0) this.y += this.height;
		else this.y -= this.height;
	}
	
	public void reflect(GameObject go) {
		int px = (this.dx < 0) ? this.x : this.x + this.width;
		int py = (this.dy < 0) ? this.y : this.y + this.height;
		int ox = (this.dx > 0) ? go.x : go.x + go.width;
		int oy = (this.dy > 0) ? go.y : go.y + go.height;
		
		switch(go.getType()) {
		case Racket:
			racket.play();
			break;
		case Block:
			beam.play();
			break;
		case Goal:
			goal.play();
			break;
		default:
			boin.play();
			break;
		}
		
		if (Math.abs(px-ox) < this.width) this.reflectX();
		if (Math.abs(py-oy) < this.height) this.reflectY();
	}

	// 打った人のバーの色に変わる
	public void changeColor(Color c) {
		this.color = c;
	}
}
