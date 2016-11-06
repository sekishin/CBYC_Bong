package code;

import java.awt.Graphics;

public abstract class GameObject {
	
	private final int SIZE;
	private double x, y;
	
	// コンストラクタ
	public GameObject(int s, double x, double y) {
		this.SIZE = s;
		this.x = x;
		this.y = y;
	}
	
	// 描画
	abstract public void draw(Graphics g);

}
