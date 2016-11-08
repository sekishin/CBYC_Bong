package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {
    
	private Color color;
    private final int width;  // 幅
    private final int height;  // 高さ
    
    public Wall(int x, int y, int w, int h, Color c) {
        super(x, y);
        this.width = w;
        this.height = h;
        this.color = c;
        
        rect = new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public Type getType() {
    	return Type.Wall;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color); // 描画するときの色
        g.fillRect(this.x, this.y, this.width, this.height); // パックの描画
    }

}
