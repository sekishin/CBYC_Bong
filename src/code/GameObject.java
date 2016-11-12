package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;
	protected int width, height;
	public Rectangle rect;
	
	enum Type {
		Racket,
		Wall,
		Field,
		Puck,
		Block
	}

	// コンストラクタ
	public GameObject(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	// 描画
	abstract public void draw(Graphics g);
	
	// オブジェクトの種類
	abstract public Type getType();
}
