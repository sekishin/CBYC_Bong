package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class StartScreen implements ImageObserver {
    private String path = "../image/Bong.jpg";
    private Image START_IMAGE = new ImageIcon(getClass().getResource(path)).getImage();
    private int startWidth;
    private int startHeight;
    private static final int START_X = 0;
    private static final int START_Y = 0;
    public GameSound START_BGM = new GameSound("../music/zangyousenshi.wav");

    // コンストラクタ
    public StartScreen (int x, int y) {
        this.startWidth = x;
        this.startHeight = y;
    }

    // 描画とBGM再生
    public void start(Graphics g) {
        g.drawImage(START_IMAGE, START_X, START_Y, startWidth, startHeight, this);
        START_BGM.start();
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
