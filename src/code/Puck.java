package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Puck extends GameObject {
	private static final int MAX_INVISIBLE_TIME = 250;
	private static final int MAX_POWERFUL_TIME = 250;
	private int dx, dy;
	private Color color = Color.WHITE;  // パックの色
	private static GameSound boin = new GameSound("../music/car.wav");
	private static GameSound crash = new GameSound("../music/crash.wav");
	private static GameSound powerfulCrash = new GameSound("../music/powerfulCrash.wav");
	private static GameSound racket = new GameSound("../music/racket.wav");
	private static GameSound goal = new GameSound("../music/bom.wav");
	private boolean visible = true;
	private Color invisibleColor = Color.WHITE;
	private int invisibleTime;
	private boolean powerful = false;
	private int powerfulTime;
	
	// コンストラクタ
	public Puck(int x, int y, int w, int h, int dx, int dy) {
		super(x, y, w, h);
		this.dx = dx;
		this.dy = dy;
	}
	
	//-- パックの描画
	@Override
	public void draw(Graphics g) {
		if (this.powerful) {
			g.setColor(Color.RED);
			g.fillOval(this.x-6, this.y-6, this.width+12, this.height+12);
			g.setColor(Color.ORANGE);
			g.fillOval(this.x-4, this.y-4, this.width+8, this.height+8);
			g.setColor(Color.YELLOW);
			g.fillOval(this.x-2, this.y-2, this.width+4, this.height+4);
		}
		g.setColor(this.color);  // 描画するときの色
		if (this.visible) {
			g.fillOval(this.x, this.y, this.width, this.height);  // パックの描画
		} else if (this.color != this.invisibleColor) {
			g.drawOval(this.x, this.y, this.width, this.height);
		}
	}
	
	@Override
	public Type getType() {
		return Type.Puck;
	}

	// パックの移動
	public void move() {
		this.x += this.dx;
		this.y += this.dy;
		if (! this.visible) {
			this.invisibleTime++;
     		if (this.invisibleTime > MAX_INVISIBLE_TIME) this.visible = true;
		}
		if (this.powerful) {
			this.powerfulTime++;
     		if (this.powerfulTime > MAX_POWERFUL_TIME) this.powerful = false;
		}
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
/*		if (this.dx > 0) this.x += this.width;
		else this.x -= this.width;
*/	}

	// y軸方向の反射
	public void reflectY() {
		this.dy = -this.dy;
/*		if (this.dy > 0) this.y += this.height;
		else this.y -= this.height;
*/	}
	
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
			if (this.powerful) powerfulCrash.play();
			else crash.play();
			break;
		case Goal:
			goal.play();
			//boin.play();
			break;
		default:
			boin.play();
			break;
		}
		
		if ( this.powerful && go.getType() == Type.Block ) return;
		
		if (Math.abs(px-ox) < this.width) this.reflectX();
		if (Math.abs(py-oy) < this.height) this.reflectY();
	}

	// 打った人のバーの色に変わる
	public void changeColor(Color c) {
		this.color = c;
	}
	
	/*
	 * 色を取得
	 * @ return 現在の色
	 */
	public Color getColor() {
		return this.color;
	}
	
	/*
	 * 見えなくする
	 */
	public void invisible(Color c) {
		this.visible = false;
		this.invisibleTime = 0;
		this.invisibleColor = c;
	}
	
	/*
	 * ブロック破壊時に反射しない
	 */
	public void powerUp() {
		this.powerful = true;
	}
}
