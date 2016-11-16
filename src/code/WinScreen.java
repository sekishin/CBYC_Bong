package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class WinScreen implements ImageObserver {
    private String path = "../image/homo4.jpg";
    private Image WIN_IMAGE = new ImageIcon(getClass().getResource(path)).getImage();
    private int startWidth;
    private int startHeight;
    private static final int START_X = 0;
    private static final int START_Y = 0;
    public GameSound WIN_BGM = new GameSound("../music/yaranaika.wav");

    // コンストラクタ
    public WinScreen (int x, int y) {
        this.startWidth = x;
        this.startHeight = y;
    }

    // 描画とBGM再生
    public void win(Graphics g) {
        g.drawImage(WIN_IMAGE, START_X, START_Y, startWidth, startHeight, this);
        WIN_BGM.start();
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}