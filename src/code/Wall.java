package code;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {
    Color color;
    int wideth;  // 幅
    int height;  // 高さ
    public Wall(int x, int y, int w, int h, Color c) {
        super(x, y);
        this.wideth = w;
        this.height = h;
        this.color = c;
    }

    @Override
    public Type getType() {
    	return Type.Wall;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color); // 描画するときの色
        g.fillRect((int) x, (int) y, (int)wideth, (int)height); // パックの描画
    }

}
