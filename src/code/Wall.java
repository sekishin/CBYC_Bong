package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {

    public Wall(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, c);
        rect = new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public Type getType() {
    	return Type.Wall;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color); // 描画するときの色
        g.fill3DRect(this.x, this.y, this.width, this.height, true); // 壁の描画
    }

}
