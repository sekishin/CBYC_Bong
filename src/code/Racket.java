package code;

import java.awt.Color;
import java.awt.Graphics;

public class Racket extends GameObject {
	
	private static final int MAX_BIG_TIME = 180;
	private static final int MAX_RACKET_HEIGHT = 150;
	private static final int MIN_RACKET_HEIGHT =50;
	private static final int CHANGE_HEIGHT = 1;
	private static GameSound expand = new GameSound("music/expand.wav");
	private static GameSound shrink = new GameSound("music/shrink.wav");

	private boolean isBig = false;
	private int bigTime;
	private boolean up = false;
	private boolean down = false;
	private boolean right = false;
	private boolean left = false;

	static enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
	
	public Racket(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fill3DRect(this.x, this.y, this.width, this.height, true);
	}

	@Override
	public Type getType() {
		return Type.Racket;
	}
	
	/*
	 * 一部でも指定したオブジェクト外に出ているか
	 * @ return 外にいるか
	 */
	public boolean isOut(GameObject go) {
		return ! go.getRect().contains(this.getRect());
	}
	
	/*
	 * 指定したオブジェクトと衝突するか
	 * @ return 当たっているか
	 */
	public boolean isHit(GameObject go) {
		return getRect().intersects(go.getRect());
/*		Rectangle o = go.getRect();
		if (this.x + this.width <= o.x) return false;
		if (this.y + this.height <= o.y) return false;
		if (this.x >= o.x + o.width) return false;
		if (this.y >= o.y + o.height) return false;
		return true;
*/	}
	
	/*
	 * 情報の更新
	 */
	public void move() {
		if (this.isBig) {
			this.bigTime++;
			if (this.bigTime >= MAX_BIG_TIME) {
				this.isBig = false;
				shrink.play();
			}
		}
		changeHeight();
		if (up)  {
			this.y -= 10;
			if ( ! ObjectManager.racketExist(this, ObjectManager.getEnemyRacket(this.color)) || isOut(ObjectManager.getField())) back(Direction.UP);
		}
		if (down) {
			this.y += 10;
			if ( ! ObjectManager.racketExist(this, ObjectManager.getEnemyRacket(this.color)) || isOut(ObjectManager.getField())) back(Direction.DOWN);
		}
		if (right) {
			this.x += 5;
			if ( ! ObjectManager.racketExist(this, ObjectManager.getEnemyRacket(this.color)) || isOut(ObjectManager.getField())) back(Direction.RIGHT);
		}
		if (left) {
			this.x -= 5;
			if ( ! ObjectManager.racketExist(this, ObjectManager.getEnemyRacket(this.color)) || isOut(ObjectManager.getField())) back(Direction.LEFT);
		}
	}
	
	/*
	 * 戻る
	 */
	private void back(Direction dir) {
		switch (dir) {
		case UP : this.y += 10; break;
		case DOWN : this.y -= 10; break;
		case RIGHT : this.x -= 5; break;
		case LEFT : this.x += 5; break;
		}
	}
	
	/*
	 * フラグを立てる
	 */
	public void flagUp(Direction dir) {
		switch (dir) {
		case UP : this.up = true; break;
		case DOWN : this.down = true; break;
		case RIGHT : this.right = true; break;
		case LEFT : this.left = true; break;
		}
	}

	/*
	 * フラグをたたむ
	 */
	public void flagDown(Direction dir) {
		switch (dir) {
		case UP : this.up = false; break;
		case DOWN : this.down = false; break;
		case RIGHT : this.right = false; break;
		case LEFT : this.left = false; break;
		}
	}
	
	/*
	 * flagの情報を取得
	 * @ retrurn flag
	 */
	public boolean getFlag(Direction dir) {
		switch (dir) {
		case UP : return this.up;
		case DOWN : return this.down;
		case RIGHT : return this.right;
		case LEFT : return this.left;
		}
		return false;
	}

	/*
	 * 色を取得
	 */
	public Color getColor() {
		return this.color;
	}
	
	/*
	 * 巨大化
	 */
	public void bigRacket() {
		this.isBig = true;
		this.bigTime = 0;
		expand.play();
	}
	
	public void changeHeight() {
		if (this.isBig) {
			beBig(CHANGE_HEIGHT, CHANGE_HEIGHT*2);
			if (ObjectManager.racketExist(this, ObjectManager.getEnemyRacket(this.color)) && ! isOut(ObjectManager.getField())) return;
			beSmall(CHANGE_HEIGHT, CHANGE_HEIGHT*2);
			beBig(CHANGE_HEIGHT*2, 0);
			if (ObjectManager.racketExist(this, ObjectManager.getEnemyRacket(this.color)) && ! isOut(ObjectManager.getField())) return;
			beSmall(CHANGE_HEIGHT*2, 0);
			beBig(0, CHANGE_HEIGHT*2);
			if (ObjectManager.racketExist(this, ObjectManager.getEnemyRacket(this.color)) && ! isOut(ObjectManager.getField())) return;
			beSmall(0, CHANGE_HEIGHT*2);
		} else {
			beSmall(CHANGE_HEIGHT, CHANGE_HEIGHT*2);
		}
	}
	
	/*
	 * 大きくする
	 */
	private void beBig(int dy, int dh) {
		if (this.height >= MAX_RACKET_HEIGHT) return;
		this.y -= dy;
		this.height += dh;
	}
	
	/*
	 * 小さくする
	 */
	private void beSmall(int dy, int dh) {
		if (this.height <= MIN_RACKET_HEIGHT && dh != 0) return;
		this.y += dy;
		this.height -= dh;
	}
	
	
}
