package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class DeadlyGauge implements ImageObserver {
	
    private static final int MAX_DEADLY_GAUGE = 10;
	private static final int DEADLY_GAUGE_WIDTH = 100;
	private static final int DEADLY_GAUGE_HEIGHT = 30;
	private static final int DEADLY_IMAGE_WIDTH = 100;
	private static final int DEADLY_IMAGE_HEIGHT = 70;
	private static GameSound MAX_SOUND = new GameSound("music/iai.wav");
	private String deadlyImagePath;
	private final Image DEADLY_IMAGE;
	private final int Y = 440;
	
	private int x;    // どれだけ左にずらすか
	private int currentGauge;
	private Color color;
	
	public DeadlyGauge(int x, Color c, String p) {
		this.x = x;
		this.color = c;
		this.currentGauge = 0;
		this.deadlyImagePath = p;
		this.DEADLY_IMAGE  = new ImageIcon(getClass().getResource(this.deadlyImagePath)).getImage();
	}
	
	/*
	 * ゲージが最大か
	 * @ return boolean
	 */
	public boolean isMax() {
		return (this.currentGauge >= MAX_DEADLY_GAUGE) ? true : false;
	}

	/*
	 * 必殺ゲージの増加
	 */
    public void gaugeUp() {
    	if (currentGauge >= MAX_DEADLY_GAUGE) return;
    	if (this.currentGauge < MAX_DEADLY_GAUGE) this.currentGauge++;
		if (currentGauge == MAX_DEADLY_GAUGE) MAX_SOUND.play();
    }

    /*
     * 必殺ゲージのリセット
     */
    public void gaugeReset() {
        this.currentGauge = 0;
    }
    
    /*
     * ゲージの現在値を取得
     * @ return ゲージの現在値
     */
    public int getGaugeCount() {
    	return this.currentGauge;
    }
    
    /*
     * 必殺ゲージの描画
     */
    public void draw(Graphics g) {
    	g.setColor(color);
    	for (int i = 0; i < this.currentGauge; i++) {
    		g.fill3DRect(this.x, Y - DEADLY_GAUGE_HEIGHT * i, DEADLY_GAUGE_WIDTH, DEADLY_GAUGE_HEIGHT, true);
    	}
    	if (currentGauge == MAX_DEADLY_GAUGE) g.drawImage(DEADLY_IMAGE, this.x, Y+30, DEADLY_IMAGE_WIDTH, DEADLY_IMAGE_HEIGHT, this);
    }

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
}
