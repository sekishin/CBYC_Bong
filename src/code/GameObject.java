package code;

import java.awt.Graphics;

public abstract class GameObject {
	
	protected int x, y;
	
	// 繧ｳ繝ｳ繧ｹ繝医Λ繧ｯ繧ｿ
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// 謠冗判
	abstract public void draw(Graphics g);

}
