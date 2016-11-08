package code;

import java.awt.Graphics;

public abstract class GameObject {
	
	protected int x, y;
	
	// コンストラクタ
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// 描画
	abstract public void draw(Graphics g);
}
