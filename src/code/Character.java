package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public abstract class Character implements ImageObserver {
    protected int x, y;  // 画像の座標
    protected int width, height;  // 画像の縦横のサイズ
    protected String path;
    protected static String deadly_image_path = "../image/homo4.jpg";
    protected Image deadly_image = loadImage(deadly_image_path);
    protected Image player_image;
    protected int current_gauge = 0;
    public int gauge_max;
    protected Color color;



    // コンストラクタ
    public Character(int x, int y, int w, int h, int m, Color c, String p) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.gauge_max = m;
        this.color = c;
        this.path = p;
        player_image = loadImage(p);
    }

    // 描画
    public void draw(Graphics g) {
        g.drawImage(player_image, this.x, this.y, this.width, this.height, this);
        g.setColor(color);
        g.fill3DRect(this.x, this.y + 450 - current_gauge, this.width,  current_gauge, true); // 必殺ゲージの描画
        if ( current_gauge == gauge_max ) {
            g.drawImage(deadly_image, this.x, this.y + 500, this.width, this.height, this);
        }
    }

    // 画像の取得
    public Image loadImage (String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        return icon.getImage();
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

}
