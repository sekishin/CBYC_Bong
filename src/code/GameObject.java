package code;

import java.awt.Graphics;

public abstract class GameObject {

  protected final int SIZE;
	protected int x, y;  // puckの座標

	// コンストラクタ
	public GameObject(int s, int x, int y) {
		this.SIZE = s;
		this.x = x;
		this.y = y;
	}

	// 描画
	abstract public void draw(Graphics g);

}
