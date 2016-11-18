package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class WinScreen implements ImageObserver {
    private Image WIN_IMAGE;
    private int startWidth;
    private int startHeight;
    private static final int START_X = 0;
    private static final int START_Y = 0;
    public GameSound WIN_BGM = new GameSound("music/fanfare.wav");

    // コンストラクタ
    public WinScreen (int x, int y) {
        this.startWidth = x;
        this.startHeight = y;
    }

    // 描画とBGM再生
    public void win(Graphics g, String p) {
        WIN_IMAGE = loadImage(p);
        g.drawImage(WIN_IMAGE, START_X, START_Y, startWidth, startHeight, this);
        WIN_BGM.start();
    }

    /*
     * 画像の取得
     * @取得した画像
     */
    public Image loadImage (String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        return icon.getImage();
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}