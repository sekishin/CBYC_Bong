package code;

import java.awt.Graphics;


public abstract class Character {
    protected int x, y;  // 画像の座標
    protected int width, height;  // 画像の縦横のサイズ
    protected String path;

    // コンストラクタ
    public Character(int x, int y, int w, int h, String p) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.path = p;
    }

    // 描画
    abstract public void draw(Graphics g);

}
