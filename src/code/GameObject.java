package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;
	protected int width, height;
	protected Color color;
	protected Rectangle rect;
	
	enum Type {
		Racket,
		Wall,
		Field,
		Puck,
		Block,
		Goal
	}

	// コンストラクタ
	public GameObject(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.color = c;
	}

	// 描画
	abstract public void draw(Graphics g);
	
	// オブジェクトの種類
	abstract public Type getType();
	
	public Color getColor() {
		return this.color;
	}
	
	// 矩形情報の取得
	public Rectangle getRect() {
		this.rect = new Rectangle(this.x, this.y, this.width, this.height);
		return this.rect;
	}
}
