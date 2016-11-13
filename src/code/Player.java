package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Player extends Character implements ImageObserver {
    public Image image;
    // コンストラクタ
    public Player(int x, int y, int w, int h, String p) {
        super(x, y, w, h, p);
        loadImage(p);
    }

    // 描画
    public void draw(Graphics g) {
        g.drawImage(image, this.x, this.y, this.width, this.height, this);
    }

    // 画像の取得
    public void loadImage (String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        image = icon.getImage();
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
